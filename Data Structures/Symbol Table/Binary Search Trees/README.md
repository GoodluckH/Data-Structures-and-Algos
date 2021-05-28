# Binary Search Tree Symbol Table  

Implementing an ST using Linked List can be inefficient as it takes linear time for basically all operations.For example, the runtime is linear when inserting a new key-value pair because we need to scan the existing linked list to see if the key being inserted already exists. And when it comes to search, it runs on average at `N/2` because we still need to go through the linked list one by one to compare keys.

Although the search operation can be improved to `lgN` using two arrays lists where we keep them sorted when inserting, the inserting operation would still be linear because it's similar to how Insertion Sort works.

Therefore, introducing a more abstract data structure helps. With a Binary Search Tree (BST) we can achieve logarithemic performance for all operations.

# The BST Data Structure

Similar to a Linked List, we need to define our BST using a private class:

```java

```
