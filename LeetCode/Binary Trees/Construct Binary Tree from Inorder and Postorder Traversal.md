# Construct Binary Tree from Inorder and Postorder Traversal

https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

## Description
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

## Approach

This is a quite difficult problem. But the key to understand this is that our postorder array contains all the "root" nodes if we count from the end. Then, our inorder array would give us a clue of the left subtree and the right subtree once we have located a root. 

The right subtree is easy to construct because we can just decrement our pointer for postorder, and for each root node, we can know what to build as we just keep decrementing the pointer for postorder until we've reached the end, evidence by the same value in postorder and inorder. 

However, it's a bit tricky to do the left subtree. We need to actually do this after constructing all the right tree so that we know of a "boundary" at each level when the recursion for postorder ends. For that purpose, we need to pass in an argument `end` to keep track of the boundary.

## Implementation
```java
int pInorder;
int pPostorder; 

private TreeNode helper(int[] inorder, int[] postorder, TreeNode end) {
  if (pPostorder < 0) return null;
  
  TreeNode root = new TreeNode(postorder[pPostorder--]); 
  if (inorder[pInorder] != root.val) root.right = helper(inorder, postorder, root);
  pInorder--;
  
  if (end == null || inorder[pInorder] != end.val) root.left = helper(inorder, postorder, end);
  return root;
}

public TreeNode buildTree(int[] inorder, int[] postorder) {
  pInorder = inorder.length - 1;
  pPostorder = postorder.length - 1;
  return helper(inorder, postorder, null);
}
```

## Complexity Analysis

Since we are going through every elements in the input array so the runtime is O(N), and our space requirement should be O(N), too, if the tree is skewed.
