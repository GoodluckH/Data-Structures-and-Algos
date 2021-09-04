# Path Sum

https://leetcode.com/problems/path-sum/

## Description
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

## Approach

This is a fairly straightforward problem that we can easily solve using recursion or iteration. For recursion method, we need to think about how to perform validity checks and what to pass to the return statement. Since we are trying to find the root-to-leaf solution, that means the ending node at which the accumulated value equates to the target must be a leaf with no children. 

Also, we are not abandoning the other branch when checking on this branch. So we should have an or-statement to check both branches (left and right).

For the iterative solution, we need to understand how stacks work with accumulated sum values. We need to have a separate stack for that instead of simply having a variable to keep track the sums so far.

## Implementation
Recursion:
```java
public boolean hasPathSum(TreeNode root, int targetSum) {
	if (root == null) return false;
	if (root.left == null && root.right == null) return targetSum == root.val;
	return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
}
```
Iteration:
```java
public boolean hasPathSum(TreeNode root, int targetSum) {
	if (root == null) return false;
	
	Stack<TreeNode> nodes = new Stack<>();
	Stack<Integer> sums = new Stack<>();
	
	nodes.push(root);
	sums.push(root.val);
	
	while (!nodes.isEmpty()) {
		TreeNode cur = nodes.pop();
		int curSum = sums.pop();
		
		if (cur == null) continue;
		if (cur.left == null && cur.right == null && curSum == targetSum) return true;
		
		if (cur.left != null) {
			nodes.push(cur.left);
			sums.push(curSum + cur.left.val);
		}
		
		if (cur.right != null) {
			nodes.push(cur.right);
			sums.push(curSum + cur.right.val);
		}
	}
	return false;
}
```

## Complexity Analysis

Since we are checking all nodes in the worst case, the runtime is O(N). As for space requirement, it's proportional to the height of the tree. 
