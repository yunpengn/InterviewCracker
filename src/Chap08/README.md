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

#### 8.5 Recursive Multiply

In fact, you should think about how CPU instruction set would perform multiplication. We convert two operands to their binary format. We then multiply bit-by-bit (add, left shift, repeat). Of course, you can do things like `a * b = (a / 2) * (b * 2)` from an SE perspective. But that is not really necessary and it is probably not performant as well.

#### 8.6 Tower of Hanoi

This is a very classical recursion problem. We can understand it using mathematical reduction.

#### 8.7 Permutations without Dups

This one is also classical. Use `Perm(n - 1)` to compute `Perm(n)`.

#### 8.8 Permutations with Dups

We represent the input as a hashtable from character to the number of occurrences. Then follow the same approach.

#### 8.9 Parens

Use a recursive solution. `Parens(a, b) = Parens(a - 1, b) + Parens(a, b - 1)`, where `a` and `b` represent the number of open & close brackets respectively.

#### 8.10 Paint Fill
