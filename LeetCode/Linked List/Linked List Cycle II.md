# Linked List Cycle II

https://leetcode.com/problems/linked-list-cycle-ii/

## Description
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Notice that you should not modify the linked list.

## Approach

Similar to the previous version of this problem, we need to first find out the point where our fast pointer meets the slow pointer, then, we need to exploit the fact that the distance between head and where the cycle begins is the same as where the two pointers meet to where the cycle begins. Using these pieces of knowledge, the implementation is simple and straightforward.

## Implementation
```java
public ListNode detectCycle(ListNode head) {
    if (head == null) return null;
    ListNode meet = meet(head);
    if (meet == null) return null;
    ListNode start = head;
    while (start != meet) {
        meet = meet.next;
        start = start.next;
    }
    return start;
}

ListNode meet(ListNode head) {
    ListNode slow = head, fast = head; 
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return slow;
    }
    return null;
}
```

## Complexity Analysis
Our space requirement is constant as we are just using pointers. For the runtime, we are looping through the LL for most of our operations, so the runtime is linear.