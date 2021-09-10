# Construct Binary Tree from Preorder and Inorder Traversal

https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

## Description
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

## Approach
Very similar question to building the tree from postorder and inorder. We just need to use a stopper to do the magic. The code would be clean but the caveat is that things might not work if we have duplicate values in a tree. If that's the case, we will have to pass a treenode as a stopper rather than an integer.

## Implementation
```java
int in;
int pre;

private TreeNode build(int[] preorder, int[] inorder, int end) {
	if (pre >= preorder.length) return null;
	if (end == inorder[in]) {in++; return null;}
	
	TreeNode node = new TreeNode(preorder[pre++]);
	node.left = build(preorder, inorder, node.val);
	node.right = build(preorder, inorder, end);

	return node;
}
public TreeNode buildTree(int[] preorder, int[] inorder) {
	in = 0;
	pre = 0;
	return build(preorder, inorder, Integer.MIN_VALUE);
}
```

## Complexity Analysis

The runtime is O(N) beacuse we are visiting every node and the space is O(N) at worst due to recursion.
