# Invert Binary Tree
https://leetcode.com/problems/invert-binary-tree/

## Description
Given the root of a binary tree, invert the tree, and return its root.

## Approach
We can't just simply recursively assign right node to left node, and vice versa, because after all the recursive calls to left, the assignments to the left node would stay the same. Instead, think about how we swap elements in a list. We create a temporary variable to hold one of the element, then do the swapping. Same thing applies here, too.

## Implementation
Recursive solution:
```java
public TreeNode invertTree(TreeNode root) {
  if (root == null) return root;
  TreeNode left = root.left;
  root.left = invertTree(root.right);
  root.right = invertTree(left);
  return root;
}
```

Iterative solution: 
```java
public TreeNode invertTree(TreeNode root) {
  if (root == null) return root;
  Stack<TreeNode> stack = new Stack<>();
  stack.push(root);
  
  while (!stack.isEmpty()) {
    TreeNode node = stack.pop();
    TreeNode left = node.left;
    node.left = node.right;
    node.right = left;
    
    if (node.left != null) stack.push(node.left);
    if (node.right != null) stack.push(node.right);
  }
  return root;
}
```

## Complexity Analysis
The runtime is O(N) since we are visiting all nodes. The space complexity should be O(N) in the worst case when we have a skewed tree. 
