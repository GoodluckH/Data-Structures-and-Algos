# Serialize and Deserialize Binary Tree

https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

## Description
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

## Approach
This problem is actually very simple if you understand how pre-order traversal works. Why pre-order works? Well, pre-order starts with the parent node itself, so we can be sure that we can construct the root or parent node easily.

So, for encoding the tree, we can just walk through the tree using pre-order traversal, and denote null trees using some symbols. To decode the tree, we can just take the data and, again, use pre-order traversal to construct the tree.

## Implementation
```java
public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X"; 
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      encode(root, sb);
      return sb.toString();
    }
    
    private void encode(TreeNode root, StringBuilder sb) {
      if (root == null) {
        sb.append(NN).append(spliter);
      } else {
        sb.append(root.val).append(spliter);
        encode(root.left, sb);
        encode(root.right, sb);
      }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      Queue<String> nodes = new LinkedList<>(); 
      nodes.addAll(Arrays.asList(data.split(spliter)));
      return buildTree(nodes);
    }
    
    private TreeNode buildTree(Queue<String> nodes) {
      String val = nodes.remove();
      if (val.equals(NN)) return null;
      TreeNode root = new TreeNode(Integer.parseInt(val));
      root.left = buildTree(nodes);
      root.right = buildTree(nodes);
      return root;
      }
}

```

## Complexity Analysis
For encoding the tree, since we are traversing every nodes, so the runtime is O(N), and our space complexity is O(N) given by the `StringBuilder` as well as the implicit call stack resulting from the recursion.

For decoding the tree, since we constructing each node, then the runtime is O(N), and our space complexity is O(N) given by the `Queue` as well as the recursion call stack.

So, over all, this is a linear algo.
