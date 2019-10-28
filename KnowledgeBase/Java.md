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
        - Abstract methods in an abstract class cannot be `private`, though. Otherwise, subclasses cannot see and implement it
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
- Difference between `Hashtable`, `HashMap`, `ConcurrentHashMap`, `LinkedHashMap`, `TreeMap` and `IdentityHashMap`:
    - `Hashtable` and `ConcurrentHashMap` is thread-safe, but `HashMap` is not.
        - However, `ConcurrentHashMap` would provide better performance under high concurrency.
    - `HashMap` is implemented as an array of `LinkedList`s (because of hash conflict):
        - Starting from Java 8, the underlying `LinkedList` will be changed to a red-black tree when there are more than 8 items in the bucket. It will be converted back to a `LinkedList` when there are less than 6 items in the bucket.
    - `HashMap` has an initial size of 16 and a default load factor of 0.75:
        - When exceeding the current capacity, the `HashMap` will be resize with a factor of 2. This is calculated by `oldCap << 1`.
    - `Hashtable` has an initial size of 11 and a default load factor of 0.75
        - When exceeding the current capacity, the `Hashtable` will be resize with a factor of about 2. This is calculated by `(oldCap << 1) + 1`.
    - `ConcurrentHashMap` uses segments to have better concurrency support. Theoretically, the number of concurrent access allowed is equal to the number of segments.
        - By default, `ConcurrentHashMap` creates 16 segments.
        - Starting from Java 8, `ConcurrentHashMap` is re-designed. It uses an array of `LinkedList`s (or red-black trees when there are too many items in a bucket). Effectively, it is the same as `HashMap`. It uses CAS to add lock when necessary.
    - `LinkedHashMap` is similar to `HashMap`, but it preserves the insertion & access order of the elements inside. It is a subclass of `HashMap`, and is introduced in Java 4.
        - However, similar to `HashMap`, `LinkedHashMap` is not thread-safe.
        - Internally, `LinkedHashMap` maintains a double linked list. Whenever a new element is inserted or an old element is accessed, that node will be put to the head of the double linked list.
        - `LinkedHashMap` is useful for implementing an LRU (least recently used) cache.
    - `TreeMap` is also a key-pair collection. Although it supports ordering, its access time complexity is `O(logn)` rather than `O(1)`.
        - Internally, a balanced red-black tree is maintained.
    - `IdentityHashMap` uses reference-equality rather than object-equality when comparing keys. Its original use case is to store topology-preserving graph transformation, such as serialization or deep copying. However, it can actually be used as a `MultiHashMap`, in which we can store multiple entries with equal keys.

## Concurrency

- Difference betwen co-routine, thread and process:
    - Java supports multi-process and multi-thread only.
    - Thread is a lighter concept compared to process. Threads from the same process share some common resources, so we could save some resources when doing context switches between threads.
- Difference between `Runnable` and `Thread`:
    - Both are used to implement multi-thread in Java. Both need to override the `run` method.
    - `Runnable` is an interface, while `Thread` is a class. `Thread` actually also implements the `Runnable` interface.
    - Since Java does not support multi-inheritance, it may be better to use `Runnable` to support more features.
    - Also, using `Runnable` can help to share resources (i.e., you can instantiate one `Runnable` object and use multiple `Thread` objects to start with it. Then, the fields inside that `Runnable` object will be shared).
- Different use cases of `synchronized`:
    - Lock for the whole class when using it on static methods.
    - Lock for the single instance otherwise.

## Spring

- IoC (inverse of control), DI (dependency injection), AOP (aspect-oriented programming)
    - Spring AOP separates the business logic and system services (such as logging, auditing, transaction management). It in fact uses the delegate pattern. In oher words, it attempts to use either `XML` configuration files or Java annotations to dynamically and implicitly insert the code for system services into the code for business logic.
    - IoC is a useful design pattern. In Spring, all objects will be stored in IoC containers. These containers then adopt the factory pattern and provides the instantiation of objects when needed. Therefore, there is no need to manually create these objects.
    - DI can be achieved via either constructor or setter.
