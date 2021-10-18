# Find Peak Element 

https://leetcode.com/problems/find-peak-element/

## Description
A peak element is an element that is strictly greater than its neighbors.
Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞.
You must write an algorithm that runs in O(log n) time. 

## Approach
We can do this problem both iteratively and recursively. What we are interested is whether the `mid` element is larger than the `mid + 1` element, if so, we can just search the portion of the array left of `mid`. Otherwise, we proceed to search the right side. In the end, we return the left pointer `i`. 

## Implementation
Recursive solution:
```java
public int findPeakElement(int[] nums) {
  return search(nums, 0, nums.length - 1);
}

int search(int[] nums, int i, int j) {
  if (i == j) return i;
  int mid = (i + j) / 2;
  if (nums[mid] > nums[mid + 1]) return search(nums, i, mid);
  else return search(nums, mid + 1, j);
}
```

Iterative solution:
```java
public int findPeakElement(int[] nums) {
  int i = 0, j = nums.length - 1;
  while (i < j) {
    int mid = (i + j) / 2;
    if (nums[mid] > nums[mid + 1]) j = mid;
    else i = mid + 1;
  }
  return i;
}
```

## Complexity Analysis

For our iterative solution, the runtime is O(LogN) because of the binary search, and the space requirement is constant. Same for the recursion except that we'd have an implicit call stack that has a size of O(LogN).
