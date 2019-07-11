# Chapter 4 - Trees and Graphs

1. Be sure to always ask whether a tree is a binary tree, whether it is a BST, and whether it is a balanced BST. Never assume a tree is either full, complete or perfect.
2. There are 4 kinds of traversals on a tree: pre-order traversal, in-order traversal, post-order traversal and level-order traversal _(use a queue to implement)_.
3. Remeber to use heap if you need to retrieve the minimum element in O(1) time (you still need O(logn) time if you want to remove the minimum element).
4. Trie (prefix tree) can also be useful sometimes. For example, it can be used to represent strings with common prefix. In this case, we need to use a special value to indicate the end of a string. When representing strings containing lower-case letters only, a trie could be an up to 26-ary tree.
5. A graph can either be direct or indirect, connected or not connected, cyclic or acyclic, weighted or unweighted. DAG is a special set of graphs, which allows topological sort。
6. A graph can have 3 representations: adjacency list, adjacency matrix and edge list.
7. A graph can be searched using DFS or BFS (level-order traversal of a tree is a special case of BFS. BFS can also be used to find the shortest path on an unweighted graph. BFS may be useful for social networks (or even its bi-directional variant). For an iterative implementation, DFS uses a stack while BFS uses a queue. For a recursive implementation, usually only DFS is considered to be possible.
8. There are also advanced algorithms such as shortest path (Bellman-Ford, Dijkstra), minimum spanning tree and network flow.
