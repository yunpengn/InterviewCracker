# MySQL

In this guide, we introduce some knowledge about MySQL database engine. Some knowledge may be specific to the implementation of MySQL, and not applicable to other SQL engines.

## Differences between MySQL engines - MyISAM vs InnoDB

- In production, you should almost always use InnoDB rather than MyISAM.
	- Starting from MySQL 5.5.5, InnoDB is the default engine for MySQL server.
- InnoDB supports both row-level locking and table-level locking. However, MyISAM only supports table-level locking.
- InnoDB supports transaction management, while MyISAM does not.
- InnoDB supports foreign key, while MyISAM does not.
- InnoDB uses clustered index, while MyISAM uses non-clustered index.
- InnoDB does not store the number of rows in system catalog, while MyISAM does.
	- Thus, if you run `SELECT COUNT(*) FROM my_tbl`, the query will be much faster on MyISAM.
- InnoDB supports disaster recovery much better than MyISAM.
- InnoDB requies every table to have a primary key. This is because InnoDB is an index-organized storage engine (i.e., it always uses clustered index). InnoDB indeed stores the actual data in the leaf nodes of a B+ tree index for the primary key.
    - Thus, it is wise to keep the size of the primary key small.
    - If not supplied, an implicit primary key (i.e., a counter of 6 bytes) will be created.
    - Searches on secondary index will eventually result in a search on the primary key index.

## MySQL character set

- In production, always use `utf8mb4` as the character set for all tables.
	- Starting from MySQL 8.0, `utf8mb4` is the default character set.
- `utf8` character set is not really UTF8-compatible. It uses a maximum of 3 bytes per character.

## MySQL Concurrency, Transaction & Isolation Level

- SQL supports 4 fundamental properties: ACID (atomicity, consistency, isolation, durability).
- To ensure the atomicity and durability of a sequence of SQL statements, they have to be wrapped into an SQL transaction.
	- However, code without transaction would in general lead to better concurrency performance. We can avoid the use of transaction by careful design (such as adopting MVVC, multi-version concurrency control).
- According to ANSI SQL standard, there are 4 isolation levels:

![Isolation Levels](../img/sql_isolation.png)

## MySQL High Performance & High Availability

- To ensure the high performance of MySQL servers, there are a few aspects to consider:
	- Separate read and write: use slaves as read replica;
	- Use shard to partition the database;
	- Avoid the use of lock and transaction.
- To ensure the high availablity of MySQL servers, we have to:
	- Set up a slave for every production MySQL server and the slave must be a different geographic location;
	- Perform daily snapshot-based backup from the slave.
- MVCC (multi-version concurrency control) is useful for applications with high concurrency.
	- It is different from the traditional lock-based concurrency control.
	- The most classical use case for MVCC is the bank transfer problem.
