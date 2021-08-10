# Reorder List

https://leetcode.com/problems/reorder-list/

## Description

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

## Approach
If you draw this problem on a piece of paper, you will immediately unlock some insight. The most obvious pattern I found is that this problem involves finding the middle and do the reversal on the second half, which is interestingly similar to the [Panlindrome Linked List problem](https://github.com/GoodluckH/learn/blob/main/LeetCode/Linked%20List/Palindrome%20Linked%20List.md).

So, once we have reversed the second half, we just insert each node of the second half after each node of the first half.

## Implementation 

```java
public void reorderList(ListNode head) {
    if (head == null) return;
    
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {slow = slow.next; fast = fast.next.next;}
    if (fast != null) slow = slow.next; // IMPORTANT! deal with odd number of nodes

    slow = reverse(slow);
    fast = head;

    while (slow != null) {
        ListNode t = fast.next;
        fast.next = slow;
        slow = slow.next;
        fast = fast.next;
        fast.next = t;
        fast = fast.next;
    }
    fast.next = null;
} 

ListNode reverse(ListNode head) {
    ListNode pre = null, cur = head;
    while (cur != null) {
        ListNode t = cur.next;
        cur.next = pre;
        pre = cur;
        cur = t;
    }
    return pre;
}
 ```

 ## Complexity Analysis
 To find the middle and reverse the second half of the LL each takes linear time to run. The "stitching" operation takes linear time proportional to `slow` node. So runtime overral is linear. The space requirement is constant as we are modifying the input LL in-place. 