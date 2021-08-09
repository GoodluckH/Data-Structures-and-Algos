# Merge K Sorted Lists

https://leetcode.com/problems/merge-k-sorted-lists/

## Description

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

## Approach

**Brute force:** so I started out by running [Merge Two Sorted Lists](https://github.com/GoodluckH/learn/blob/main/LeetCode/Linked%20List/Merge%20Two%20Sorted%20Lists.md) algorithm on each one while updating the result of the previously merged two. While this worked, it had a runtime of `kN` where k is the number of linked lists in the input array, and N is the total number of nodes.

**Priority Queue:** we can use a PQ to dramatically improve our runtime at the expense of maintaining an external data structure proportional to `k`, although `k` is usually much smaller than `N`. We just keep add nodes to the PQ and "polling" from the PQ to construct our result.

**Divide and Conquer:** So, we can essentially just use a modified [mergesort](https://github.com/GoodluckH/learn/tree/main/Computer%20Science/Data%20Structures%20and%20Algorithms/Sorting%20Algorithms/Merge%20Sort) to solve this. Basically, we partition our input array all the way till there are two left, then we run the Merge Two Sorted Lists algo on the two; then, our recursive stack will do the mergesort for another two just sorted linked lists, and so on.

## Implementation
**Brute Force:**
```java
public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    ListNode ans = lists[0];
    for (int i = 1; i < lists.length; i++) ans = merge(ans, lists[i]);
    return ans;
}

ListNode merge(ListNode l1, ListNode l2) {
    ListNode sentinel = new ListNode(), cur = sentinel; 
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {cur.next = l1; l1 = l1.next;}
        else {cur.next = l2; l2 = l2.next;}
        cur = cur.next;
    }
    cur.next = l1 == null? l2: l1;
    return sentinel.next;
}
```

**Priority Queue:**
```java
public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
    for (ListNode node: lists) if (node != null) pq.add(node);
    ListNode sentinel = new ListNode(), cur = sentinel; 
    while (!pq.isEmpty()) {
        cur.next = pq.poll();
        cur = cur.next;
        if (cur.next != null) pq.add(cur.next);   // we start with cur = null so need to advance one more
    }
    return sentinel.next;
}
```

**Divide and Conquer:**
```java
public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    return partition(lists, 0, lists.length - 1);
}

ListNode partition(ListNode[] lists, int i, int j) {
    if (i > j) return null;
    if (i == j) return lists[i];
    int mid = (i + j) / 2;
    ListNode left = partition(lists, i, mid);
    ListNode right = partition(lists, mid + 1, j);
    return merge(left, right);
}

ListNode merge(ListNode l1, ListNode l2) {
    ListNode sentinel = new ListNode(), cur = sentinel; 
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {cur.next = l1; l1 = l1.next;}
        else {cur.next = l2; l2 = l2.next;}
        cur = cur.next;
    }
    cur.next = l1 == null? l2: l1;
    return sentinel.next;
}
```

## Complexity Analysis

**Brute force:** as discussed earlier, we have a kN runtime but a constant space requirement. 

**Priority Queue:** adding things and pollings from a PQ will take klogk time due to sink and swim operations. And we are performing these operations on N nodes, so the overall runtime is Nlogk. The space is k.

**Divide and Conquer:** It takes Nlogk time (logN because halfing the input through recursion) and logk space because of the recursion call stack.
