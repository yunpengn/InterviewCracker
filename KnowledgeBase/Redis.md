# Redis

In this guide, we introduce some knowledge about Redis.

## What data types does Redis support?

- According to [its official documentation](https://redis.io/topics/data-types-intro), Redis is a remote _data structures server_ that stores key-value pairs.
- The keys in Redis are binary-safe strings (i.e., any binary sequence is eligible, like the content of a JPEG file). The maximum key size is 512MB.
    - However, it is not recommended to use keys that are too long. If possible, you can consider use its hash value instead.
- The values in Redis can be:
    - String: similar to keys, cannot be larger than 512MB.
    - List: implemented as linked list.
    - Hash: a hash map from string to string, can store at most 2^32 entries.
    	- A hash could be implemented using a ziplist (when the size is relatively small) or a hash table.
    - Set: a hash set of strings, can store at most 2^32 members.
    	- A set could be implemented using an inset (when the size is relatively small) or a hash set.
    - ZSet: a sorted set with the order defined by a floating-point number `score`, can store at most 2^32 members.
    	- When the ZSet is relatively small (as defined by `zset-max-ziplist-entries` and `zset-max-ziplist-value`), it would be implemented using a ziplist (a compact-form linked list).
    	- Otherwise, the ZSet would be implemented using a skip list.
- Why Redis uses ziplist when the data structure is relatively small?
    - Because ziplist is simple and consumes a smaller memory footprint.

## Why is Redis single-threaded?

- For a memory-based cache such as Redis, CPU is usually not the bottleneck. Redis is usually memory or network bound.
- Using single-threaded avoids the resources wasted on context switch, or the time waiting for lock, etc.
- To utilize multiple CPUs, the official recommendation is to start multiple Redis instances on the same machine.
- However, Redis became multi-threaded since its 6.0 version (I/O threading feature to avoid network blocking).
    - Before Redis 4.0, Redis is really single-threaded.
    - Starting from Redis 4.0, Redis continues to be single-threaded for main operations. However, it starts to utilize other threads for big-key deletion, unused connection cleanup, etc.
    - Starting from Redis 6.0, Redis becomes really multi-threaded to improve network I/O.

## Differences between Redis and Memcached

- Redis supports more data types.
- Redis supports data persistence using RDB or AOF.
- Starting from Redis 3.0, Redis natively supports cluster mode.
- Redis is single-threaded, while Memcached is not.
- Redis should almost always be better than Memcached. The only possible reason for choosing Memcached is its simplicity.

## Differences between Redis and etcd

- Redis and etcd are both distributed key-value store.
- However, Redis is an in-memory cache, while data in etcd can only be persisted in hard disk.
	- Thus, etcd does not provide fast querying. etcd should not be used as the primary data store or cache.
- The only correct usage of etcd is for configuration management and service discovery.
	- etcd is originally designed to save configurations for Kubernetes.

## Redis Persistence - RDB vs AOF

- RDB is a snapshot-based solution.
- AOF uses append-only file to support better persistence. It is similar to the concept of binlog in MySQL.

## Redis Distributed Lock

- To implement a lock with Redis, we would use `SETNX` _(set if not exists)_ command.
	- For a safer approach, we use `SETNX` to write a random value to the specific key. After that, we use `GET` to check the value at that key is indeed the value set by the current process/thread.
	- To avoid deadlock, it is compulsory to set an expiry time for the key.
- There is an official solution called `Redlock`. See [here](https://redis.io/topics/distlock) for more information.
	- The author of Redis thinks that `RedLock` is better than the traditional implementation.

## Consistency between Redis and MySQL

See https://yunpengn.github.io/blog/2019/05/04/consistent-redis-sql/.

## Redis String Implementation

Redis implements string in a way called [Simple Dynamic String](https://github.com/antirez/sds). It is defined using the following wrapper struct:

```cpp
struct sdshdr {
    int len;
    int free;
    char buf[];
}
```

This brings the following benefits over the native string representation in C:

- Allow `O(1)` time complexity for the length of a given string. For native C, this needs `O(n)`.
- Avoid buffer overflow for string. When appending new characters, SDS will dynamically allocate more spaces.
- Minimize the number of memory allocation operations.

## Differences between Redis Transaction and MySQL Transaction

- Both are used to ensure the atomicity and isolation of a set of statements.
- MySQL uses `UNDO/REDO` log to be always able to commit or rollback a given transaction. However, Redis is unable to rollback.

## Why does Redis Cluster need at least 3 master nodes?

- Short answer: in the event of partial failure, you need at least 3 master nodes to agree on majority.
- Long answer: Redis Cluster uses `PING/PONG` to detect failures. When node `A` sends `PING` to node `B` but did not receive the response of `PONG`, `A` will believe `B` has gone offline. When majority (more than half) of the master nodes believe a certain node is offline, that node will be removed from the cluster (and its slave will be promoted). If there are only 2 master nodes and the link between them breaks, they will both believe the other goes offline (and everything becomes a mess).

## How doees Redis Cluster work?

- Unlike ZooKeeper or etcd, Redis Cluster does not need an extra centralized party to coordinate and maintain cluster information. Nodes inside a Redis Cluster uses the gossip protocol to monitor the health of each other.
- Unlike other data stores which often use consistent hashing for data partitioning, Redis Cluster uses a unique hash slot approach which always divides the key space into 16384 slots. Then, the nodes only have ownership on some of the slots.

## What does Redis do when the keys expire?

- Redis utilizes a combination of the following two strategies:
    - Lazy deletion: only execute `expireIfNeeded` function when there is an operation on this key and it has expired.
    - Periodic deletion: perform a scan of the expiration hash-set every 10s, scan & `expireIfNeeded` for 20 keys in the hash-set randomly, repeat this process if more than 25% of the 20 keys are deleted.
