# Chapter 13 - Java

Being one of the most successful languages in the history, there are a lot of language details to learn in Java. For example, there are many de-facto OOP concepts, such as inheritance, override vs overload, etc. You certainly should understand abstract, final, default, etc. as well. Java collection framework has a lot to test as well.

## Examples

#### 13.1 Private Constructor

If a class's constructor is private, that class can only be inherited by inner classes.

#### 13.2 Return from Finally

Yes, the `finally` block will still execute. The `finally` block will only be skipped if the thread or JVM is killed.

#### 13.3 Final, etc.

- `final` means either the variable, field cannnot be modified after initialization or the class cannot be inherited.
- `finally` means the block will always be executed after `try` and/or `catch`.
- `finalize` is called by GC when there is no more reference(s) and collection happens.

#### 13.4 Generics vs Templates

Java generics relies on `type erasure`, thus, it is only a syntax sugar. C++ templates form a glorified macro set. Each type will be generated into a new copy of the template code by the compiler.

#### 13.5 TreeMap, HashMap, LinkedHashMap


