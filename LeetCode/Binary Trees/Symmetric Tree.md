# Symmetric Tree 

https://leetcode.com/problems/symmetric-tree/

## Description
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

## Approach
We can do this both recursively and iteratively. But first, we need to understand what symmetry means in this problem. It means that the outer children are of equality, so we can't just simply test if left child is equal to right child. For childs that have subchildren, we need to take care of the symmetry property by passing in arguments properly.

## Implementation
Recursive:
```java
public boolean isSymmetric(TreeNode root) {
	return root == null || helper(root.left, root.right);
}

boolean helper(TreeNode left, TreeNode right) {
	if (left == null || right == null) return left == right;
	else if (left.val != right.val) return false;
	return helper(left.left, right.right) && helper(left.right, right.left);
}
```

Iterative:
```java
public boolean isSymmetric(TreeNode root) {
	if (root == null) return true;
	Stack<TreeNode> stack = new Stack<>();
	
	stack.push(root.left);
	stack.push(root.right);
	
	while (!stack.isEmpty()) {
		TreeNode left = stack.pop();
		TreeNode right = stack.pop();
		
		if (left == null && right == null) continue;
		if (left == null || right == null || left.val != right.val) return false;
		
		stack.push(left.left);
		stack.push(right.right);
		
		stack.push(left.right);
		stack.push(right.left);
	}
	return true;
}

```

## Complexity Analysis
We are running propertional to the number of leaves, so it's O(N). As for space requirement, is proportional to the height of the tree, or O(H).
