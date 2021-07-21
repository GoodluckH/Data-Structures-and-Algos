# LeetCode

## Java Native Data Structures 

`HashMap`: Implemented by *Seperate Chaining* method. It has a constant runtime for insert and search due to the hashCode method. But worst case scenario might result in logarithmic runtime of lg*N*.

`Arrays.sort()`: Implemented by using *dual-pivot quicksort* which has an O(*n*log*n*) runtime, and is faster than single pivot quicksort. 

`Collections.sort()`: Uses merge sort to ensure stability.

More for sorting: https://stackoverflow.com/questions/15154158/why-collections-sort-uses-merge-sort-instead-of-quicksort
