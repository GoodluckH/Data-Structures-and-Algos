# Palingdrome Linked List

https://leetcode.com/problems/palindrome-linked-list/

## Description
Given the head of a singly linked list, return true if it is a palindrome.

## Approach
We reverse second half of the LL, then compare that with the first part, if all nodes match, then we know our answer. This works for odd numbers of nodes too, we just need to make sure that we advance the `slow` pointer to reverse the smaller half.

## Implementation
```java
public boolean isPalindrome(ListNode head) {
    ListNode slow = head, fast = head;

    //find the middle
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
    }
    if (fast != null) slow = slow.next;   // odd numbers of nodes
    slow = reverse(slow);
    fast = head;

    //check nodes
    while (slow != null) {
        if (fast.val != slow.val) return false;
        fast = fast.next;
        slow = slow.next;
    }
    return true;
}

ListNode reverse(ListNode head) {
    ListNode pre = null;
    while (head != null) {
        ListNode temp = head.next;
        head.next = pre;
        pre = head;
        head = temp;
    }
    return pre;
}
```

## Complexity Analysis
All operations are in linear time at worst. Our space requirement is constant.