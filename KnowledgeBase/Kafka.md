# Kafka

In this guide, we introduce some knowledge about Kafka stream.

- Compared to other message queues, Kafka has the following advantages:
  - A more modern architecture (compared to traditional queue model or broadcast model) where broker is an independent middleman;
  - A fault-tolerant & persistent streaming service as all messages are stored on the hard disk of broker servers;
  - Highly available as each partition could be replicated across multiple broker servers;
  - Highly scalable due to support for multiple partitions of the same topic and the concept of consumer group.
- Kafka has to rely on ZooKeeper for metadata management such as:
  - Broker registry;
  - Topic registry;
  - Load balancing (for both producer and consumer).
- Kafka guarantees strict ordering within each partition, as well as loose ordering across partitions of the same topic.
