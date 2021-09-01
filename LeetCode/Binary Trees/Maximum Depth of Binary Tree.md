# Maximum Depth of Binary Tree

https://leetcode.com/problems/maximum-depth-of-binary-tree/

## Description
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Approach
Recursion or iteration. There's really not much to say here.

## Implementation
```java
// Recursive Solution

public int maxDepth(TreeNode root) {
	if (root == null) return 0; 
	return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

// Iterative Solution
public int maxDepth(TreeNode root) {
	if (root == null) return 0;
	int max = 1;

	Stack<TreeNode> nodes = new Stack<>();
	Stack<Integer> depths = new Stack<>();

	nodes.push(root);
	depths.push(1);

	while (!nodes.isEmpty()) {
		TreeNode cur =  nodes.pop();
		int depth = depths.pop();
		
		if (cur.right == null && cur.left == null) max = Math.max(max, depth);
		if (cur.right != null) {nodes.push(cur.right); depths.push(depth + 1);}
		if (cur.left != null) {nodes.push(cur.left); depths.push(depth + 1);}
	}
	return max;
}
```

## Complexity Analysis
For runtime, it's O(N) because we are visiting every node once. The space complexity is O(H) where H is the height of the tree.
