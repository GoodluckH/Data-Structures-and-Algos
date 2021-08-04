# Merge Two Sorted Lists

https://leetcode.com/problems/merge-two-sorted-lists/

## Description
Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

## Approach
It's hardly any approach but just intuitively follow each LL. So, we can set up a `sentinel` node to have an origin, and from there, we can just merge two lists using different control flows.

## Implementation
```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode sentinel = new ListNode(), cur = sentinel;
    while (l1 != null && l2 != null) {
        if (l1.val >= l2.val) {
            cur.next = l2;
            l2 = l2.next;
        } else {
            cur.next = l1;
            l1 = l1.next;
        }
        cur = cur.next;
    }
    cur.next = l1 == null? l2: l1;
    return sentinel.next;    
}
```

## Complexity Analysis
Immediately from the code, this is a linear solution with constant space requirement.