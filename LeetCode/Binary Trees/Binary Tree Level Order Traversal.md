# Binary Tree Level Order Traversal

https://leetcode.com/problems/binary-tree-level-order-traversal/

## Description
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

## Approach
We can collect all the nodes on a certain level into a queue, then push that queue to a stack that represents all levels in a tree.

## Implementation
```java
public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> ans = new ArrayList<>();
  if (root == null) return ans;
  
  Stack<Queue<TreeNode>> queues = new Stack<>();
  queues.push(new LinkedList<TreeNode>(Arrays.asList(root)));
  
  while (!queues.isEmpty()) {
    Queue<TreeNode> queue = new LinkedList<>(); 
    List<Integer> levelNodes = new ArrayList<>();
    for (TreeNode node: queues.pop()) {
      levelNodes.add(node.val);
      if (node.left != null) queue.add(node.left);
      if (node.right != null) queue.add(node.right);
     }
     
    ans.add(levelNodes);
    if (!queue.isEmpty()) queues.push(queue); 
  }
  
  return ans;
}
```

## Complexity Analysis
For runtime, we are running the for-loop to collect nodes on a certain level of the tree, so let N be the average number of nodes at any level of the tree, and L be the total number of levels of the tree. Then, our runtime would be O(L*N).

As for space requirement, it's proportional to our return data strucuture, which is (N*L) too.
