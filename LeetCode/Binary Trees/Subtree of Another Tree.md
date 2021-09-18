# Subtree of Another Tree

https://leetcode.com/problems/subtree-of-another-tree/

## Description
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

## Approach
We are basically running the [same tree](https://github.com/GoodluckH/learn/blob/main/LeetCode/Binary%20Trees/Same%20Tree.md) problem on each node.

## Implementation
```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
  if (root == null) return root == subRoot;
  if (comp(root, subRoot)) return true;
  return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
}

boolean comp(TreeNode p, TreeNode q) {
  if (p == null && q == null) return true;
  if (p == null || q == null || p.val != q.val) return false;
  return comp(p.left, q.left) && comp(p.right, q.right);
}
```

## Complexity Analysis

Since we are comparing on each node against subRoot, our runtime at worst is O(N * M) for M is the number of nodes in subRoot. For space complexity, it's going to be O(N * M) as well in the implict stack because we are potentially recursivelly calling all nodes in root, and with each node, we are recursively calling `comp` method which at worst has a space complexity of O(M). 
