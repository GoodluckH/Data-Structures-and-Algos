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
int in;
int post;

TreeNode build(int[] inorder, int[] postorder, int end) {
    if (post < 0) return null;
    if (end == inorder[in]) {in--; return null;}

    TreeNode node = new TreeNode(postorder[post--]);
    node.right = build(inorder, postorder, node.val);
    node.left = build(inorder, postorder, end);

    return node;
}

public TreeNode buildTree(int[] inorder, int[] postorder) {
    in = inorder.length - 1;
    post = postorder.length - 1;
    return build(inorder, postorder, Integer.MIN_VALUE);
}
```

## Complexity Analysis

Since we are going through every elements in the input array so the runtime is O(N), and our space requirement should be O(N), too, if the tree is skewed.
