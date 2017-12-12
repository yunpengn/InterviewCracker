# Chapter 1 - Arrays and Strings

Arrays and strings may be possibly the most basic concept for a technical interview. Essentially, in C, a string is just 
an array of characters. Thus, arrays and strings are the same thing.

## Related Knowledge

- In some languages (like C/C++ and Java), arrays are of fixed length.  However, in some other languages (like JavaScript), 
arrays are automatically resizable. This is why some people call resizable arrays "**_lists_**".
- In Java, `ArrayList` is an array-like data structure that offers the dynamic resizing feature.
- In Java, the resizing factor of an `ArrayList` is 2, which leads to an amortized access/insert time of O(1).
- In Java, `StringBuilder` can be used to reduce time complexity due to mass string concatenation.
- Hashing is an important technique to implement highly efficient lookup in O(1). That means, for an array of elements, 
a linear search results in O(n), while a binary search results in O(log n). However, if we do some pre-computation and 
insert all elements into a hash table, then the lookup time will be O(1).

## Examples

#### 1.1 Is Unique

We are asked to check if a string has any duplicate characters, i.e., whether any character appears more than once. Below 
are a few ways to solve this problem.

1. I personally think using a hash table may be the most suitable (though may not be the most efficient) way. We can just 
go through each character in the string and put them into the hash table. However, before putting them into the hash table, 
check whether it already exists in the hash table. If so, return false. Time complexity is O(n).
2. If we can assume that the string only contains ASCII/extended-ASCII characters. Then we could simply create an array 
of size 128/256. Each element in the array is a boolean flag. Similarly, we go through the string and change the element 
to be true. If the element is already true, return false. Notice this means the string cannot be longer than 128/256. 
Otherwise, there must exist duplicates (according to Pigeonhole principle). This implies the size n can be seen as a 
constant in this case. Thus, time and space complexity are both O(1).
3. If all characters are UniCode, the above method is still theoretically feasible but will consume much space.
4. Alternatively, we could sort all characters in the string and check whether any neighbors are the same. Time complexity
is O(n * log n).


#### 1.2 Check Permutation

This question is actually quite similar to 1.1, in which the way to solve. Below are a few methods.

1. Sort two arrays (need to convert them to two arrays of characters first) and check whether they are the same. Time 
complexity is O(n * log n).
2. For string `A`, build a hash map to store the number of times each character occurs. That means, the key of the hash 
map is the character, the value is an integer. For string `B`, iterate through each character, check the value in the 
hash map, if it does not exist or equal to 0, return false; otherwise, decrease that integer by 1 and continue. After 
finishing the iteration, return true. Time complexity is O(n).
