# Sort List

https://leetcode.com/problems/sort-list/

## Description
Given the head of a linked list, return the list after sorting it in ascending order.

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

## Approach
Clearly, the follow-up wants us to use mergesort to solve this problem as indicated by the runtime and space requirements.

So, we need to do a bottom-up mergesort by dividing our linked list by half, and repeat so until we are left with two one-node lists to do the merge. The merge operation is essentially the same thing as [Merge Two Sorted Lists problem](https://github.com/GoodluckH/learn/blob/main/LeetCode/Linked%20List/Merge%20Two%20Sorted%20Lists.md).

## Implementation
```java
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode pre = null, slow = head, fast = head;
    while (fast != null && fast.next != null) {
        pre = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    pre.next = null; // break the two linked lists
    
    ListNode left = sortList(head);
    ListNode right = sortList(slow);
    return merge(left, right);
}

ListNode merge(ListNode l1, ListNode l2) {
    ListNode sentinel = new ListNode(), cur = sentinel;
    while (l1 !=null && l2 != null) {
        if (l1.val < l2.val) {cur.next = l1; l1 = l1.next;}
        else {cur.next = l2; l2 = l2.next;}
        cur = cur.next;
    }
    cur.next = l1 == null? l2: l1;
    return sentinel.next;
}
```
