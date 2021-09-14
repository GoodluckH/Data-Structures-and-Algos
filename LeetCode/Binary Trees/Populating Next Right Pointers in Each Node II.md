# Populating Next Right Pointers in Each Node II

https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/


## Description
Given a binary tree
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

## Approach
For recursive solution, we need to have a helper method that travels horizontally to get the next node. And as for iterative solution, treat this as a linked list problem where in we need to have a sentinel node set up at each level, then connect the nodes whenever possible.

## Implementation
Recursive solution
```java
public Node connect(Node root) {
  if (root == null) return root;
  
  if (root.left != null) {
    if (root.right != null)  root.left.next = root.right;
    else root.left.next = find(root);
  }
  
  if (root.right != null) {
    root.right.next = find(root);
  }
  
  connect(root.right);
  connect(root.left);
  return root;
}

private Node find(Node root) {
  while (root != null) {
    root = root.next;
    if (root.left != null) return root.left;
    if (root.right != null) return root.right;
  }
  return null;
}
```

Iterative solution:
```java
public Node connect(Node root) {
  if (root == null) return null;
  
  Node sentinel = new Node(-1), node = root;
  
  while (node != null) {
    Node cur = sentinel;
    while (node != null) {
     if (node.left != null) {cur.next = node.left; cur = cur.next;}
     if (node.right != null) {cur.next = node.right; cur = cur.next;}
     node = node.next;
    }
    node = sentinel.next;
    cur.next = null;
  }
  return root;
}
```

## Complexity Anlysis
Since we are visiting all nodes, our runtime would be linear, or O(N). As for space, we are constant excluding the recursion call stacks.
