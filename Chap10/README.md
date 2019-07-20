# Chapter 10 - Sorting and Searching

Understand and write out all basic sorting algorithms (quick sort, merge sort, insertion sort, etc.) as well as search algorithms (binary search). Remember corner cases such as radix sort as well.

## Examples

#### 10.1 Sorted Merge

We merge from the end.

#### 10.2 Group Anagrams

Use a multi-hashmap. Override the hash code of each string. This is actually a modification of bucket sort.

#### 10.3 Search in Rotated Array

We could use a modified version of the normal binary search, while can still achieve a time complexity of O(logn).

#### 10.4 Sorted Search, No Size

We shall use a variant of binary search, which is the aggressive cow.

#### 10.5 Sparse Search

1. Filter out all empty strings first. Then do a normal binary search.
2. Modify binary search. When the mid point is an empty string, find the nearest non-empty string.

#### 10.6 Sort Big File

Use external sort.

#### 10.7 Missing Int

- 1GB of memory means 8 billion bits. Thus, we can use half of it to construct a bit vector to store all possible 4 billion items.
- If only 10MB of memory is available: use a two-pass approach. Separate into different ranges and count how many items there are in each range.

#### 10.8 Find Duplicates

4KB means 32,000 bits. Thus, we can use a bit vector.

#### 10.9 Sorted Matrix Search

- Perform binary search on each row or column, time complexity of min(O(m * logn), O(logm * n)).
- Think about the whole matrix of an array of size M * N, time complexity of O(log(m * n)).
- Compare the target with the center of the whole matric. In this way, we can eliminate either 1 or 3 quarters. Recursively perform this.

#### 10.10 Rank from Stream

Use an augmented BST. Each node stores the size of the sub-tree rooted at that node.

#### 10.11 Peaks and Valleys

Put elements into groups of 3 and swap.
