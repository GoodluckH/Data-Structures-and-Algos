# Linked List Cycle

https://leetcode.com/problems/linked-list-cycle/

## Description
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

## Approach

My first approach was to modify the node value to MIN as a marker, so that if we encounter a node that is of MIN value, we know there's a cycle. However, this only works in this particular problem because we are allowed to modify the value of those nodes, and the constraints tell us that none of the nodes will have MIN value.

Apparently, it's really testing our knowledge on using two pointers. We will have one pointer moving one node at a time, the other two at a time. This way, if there's cycle, they will eventually meet each other.

## Implementation
```java
public boolean hasCycle(ListNode head) {
   if (head == null) return false;
   ListNode slow = head, fast = head.next;
   while (fast != null && fast.next != null && slow != null) {
       if (slow == fast) return true;
       slow = slow.next;
       fast = fast.next.next;
   }     
   return false;
}
```

## Complexity Analysis
This is a linear algorithm because we are just looping through the linked list through two pointers. And this is a constant memory solution.