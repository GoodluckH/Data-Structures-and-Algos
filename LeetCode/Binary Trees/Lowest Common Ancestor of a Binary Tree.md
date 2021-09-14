# Lowest Common Ancestor of a Binary Tree

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


## Description
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

## Approach
For the recusion, it's quite intuitive. We just need to set the base case right, then we just iterativley run the algorithm on left and right. The tricky part is the return statement. Think about this way: if one child node is null after all the travesal, then we can comfortably return the right child node because it's imposible to find our answer in the left child node. If both are null, then that means the root of both child nodes is the answer.

The iterative approach, however, is much trickier. A straightforward way of doing this is to get every node's parent node saved somewhere in a hash map until we have tracked all the nodes to p and q. Then, we can basically travel upward, or 'back tracking', one of the node all the way to root, and record all the parent nodes in a set. Then, we back track the other node. As soon as we meet a node that's in our set, we are done.

## Implementation
Recursive solution:
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  if (root == null || root == p || root == q) return root;
  
  TreeNode left = lowestCommonAncestor(root.left, p, q);
  TreeNode right = lowestCommonAncestor(root.right, p, q);
  return left == null? right: right == null? left: root;
}
```

Iterative solution:
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  Stack<TreeNode> stack = new Stack<>(); 
  Map<TreeNode, TreeNode> parent = new HashMap<>();
  
  stack.push(root);
  parent.put(root, null);
  
  while (!parent.containsKey(p) || !parent.containsKey(q)) {
    TreeNode node = stack.pop();
    if (node.left != null) {
      stack.push(node.left);
      parent.put(node.left, node);
    }
    if (node.right != null) {
      stack.push(node.right);
      parent.put(node.right, node);
    }
  }
  
  Set<TreeNode> set = new HashSet<>();
  
  while (p != null) {
    set.add(p);
    p = parent.get(p);
  }
  
  while (!set.contains(q)) q = parent.get(q);
  return q;
}
```

## Complexity Analysis 
For both approaches, we are running O(N) in the worst case. As for space complexity, it's O(N) for the worst case too.
