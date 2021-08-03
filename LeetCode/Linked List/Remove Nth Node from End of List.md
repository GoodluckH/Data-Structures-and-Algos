# Remove Nth Node From End of List

https://leetcode.com/problems/remove-nth-node-from-end-of-list/

## Description
Given the head of a linked list, remove the nth node from the end of the list and return its head

## Approach
Since we are delete nth node from the end, and our linked list is unidirection, that means we need two pointers to tell us when the end has been reached. The crux is to have two pointers maintain n-nodes in between, so that we know what to delete.

A corner case is when we are deleting the first node, so it's impossible to have n-nodes in between two pointers before the fast pointer reaches null zone. In this case, we can simply return `head.next`. 

## Implementaion

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode slow = head, fast = head;
    while (fast != null) {
        fast = fast.next;
        if (n-- < 0) slow = slow.next;
    }
    if (n >= 0) return head.next;
    slow.next = slow.next.next;
    return head;
}
```

## Complexity Analysis

Since we are passing through the LL just once, it's obvious that our runtime is linear and our space is constant because we only have two pointers.