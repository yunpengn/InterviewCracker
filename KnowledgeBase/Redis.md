# Redis

In this guide, we introduce some knowledge about Redis.

## Why is Redis single-threaded?

- For a memory-based cache such as Redis, CPU is usually not the bottleneck. Redis is usually memory or network bound.
- Using single-threaded avoids the resources wasted on context switch, or the time waiting for lock, etc.
- To utilize multiple CPUs, the official recommendation is to start multiple Redis instances on the same machine.

## Differences between Redis and Memcached

- Redis supports more data types.
- Redis supports data persistence using RDB or AOF.
- Starting from Redis 3.0, Redis natively supports cluster mode.
- Redis is single-threaded, while Memcached is not.

## Differences between Redis and etcd

- Redis and etcd are both distributed key-value store.
- However, Redis is an in-memory cache, while data in etcd can only be persisted in hard disk.
	- Thus, etcd does not provide fast querying. etcd should not be used as the primary data store or cache.
- As of 2019, the only correct usage of etcd is for configuration management and service discovery.
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
