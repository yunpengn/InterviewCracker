# Chapter 3 - Stacks and Queues

Stack and queue are always coming in a pair. The most famous use would be stack for DFS and queue for BFS. Stack is also good companion for recursion.

## Examples

#### 3.1 Three in One

To be honest, this question is very ambiguous itself. The requirement is not clear. It turns out that the key is how to maximize the use of the full capacity. We can create a circular array and perform shifting when required.

#### 3.2 Stack Min

For each element in the stack, maintain the "min" up to that element in the stack.

#### 3.3 Stack of Plates

1. Just maintain an `ArrayList` of `Stack`s.
2. To implement `popAt(int index)`, we need to clarify whether we need to make sure all internal `Stack`s are always at full capacity. If needed, we probably need to use circular array (or probably a linked list) to implement the internal `Stack`s for efficient "pushing down" them.

#### 3.4 Queue via Stacks

The solution of this problem is trying to achieve the so-called amortized time complexity of O(1). Whenever there is a call to `peek` or `dequeue`, we throw all elements in the stack to the other stack. Then, they will be in the reverse order in the 2nd stack (which turns out to the preferred order of a queue). We do this "throw" again only when all elements in the 2nd stack have been dequeued. In this way, the amortized time complexity is O(1).

#### 3.5 Sort Stack

Use the 2nd stack to achieve either selection sort or insertion sort.

#### 3.6 Animal Shelter

1. Store dogs and cats in 2 separate queues and save some timestamp information. This would actually be the real-world engineering solution.
	- Think about Twitter's Snowflake algorithm (because you can always use either an UNIX timestamp or an incremental ID).
2. If we are not allowed to save timestamp, we can use 3 queues (one for dogs, one for cats, and one of both).
