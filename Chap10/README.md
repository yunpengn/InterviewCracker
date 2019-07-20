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


