# Same Tree

https://leetcode.com/problems/same-tree/


## Description 
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

## Approach

This problem is not hard but it's tricky to set up base cases for recursive solutions. As for iterative solution, we can just use one stack to push nodes in p and q. But whenever after we pushed left nodes, we need to check if the stack size is divisible by 2, otherwise that means one of the left nodes is null, therefore we need to return false.

## Implementation
Recursive solution
```java
public boolean isSameTree(TreeNode p, TreeNode q) {
  if (p == null && q == null) return true;
  if (p == null || q == null || p.val != q.val) return false;
  return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

Iterative solution
```java
public boolean isSameTree(TreeNode p, TreeNode q) {
  if (p == null && q == null) return true;
  if (p == null || q == null) return false;
  
  Stack<TreeNode> stack = new Stack<>();
  
  stack.push(p);
  stack.push(q);
  
  while (!stack.isEmpty()) {
    TreeNode pp = stack.pop(), qq = stack.pop();
    if (pp.val != qq.val) return false;
    
    if (pp.left != null) stack.push(pp.left);
    if (qq.left != null) stack.push(qq.left);
    if (stack.size() % 2 != 0) return false;

    if (pp.right != null) stack.push(pp.right);
    if (qq.right != null) stack.push(qq.right);
    if (stack.size() % 2 != 0) return false;
  }
  return true;
}
```

## Complexity Analysis

Since we are visiting all nodes in both p and q, our runtime therefore is O(p+q). Similarly, our stack size is proportional to the sum of p and q's sizes, so O(p+q).
