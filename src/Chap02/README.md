# Chapter 2 - Linked Lists

For linked list, always clarify whether it is a single or double linked list. Also, remember check for null pointers. For C/C++, do your memory management properly.

A famous technique for linked lists is called "runner": basically, use two pointers, of which the speed of the fast pointer is twice of the slow pointer. Alternatively, ask the fast pointer to start earlier for `k` units of time (then the distance between the two pointers will always be `k` at any point of time).

## Examples

#### 2.1 Remove Dups

1. Use a HashSet to store all values that have occurred up to now. O(n) time and up to O(n) space.
2. Without extra space, we have to go for O(n^2) time.

#### 2.2 Return Kth to the Last

Just use two pointers. The fast pointer is ahead of the slow pointer for k units. O(n) time and O(1) space.

#### 2.3 Delete Middle Node

Copy the data in the next node to the current one, create a new link, done. In O(1) time and O(1) space.

#### 2.4 Partition

Just create two new linked lists, and put elements into them accordingly.

#### 2.5 Sum Lists

1. If the two numbers are stored in reverse order, we can just iterate through the linked list and use a flag called `carry`.
2. If the two numbers are stored in forward order, it is just a little bit harder. We need to "look ahead" and decide whether need to plus 1 on the current digit.

- Notice: _if two lists are not of the same length, we need to pad the shorter one with 0s first_.

#### 2.6 Palindrome

Use a stack or anything similar.

#### 2.7 Intersection

1. Two linked lists intersect if they have the same tail.
2. Let's say the longer list has a length of `m`, while the shorter list has a length of `n`. Then let the longer list's pointer traverse for `m - n` first so that they would align. Then, traverse at the same speed until they meet each other.

#### 2.8 Loop Detection

Use the "runner" technique. Let the faster pointer be twice the speed as the slower pointer. Let them collide once and call the collision point `P`. Then, put two new pointers of the same speed, one at the head of the linked list, the other at `P`. Let them collide again, the new collision point is the start of the loop.
