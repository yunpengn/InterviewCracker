# Technical Questions - Examples

This document contains some interesting examples of technical questions. They are mainly related to data structures and 
algorithms. No domain-specific knowledge is required.

## Pairs with difference

_Given an array of integers, count the number of pairs of integers within the array that have difference `k`. Assume that 
all integers in the array are distinct._

1. Brute force method: Go through the array, enumerate all pairs, find those whose difference is `k`. Time complexity is 
O(n<sup>2</sup>).
2. Sort and binary search: Sort the array first, then for each integer `n` in the array, binary search whether `n + k` 
or `n - k` is in the array. Time complexity is O(n * log n).
3. Hash table: put all integers into a hash table. Then for integer `n` in the array, check whether the hash table contains 
either `n + k` or `n - k`.

## Cube equality

_Find all possible solutions to the equation a<sup>3</sup> + b<sup>3</sup> = c<sup>3</sup> + d<sup>3</sup>, where `a`, 
`b`, `c` and `d` are integers from `1` to `1000` (both inclusive)._

1. 