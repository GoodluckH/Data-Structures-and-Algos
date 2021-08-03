# Intersection of Two Linked Lists

https://leetcode.com/problems/intersection-of-two-linked-lists/

## Description

Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

## Approach

Since two linked lists are mostly of the different length, we need to compute the length of each of them and to determine where to start so that both pointers can get to the intersection together (or hit null).

However, there's a more ingenuous solution. We see that the length of A is a + c, and B is b + c. So, when one pointer of an LL has reached the end, we can switch that pointer to the head of the other LL, and vice versa. This way, a + c + b + c = b + c + a + c, two pointers will eventually meet at some node, or at null.

## Implementation
```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode A = headA, B = headB;
    while (A != B) {
        A = A == null? headB: A.next;
        B = B == null? headA: B.next;
    }   
    return A; 
}
```

## Complexity Analysis

It's a O(M+N) algorithm for `m` is the shorter LL. Like what we analyzed before, we are essentially traveling a + b + c length, so it's linear. But for some case, there might be a huge constant. The space is constant because we only have two pointers.