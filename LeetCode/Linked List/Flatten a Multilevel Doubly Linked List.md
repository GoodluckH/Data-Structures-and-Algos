# Flatten a Multilevel Doubly Linked List

https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

## Description
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

## Approach 
Whenever we encounter a node with a child node, we use a `temp` pointer to find the last node of that child LL, then, we "insert" the whole child node to where we at. And proceed with the same operation.

## Implementation
```java
public Node flatten(Node head) {
    if (head == null) return head;
    Node p = head;

    while ( p != null) {
        if (p.child == null) {p = p.next; continue;}
        Node temp = p.child;
        while (temp.next != null) temp = temp.next;
        temp.next = p.next;
        if (p.next != null) p.next.prev = temp;
        p.next = p.child;
        p.child.prev = p;
        p.child = null;
    }
    return head;
}
```

## Complexity Analysis

The algorithm runs in proportion to the number of nodes in the input but uses constant space.