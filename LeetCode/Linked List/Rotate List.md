# Rotate List
https://leetcode.com/problems/rotate-list/

## Description
Given the head of a linked list, rotate the list to the right by k places.

## Approach

This problem is quite similar to the [rotation problem](https://github.com/GoodluckH/learn/blob/main/LeetCode/Arrays/Rotate%20Array.md) on an array. The crux of the problem is when we have a `k` that is larger than the length of the array. We need to "wrap" the extra length around to figure out a stopping point.

Unlike the array rotation where we reverse the array first, it's not necesary to do that for a linked list because we have pointers around to move things easily. But our `k` won't have the same computation as for the array rotation. Here, without reversal, our `k` should be `len - k % len`.

Then the rotation is quite straight forward, we need a `fast` pointer than indicates the end of the LL, and a `slow` pointer shows where to break off. Then, we set `fast.next` to head, and set `head` to `slow.next`, and finally set `slow.next` to null.

## Implementation
```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return head;
    ListNode slow = head, fast = head;

    // get length
    int len = 1;
    while (fast.next != null) {fast = fast.next; len++;}

    // wrap around the k
    k = len - k % len;

    // rotation
    while (k-- > 1) slow = slow.next;
    fast.next = head;
    head = slow.next;
    slow.next = null;
    
    return head;
}
```

## Complexity Analysis
Immediately from the code, we are running in linear time. And our space complexity is constant because we only used pointers.