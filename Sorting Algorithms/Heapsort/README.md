#Heapsort

A heap is a data structure that resembles a binary search tree. The difference is that a heap perserves certain priority. For example, a max-oriented heap will have the largest item on its root and have the invariant that the children of a node must be smaller or equals to the node.

Heaps are a popular ADT to implement Priority Queues with efficient performance characteristics. A typical Priority Queue has the following APIs (in Java for clarity):

```java
public class MaxPQ<Key extends Comparable<Key>>
             MaxPQ()            *create a pq*
             MaxPQ(int max)
             MaxPQ(Key[] a)
        void insert(Key v)
         Key max()
         Key delMax()
     boolean isEmpty()
         int size()
```