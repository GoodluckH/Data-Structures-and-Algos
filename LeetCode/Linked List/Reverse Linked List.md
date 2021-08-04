# Reverse Linked List
https://leetcode.com/problems/reverse-linked-list/

## Description          
Given the head of a singly linked list, reverse the list, and return the reversed list.

## Approach

This is a quite classic problem. To begin, we need to have two pointers, one at null, another at head. So, as we loop through the LL, we just need to set the `curr.next` to `prev`, then update `curr` and `prev` accordingly, until we reach the end.

Even better, we can just use `head` as our `curr` pointer because we are returning `prev` anyways.

## Implementation
```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
    }   
    return prev;
}
```

## Complexity Analysis
Immediately from the code, this is a linear solution with constant space requirement.