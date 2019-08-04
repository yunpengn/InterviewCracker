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

## MySQL character set

- In production, always use `utf8mb4` as the character set for all tables.
	- Starting from MySQL 8.0, `utf8mb4` is the default character set.
- `utf8` character set is not really UTF8-compatible. It uses a maximum of 3 bytes per character.

## MySQL Concurrency, Transaction & Isolation Level

![Isolation Levels](../img/sql_isolation.png)
