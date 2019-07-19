# Chapter 9 - System Design and Scalability

The most important thing in a system design interview is to communicate. You must be sure about every detail of the requirements. This is the same as the real world. When you are at work, you need to communicate with the PMs well.

Also, you should adopt the start-up philosophy: make it work first, then make it scale.

## Examples

#### 9.1 Stock Data

In fact, 1000 client applications may not be a lot. We can simply use an SQL database with read replicas, and/or Redis cache. If there are too many data to store, we can do a sharding as well.

Alternatively, we can even store the data as text files and provide an API which is served by static files. We can use things like HTTP basic authentication. And yeah, by the way, things like AWS S3 is also a good option.

#### 9.2 Social Network

Use a bi-directional (starting from both source and destination) BFS. In real world, you probably should NOT even design such a feature. With millions of users, you definitely need sharding. And the sharding could be "smart", such as partition by geo-location (since people in the same city are more likely to know each other).

#### 9.3 Web Crawler

You should look at Google's PageRank (PR) algorithm. For a simplified version, we can avoid cycles in a link graph by using a HashTable. However, in the real world, this is not possible.

- For the Internet, it is very hard to decide whether we have visited this page before. For instance, two pages with different URLs may have the same content. Two pages may have randomly generated content but have a cyclic dependency. Thus, we need to define a (probably very complex) function to determine some degree of similarity (or identity).
- Notice that the function above is just an approximation. So basically, we give the link a score. With a lower score, it does not mean we will never visit it again. It just means that link will have a lower priority.

#### 9.4 Duplicate URLs

Use either Bloom filter or external sorting.

#### 9.5 Cache

Use a ultra-scale distributed cache with LRU retention policy。

#### 9.6 Sales Rank

The sales rank should be from an OLAP database, which should be decoupled from the OLTP database. The OLAP db can get new purchases notificatinos from OLTP db via a message queue. The first n items for each category must be cached and shall be updated periodically via a background worker.

#### 9.7 Personal Financial Manager

We probably should use things like webhook or message queue. Whenever there is a new transaction, banks shall notice us by sending a payload to the webhook or create a new message in the MQ. Whenever receiving a new message, analytics will happen and store the result in database.

#### 9.8 Pastebin

The actual content could be very large. Thus, it is not a wise idea to store them in database. Instead, we shall probably store it as a text file and use a CDN in the frontend (basically, we need S3 + CloudFront). To generate random URLs, we can just use UUID.


