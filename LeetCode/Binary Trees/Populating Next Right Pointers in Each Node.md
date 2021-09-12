# Populating Next Right Pointers in Each Node

https://leetcode.com/problems/populating-next-right-pointers-in-each-node/


## Description
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

## Approach

We can do this problem both iteratively and recursively. The crux is how to link the next pointer to another child. To do this, we need to check if the current node has a valid next node at all. If so, we are basically treating this as a linked list problem. 

To do this recursively, we need to just traverse each level where we link the current node's next pointer to either a null value or a valid node passed in as an argument.

The iteratively solution is straightforward, too. We just need to check if the current node has a valid next node. 

## Implementation
Recursive solution:
```java
public Node connect(Node root) {
	travel(root, null);
	return root;
}

private void travel(Node curr, Node next) {
	if (curr == null) return;
	curr.next = next;
	travel(curr.left, curr.right);
	travel(curr.right, curr.next == null? null: curr.next.left);
}
```

Iterative solution:
```java
public Node connect(Node root) {
	Node head = root;
	while (head != null && head.left != null) {
		Node curr = head;
		while (curr != null) {
			curr.left.next = curr.right;
			curr.right.next = curr.next == null? null: curr.next.left;
			curr = curr.next;
		}
		head = head.left;
	}
	return root;
}

```

## Complexity Analysis
This is an O(N) algorithm because we need to visit every node in the tree. As for space, it's constant (excluding the implicit stack for the recursive solution).
