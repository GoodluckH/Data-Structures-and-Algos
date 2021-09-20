# Kth Smallest Element in a BST 

https://leetcode.com/problems/kth-smallest-element-in-a-bst/

## Description
Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.

## Approach
If you know how inorder traversal works,then this problem is easy. With a valid binary search tree, inorder will always return things, well, in order. So, we just need to add some conditional checks to see if we have found the kth smallest element.

## Implementation
```java
public int kthSmallest(TreeNode root, int k) {
  Stack<TreeNode> stack = new Stack<>();
  while (root != null || !stack.isEmpty()) {
    while (root != null) {
    stack.push(root);
    root = root.left;
    }
    
    root = stack.pop();
    if (--k == 0) return root.val;
    root = root.right;
  }
  return root.val;
}
```

## Complexity Analysis
Inorder traversal takes O(N) in runtime and O(N) in space in the worst case.
