# ZooKeeper

In this guide, we introduce some knowledge about ZooKeeper.

## What is ZooKeeper?

- ZooKeeper is a centralized open-source software for maintaining configuration information, naming, providing distributed synchronization, and providing group services.
- Although ZooKeeper itself a centralized party, its high availability can be guaranted by using ZooKeeper in a clustered setting.
- A ZooKeeper cluster has 1 master node and multiple read-only slave nodes.

## ZAB Protocol

- Internally, ZooKeeper makes use of the ZAB (ZooKeeper Atomic Broadcast) protocol to communicate among different nodes within a cluster.
- ZAB protocol is a generic consensus algorithm on top of Paxos.
