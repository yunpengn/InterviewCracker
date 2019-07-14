# Chapter 8 - Recursion and Dynamic Programming

When talking about recursion algorithm, always remember to include the recursion stack when considering the space complexity. Also, it may be possible to convert a recursive algorithm to an iterative one using stack (to emulate the stack trace). DP & memoization is basically using an array to cache the result of some recursive calls.

## Examples

#### 8.1 Triple Step

steps(n) = steps(n - 1) + steps(n - 2) + steps(n - 3)

#### 8.2 Robot in a Grid

A certain cell is defined to be reachable if either its left or up cell is reachable. At the beginning, only the top-left cell is reachable. Just iterate over this 2D array.

#### 8.3 Magic Index

To find the index such that `A[i] = i`, we have to use a modified version of binary search. If `A[n / 2] < n / 2`, we go to the right half; otherwise, we go to the left half.

If the values are not distinct, we need to modify the algorithm above a bit. If `A[n / 2] < n / 2`, we not only need to search the right half, we have to search for part of the left half, specifically, in the range of `[A[n / 2], n / 2]`.

#### 8.4 Power Set

There are `2^n` subsets for a set with `n` elements. Use a recursion algorithm, by having each element either present or absent. Alternatively, use a bitmap to implement an iterative approach (i.e., integers from `0` to `2^n - 1)`.
