# Remove Linked List Elements

https://leetcode.com/problems/remove-linked-list-elements/

## Description
Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

## Approach
There are many ways to solve this elegantly, including recursion. But recursion might be problematic if our input LL is large (stack overflow error). Instead, we can have two pointers, one is a sentinel node that points to our head, the other is a `curr` node that does the looping and deletion operations. We want a `sentinel` node because sometimes the element we are deleting is the head node it self.

## Implementation
```java
public ListNode removeElements(ListNode head, int val) {
    ListNode sentinel = new ListNode(-1, head), cur = sentinel;
    while (cur.next != null)  {
        if (cur.next.val == val) cur.next = cur.next.next;
        else cur = cur.next;
    }
    return sentinel.next;
}
```
## Runtime Complexity
This is a linear runtime as we start from the head all the way to the end of the LL. Our space requirement is constant as we only have two pointers.