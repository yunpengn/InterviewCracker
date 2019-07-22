# Java

In this guide, we introduce some knowledge about Java.

## General

- Java compilation:
    - `.java`, `.class` and binary files
    - Hotspot, JIT, AoT (ahead of time) compilation
- Difference between Java and C++:
    - Memory management
    - Multi-inheritance
- Difference between override and overload
- Difference between `StringBuilder` and `StringBuffer`:
    - Both extend the abstract class `AbstractStringBuilder`
    - `StringBuffer` is thread-safe
    - `StringBuilder` is not thread-safe, but is about 10% better in performance
- Difference between abstract class and interface:
    - Interface only allows public methods, while methods in abstract class allow all different access modifiers
        - Abstract methods in an abstract class cannot be private, though. Otherwise, subclasses cannot see and implement it
    - Methods in interface cannot have implementation, while non-abstract methods in abstract class can
        - Starting from Java 8, interface can declare a method as `default` and have implementation
    - Fields in interface must be `public static final`, while there is no restriction for fields in an abstract class
    - A class can implement multiple interfaces, but can only extend one abstract class
- Difference between `final`, `finally` and `finalize`:
    - `final` is used to declare a class that cannot be inherited, a method that cannot be overridden, or a field whose value cannot be changed
    - `finally` is a block of code that will always be executed no matter `try` or `catch`
        - Code in `finally` will only be skipped if the program, the process or JVM crashes
        - If both `try` and `finally` return a value, the actual return value will be the one from `finally`
    - `finalize` is the method being called by GC before a class is garbage-collected
- Difference between `==`, `equals` and `hashCode`:
    - `==` is used to compare the memory addresses of two objects
    - `equals` is used to compare equality, and will fall back to `==` if it is not overridden in the class
    - `hashCode` is used to compute hash value of an object, and will be used in collection framework such as `HashMap`
    - If `equals` return `true`, `hashCode` must return the same value; but not vice versa
- Difference betwen co-routine, thread and process
- Difference between `Throwable`, `Error`, `Exception`, `RuntimeException` and `assert`:
    - `Throwable` is the most generic class from `java.lang` package
    - `Error` inherits from `Throwable` and represents serious errors that are usually not recoverable. You can but should NOT catch `Error`s
    - `Exception` also inherits from `Throwable`. There are two types of exceptions, checked and un-checked exceptions. An exception becomes an un-checked exception when it inherits from `RuntimeException`, a subclass of `Exception`. Any checked exception must be catched or declared in method signature using `throws`.
    - `assert` is used to verify the correctness of an invariant in the code. They should not be used in production environment. To enable assertion in Java, pass an `-ea` command to JVM.
- Difference between `BIO`, `NIO` and `AIO`:
    - `BIO` is the most traditional mode, which stands for blocking I/O.
    - `NIO` stands for new, non-blocking I/O, which is introduced in Java 4. It supports channel and buffer.
    - `AIO` is the 2nd generation of `NIO`, which is introduced in Java 7. It supports an asynchronous, non-blocking model, similar to JavaScript.

## Collection Framework

- Difference between `LinkedList`, `ArrayList` and `Vector`:
    - `Vector` is thread-safe, while `LinkedList` and `ArrayList` are not.
        - However, this also means `Vector` could bring performance bottleneck.
        - The OpenJDK is working torwards a compile-time solution to optimize `Vector` by using the optimal vector hardware instructions on CPU. See [JEP 338](https://openjdk.java.net/jeps/338).
    - `LinkedList` does not support random index access, while `ArrayList` and `Vector` do.
    - `ArrayList` is a dynamic array. Thus, it supports automatic capacity growth:
        - `ArrayList` has a default capacity of 10.
            - Starting from Java 7, `ArrayList` supports deferred default capacity. When created, the `ArrayList` is empty. It will only have a capacity of 10 when first insertion happens.
        - `ArrayList` grows at the rate of 1.5:
            - This is calculated by `oldCapacity + (oldCapacity >> 1)`. Java 7 uses bit operation to improve performance.
            - To copy old elements over to the new array, `Arrays.copyOf` is used.
- Difference between `Hashtable`, `HashMap`, `ConcurrentHashMap`, `LinkedHashMap` and `TreeMap`:
    - `Hashtable` and `ConcurrentHashMap` is thread-safe, but `HashMap` is not.
        - However, `ConcurrentHashMap` would provide better performance under high concurrency.
    - `HashMap` is implemented as an array of `LinkedList`s (because of hash conflict):
        - Starting from Java 8, the underlying `LinkedList` will be changed to a red-black tree when there are more than 8 items in the bucket. It will be converted back to a `LinkedList` when there are less than 6 items in the bucket.
    - `HashMap` has an initial size of 16 and a default load factor of 0.75:
        - When exceeding the current capacity, the `HashMap` will be resize with a factor of 2. This is calculated by `oldCap << 1`.
    - `Hashtable` has an initial size of 11 and a default load factor of 0.75
        - When exceeding the current capacity, the `Hashtable` will be resize with a factor of about 2. This is calculated by `(oldCap << 1) + 1`.
