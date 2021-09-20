# Validate Binary Search Tree

https://leetcode.com/problems/validate-binary-search-tree/

## Description
Given the root of a binary tree, determine if it is a valid binary search tree (BST).
A valid BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
 ## Approach
 If we know how to do inorder traversal, then we can do the validation from a bottom up approach. Because intuitively we would want to recursively do this from top down, but the problem is that we will have to keep track of all the parent nodes to see if any child node violates the binary search invariants. Building from the bottom up, however, means when ever we pop a parent node, our `pre` pointer would still be pointing at the last child node, which serves as a check to satisfy the invariant.
 
 ## Implementation
 ```java
public boolean isValidBST(TreeNode root) {
  if (root == null) return true;
  Stack<TreeNode> stack = new Stack<>();
  TreeNode pre = null;
  while (root != null || !stack.isEmpty()) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
    root = stack.pop();
    while (pre != null && pre.val >= root.val) return false;
    pre = root;
    root = root.right;
  }
  return true;
}
 ```
 
 ## Complexity Analysis
 Since we are visiting all nodes in an inorder fashion, our runtime would be O(N). As for space, it's O(N) at worst when we have a spindly tree leaning left.
