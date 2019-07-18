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
3. Similarly, you can use an array rather than hash map to store the number of times each character occurs.

#### 1.3 URLify

We iterate through the character array, but from the end. Use two pointers, one to record down the position where the next item should be put, the other to note where we have traversed until. If a space, we put 3 new items (`%20`) in the slower pointer; otherwise, just put the char at the faster pointer to the slower pointer.

Time complexity O(n), space complexity O(1).

#### 1.4 Palindrome Permutation

1. Iterate through the character array. Use a hash map (or an array) to record down the number of times each character has occured. If all letters occurred even number of times (or one and only one of them occurred odd number of times), then return true. Time complexity O(n), space complexity O(n). 
2. Can use a bit map to save space.

#### 1.5 One Away

1. Iterate through the character array. We can definitely do this in O(n) time and O(1) space. It is just a matter how we can code it out clearly and bug-free.
2. According to the length of the two strings, we can certainly just to split it into 3 cases and handle in 3 different functions. For each case, we can just use a flag and code it out simply.
3. Think about the case when two things are of very different length.

#### 1.6 String Compression

1. Nothing fancy about this question. Just iterate through the string. O(n) and O(k) space.
2. Remeber to use `StringBuilder` if you are using Java.
3. You can probably pre-iterate the string to determine the length of the result first.

#### 1.7 Rotate Matrix

Implement the rotation layer by layer. For each layer, rotate index by index. In such a way, we can implement the algorithm in place. In O(n^2) time and O(1) space.

#### 1.8 Zero Matrix

1. Create a new matrix for output. O(n^2) time and O(n^2) space.
2. Use two O(n) array to record whether we should clear a certain row/column. O(n^2) time and O(n) space.
3. Assume there is no negative item in the matrix. Whenever we see a 0, we clear its row & column by setting all non-zero elements to -1. Then, in the 2nd iteration, we set all -1 to 0. O(n^2) time and O(1) space.
4. If we cannot assume no negative item: use 1st row and 1st column to replace the two O(n) arrays we originally need. To do this, we first check whether the 1st row and 1st column themselves contain any zero items. We save this information in 2 flags and set the 1st row and 1st column to be all 0s if so later. Then, whenever we find a zero item, we just set `A[m][0]` and `A[0][n]` to be 0. In the 2nd iteration, whenver `A[m][0]` is 0, we set the whole row to be 0; the same vertically.

#### 1.9 String Rotation

This is a logic problem rather than coding problem. In fact, `A = xy` and `B = yx`. Then `AA = xyxy`. We just need to check whether B is a substring of `AA`.
