# Heapsort

A heap is a data structure that resembles a binary search tree. The difference is that a heap perserves certain priority. For example, a max-oriented heap will have the largest item on its root and have the invariant that the children of a node must be smaller or equals to the node.

Heaps are a popular ADT to implement Priority Queues with efficient performance characteristics. A typical Priority Queue has the following APIs (in Java for clarity):

```java
public class MaxPQ<Key extends Comparable<Key>>
             MaxPQ()            // create a pq
             MaxPQ(int max)     // create a pq of initial capacity max
             MaxPQ(Key[] a)     // create a pq from an array
        void insert(Key v)      // insert a key into the pq
         Key max()              // return the largest key
         Key delMax()           // return and remove the largest key
     boolean isEmpty()          // return whether the pq is empty
         int size()             // return number of keys in the pq
```

We implement the heap with an array.

The `insert` and the `delMax` methods each takes *lgN* runtime because the height of the heap (think of it as a tree) is *lgN*. For `insert`, we add the key to the end of the array, and perform `swim` operation as necessary to put the key into the correct place. If we delete the max, or the root, of the heap, we substitute the root with the last (presumbly the smallest) key in the heap, and perform `sink` to get the key to where it belongs.

## Heapsort Runtime and Space Complexity Analysis

There are essentially two parts. The first part is to convert the input array into a max-oriented heap. We do so using the "sink down" approach where starting in the middle of the array, we sink each key to the left to its proper position until the pointer crosses index 1. 

This operation costs *2N* in runtime because the number of exchanges is about *N* but our `sink` method requires 2 compares. 

The second part of the sorting is to remove the current max and swap it with the last key in the array. Then, sink that last key down to where it belongs. And repeat until we delete the whole heap. Doing this allows as to sort the array in place. And this part costs *2NlgN* in runtime because the `delMax` runs in *2lgN* and we perform *N* times `delMax`.

Therefore, the entire sorting operation runs in *2N + 2NlgN*, which is *O(NlgN)*. The best case scenario comes when all elements are equal, in this case, we don't perform and sink or swim operations, so the algo runs in *N* linear time.

Note that there's no recursion calls and that we sort the array in place. That means we use constant additional space. Heapsort's runtime and space efficiency makes it a desirable sorting algo for applications with restricted space.

However, since we are moving keys around over long distances in the second part of the sorting operation, the stability of Heapsort is compromised. More, Heapsort is not efficient for cache techniques because it does not make comparisons with a key's adjacent elements.
