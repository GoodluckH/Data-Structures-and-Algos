# LeetCode

## Java Native Data Structures 

`HashMap`: Implemented by *Seperate Chaining* method. It has a constant runtime for insert and search due to the hashCode method. But worst case scenario might result in logarithmic runtime of lg*N*.

`Arrays.sort()`: Implemented by using *dual-pivot quicksort* which has an O(*n*log*n*) runtime, and is faster than single pivot quicksort. 

`Collections.sort()`: Uses merge sort to ensure stability.

More for sorting: https://stackoverflow.com/questions/15154158/why-collections-sort-uses-merge-sort-instead-of-quicksort


`String.substring()`: this should take about O(M) in runtime in the newest Java, but in Java 6 this was constant time.

`String.indexOf()`: seems like this is O(MN), too.

`String.split()`: https://stackoverflow.com/questions/13081527/splitting-string-on-multiple-spaces-in-java.
```
    `+` - Represents 1 or more
    `*` - Represents 0 or more
    `?` - Represents 0 or 1
`{n,m}` - Represents n to m
```
So, if we want to split by multiple spaces, we can call `split("\\s+")`. This is an O(N) algo.

If the string has leading space, then we need to call `trim()` first to get rid of the space. This method takes at worst O(N) time.

`String.join(delimitor, String[])`: https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string This is essentially an O(N) built-in function to join strings from an array. 