# Odd Even Linked List

https://leetcode.com/problems/odd-even-linked-list/

## Description
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

## Approach

I don't understand why this is a medium level question, it appeared very simple to me and was able to solve it at once. So, basically, we just need to link all odd-index nodes and even-index nodes, so naturally we'd need two pointers. But we also need to link the end of `odd` pointer to where our even nodes begins. So, we can just have another variable pointing to where the even-indexed nodes begins.

## Implementation
```java
public ListNode oddEvenList(ListNode head) {
  if (head == null || head.next == null) return head;
  ListNode odd = head, even = head.next, evenBegin = head.next;
  while (odd.next != null && even.next != null) {
      odd.next = odd.next.next;
      even.next = even.next.next;
      odd = odd.next;
      even = even.next;
  }  
  odd.next = evenBegin;
  return head;
}
```

## Complexity Analysis
Immediately from the code, this is a O(n) runtime and O(1) space algo.