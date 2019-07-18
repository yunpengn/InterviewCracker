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

1. Brute force method: Go through all possible combinations of `a`, `b`, `c` and `d` from `1` to `1000`. Check whether 
the equation holds. Time complexity is O(n<sup>4</sup>).
2. Only use three of them: Only go through all possible combinations of `a`, `b`, `c`. Then deduce the value of `d` and 
check whether the deduced value is an integer from `1` to `1000`. Time complexity is O(n<sup>3</sup>).
3. Only two of them with multiple hash map: Use the fact that all possible combinations of `a` and `b` are essentially 
the same as all possible combinations of `c` and `d`. We call each combination of integers `x` and `y` a pair. Create a 
multiple hash map, use their cubic sum x<sup>3</sup> + y<sup>3</sup> as the key, use a list of _pairs_ as the value.<br>
Go through all possible pairs from `1` to `1000` and put them into the multiple hash map. After that, for each entry in 
the hash map, the value (i.e., the list of pairs) would be the possible solutions to the equation.<br>
Time complexity is O(n<sup>2</sup>).

## Substring matching

_Given a smaller string `s` and a larger string `b`, find all permutations of the smaller string that is the substring 
of the larger one._

1. Brute force method: Generate all permutation of `s` and check whether it is a substring of `b`. Time complexity is 
O(s! * b).
2. Sliding window: go through each character in `b`, we say the next consecutive `s` characters consist of a window. We 
then need to check whether this window is a permutation of `s`. This can be done by using a hash map to count the number 
of times each character occurs. Time complexity is O(s * b).

## Tracing the median

_A sequence of numbers are generated and stored into an expanding array one by one. Find out the way to keep track of 
the median._

Maintain two heaps, one min heap to keep track of the bigger half, one max heap to keep track of the smaller half. For 
convenience, augment the two heaps to record the number of elements in each heap as well.<br>
Since we record the number of elements in each heap, we can directly get one of the root to be the median if the total 
number of numbers is odd; we will take the average of the two roots if the total number of numbers is even.<br>
If the number of elements from the two heaps is no longer in balance, we can easily pop and push between each other.

## Elements in common

_Given two sorted arrays and find the elements in common. Assume the length of the two arrays is the same and elements 
in each array is unique._

1. Brute force method: for each element in array `A`, linear search it in the other array `B`. Time complexity is O(n<sup>2</sup>).
2. Use binary search: for each element in array `A`, binary search it in the other array `B`. Time complexity is O(n * log n).
3. Use hash table: put all elements in array `B` into a hash table. For each element in array `A`, check whether it 
exists in the hash table. Time complexity is O(n), while space complexity is also O(n).
4. Merge them: merge the two arrays. During the mering process, count the elements in common. Time complexity is O(n), 
while space complexity is O(1).
