# Find K Closest Elements 
https://leetcode.com/problems/find-k-closest-elements/

## Description
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:
|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

## Approach
So, we basically want to find a window of size 'k' that fits 'x' right in the middle. To do this, we can think of a binary search on the window's mid point.

To visualize, we have

```
=======arr[mid]----------x---arr[mid + k]===
=======arr[mid]-------------arr[mid + k]=x==
In these two cases, we can see we need to slide our window to the right. 
```

```
=======x==arr[mid]-----------arr[mid + k]===
=======arr[mid]--x----------arr[mid + k]====
In these two cases, we need to slide to the left.
```

So, it's quite simple algorithm once you understand the sliding window concept and how to set up the right `right` pointer.

## Implementation
```java
public List<Integer> findClosestElements(int[] arr, int k, int x) {
  int i = 0, j = arr.length - k; // need to do this to avoid out-of-bound errors
  while (i < j) {
    int mid = (i + j) / 2;
    if (x - arr[mid] > arr[mid + k] - x) i = mid + 1;
    else j = mid;
  }
  return Arrays.stream(arr, i, i + k).boxed().collect(Collectors.toList());
}
```

## Complexity Analysis
For runtime, it's O(N - k), and for space, it's O(k).
