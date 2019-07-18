# Chapter 4 - Trees and Graphs

1. Be sure to always ask whether a tree is a binary tree, whether it is a BST, and whether it is a balanced BST. Never assume a tree is either full, complete or perfect.
2. There are 4 kinds of traversals on a tree: pre-order traversal, in-order traversal, post-order traversal and level-order traversal _(use a queue to implement)_.
3. Remeber to use heap if you need to retrieve the minimum element in O(1) time (you still need O(logn) time if you want to remove the minimum element).
4. Trie (prefix tree) can also be useful sometimes. For example, it can be used to represent strings with common prefix. In this case, we need to use a special value to indicate the end of a string. When representing strings containing lower-case letters only, a trie could be an up to 26-ary tree.
5. A graph can either be direct or indirect, connected or not connected, cyclic or acyclic, weighted or unweighted. DAG is a special set of graphs, which allows topological sort。
6. A graph can have 3 representations: adjacency list, adjacency matrix and edge list.
7. A graph can be searched using DFS or BFS (level-order traversal of a tree is a special case of BFS. BFS can also be used to find the shortest path on an unweighted graph. BFS may be useful for social networks (or even its bi-directional variant). For an iterative implementation, DFS uses a stack while BFS uses a queue. For a recursive implementation, usually only DFS is considered to be possible.
8. There are also advanced algorithms such as shortest path (Bellman-Ford, Dijkstra), minimum spanning tree and network flow.

## Examples

#### 4.1 Route Between Nodes

We can use either BFS or DFS to achieve this. Notice since this is a directed graph, we may need to do bi-directional search.

#### 4.2 Minimal Tree

Just try to make sure the tree is balanced.

#### 4.3 List of Depths

Basically, the question asks us to create a list, each item of which is a linked list of the tree nodes at a certain depth. Here, we assume each tree node has reference to its immediate parent.

Then, we can do either DFS or BFS. However, during the search process, we record down the height of each tree node. Then, according to its height, we put it into the according position in the result list.

#### 4.4 Check Balanced

We can use a recursive function to check the height of each node and thus check whether it is a balanced (by comparing the left and right side).

#### 4.5 Validate BST

1. Do an in-order traversal and check the result.
2. Augment the tree with the min and max for each sub-tree. Then do a recursive check.

#### 4.6 Successor

Given a node, its successor should be:

- If it has a right sub-tree, the min of the right sub-tree;
- Otherwise, its first ancestor of which the current traversing node is its left children;
- If there is no such ancestor, that means the given node is the max node of the whole tree and we should return `null`.

#### 4.7 Build Order

Draw a DAG and use topological sorting. The topo-sort can be done via either post-order DFS or the Kahn's algorithm.

#### 4.8 First Common Ancestor

1. With link to parent: similar to problem 2.7;
2. Without link to parent: iterative solution from the root.

#### 4.9 BST Sequences

We observe that the root must be the first element to be inserted (either for the entire and for each sub-tree). However, the left & right sub-tree does not impose any insertion ordering. In fact, this would produce a restricted version of DAG and we are essentially trying to print out all possible topological sorting.

We use a recursive solution. The all possible BST sequences for a tree is: put the root in the front, recursively find the BST sequences for its left & right sub-trees and intersect them. To "intersect" two lists: we could also use a recursion (similar to the merging process in merge sort).

#### 4.10 Check Subtree

1. Iterative: construct a special version of pre-order traversal for both trees, with using a special placeholder value for each `NULL` value met during the traversal (to fix the problems raised by duplicates).
2. Recursive: traverse `tree1` and whenver meeting a node with its value equal to that of the root of `tree2`, check whether the sub-tree at that point is identical to `tree2`.

#### 4.11 Random Node

We need to use an augmented tree. At each node, we maintain an additional information about the size of the sub-tree rooted at that node. Thus, we know the size of the whole tree, let's say `n`. Then, we do a `randomInt(0, n - 1)` and return the tree node with this index.

#### 4.12 Paths with Sum

1. If the paths have to start from root, we can just use a recursion and keep track of the sum along the way.
2. If paths do not necessaily start from root, we can repeat the above algorithm for every node. But this is brute-force, we need to optimize it.
3. In fact, overlapping sub-problems have occurred and we can optimize this using DP. At each node, we can record the sum of the path from root to that node. Then, at each node, we look back to all of its ancestors.
4. We have successfully turned this problem into the classical sub-array sum problem. To further optimize this, we have a hash map that maps from the sum of the path from root to a certain node to that node. As we go back and traverses up, we need to delete those entries in the hash map that are not applicable anymore.
