# Protocol Buffers

In this guide, we introduce some knowledge about Protocol Buffers (Protobuf).

## What are the main advantages of Protobuf?

- The encoded message in binary format is smaller.
- The encoding & decoding process is faster.
- Language-neutral and can automatically generate serialization & deserialization code in different languages.

## Why is the encoding used by Protobuf efficient?

- **Base 128 varints:** each byte has two parts: MSB (_most significant bit_, used to indicate whether this is the last byte) + rest 7 bits to represent real data (put the binary representation of each value into groups of 7 bits).
- **Message structure:** represent a message as key-value pairs, but use the field number rather than the field name as key. To store the size of the corresponding value, each field number is suffixed with 3 bits to represent its _wire type_. And again, the key is encoded as a _varint_ as well. 
