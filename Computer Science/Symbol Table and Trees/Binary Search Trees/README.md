# Binary Search Tree Symbol Table  

Implementing an ST using Linked List can be inefficient as it takes linear time for basically all operations.For example, the runtime is linear when inserting a new key-value pair because we need to scan the existing linked list to see if the key being inserted already exists. And when it comes to search, it runs on average at `N/2` because we still need to go through the linked list one by one to compare keys.

Although the search operation can be improved to `lgN` using two arrays lists where we keep them sorted when inserting, the inserting operation would still be linear because it's similar to how Insertion Sort works.

Therefore, introducing a more abstract data structure helps. With a Binary Search Tree (BST) we can achieve logarithemic performance for all operations.

## The BST Data Structure

Similar to a Linked List, we need to define our BST using a private class:

```java
private class Node {
    private Key key;
    private Value val;
    private Node left, right;
    private int n;

    public Node(Key key, Value val, int n)
    {this.key = key; this.val = val; this.n = n;}
}
```
We don't need to keep an instance variable of `n` but we'd need it to support some features in our BST symbol table such as `rank`, `ceiling`, `floor`, etc. 

## BST Symbol Table

We mentioned that all operations with BST implementation takes log time because all the comparisons will generally go down the "tree", which means the max number of compares should be proportional to the depth of the tree. In an average scenario, the tree is somewhat balanced, meaning each node has similar number of left and right nodes. In this case, the height of the tree is simply `lgN`. However, if we insert key-value pairs in an ordered fashion, we will get a spindly tree that has a height of "N", which becomes our worst case for insert and search.

To solve this problem, we can introduce even more advanced tree structures that guarantees the worst case scenario doesn't happen. Stay tuned.
