# Binary Search

https://leetcode.com/problems/binary-search/


## Description
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
You must write an algorithm with O(log n) runtime complexity.


## Approach

Use a pivot to determine whether there's a match. In this case, we need to set the loop condition as `i <= j` because there might be arrays of length 1.

## Implementation
```java
public int search(int[] nums, int target) {
  int i = 0, j = nums.length - 1;
  while (i <= j) {
    int mid = i + (j - i) / 2;
    if (nums[mid] == target) return mid;
    if (nums[mid] > target) j = mid - 1;
    else i = mid + 1;
  }
  return -1;
}
```

## Complexity Analysis

Since this is the classic binary search problem, the runtime would be O(LogN), and the space complexity is constant. 
