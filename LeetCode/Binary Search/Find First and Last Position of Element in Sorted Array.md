# Find First and Last Position of Element in Sorted Array 

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


## Description
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

## Approach 
We would want to run two binary searches. The first to find the beginning index of the target, and the second search to find the ending inddex of the target. 

When running the first binary search, we can observe that if `mid`th element is larger than or equals to target, that means we need to search to the left by setting `j` to mid. Otherwise, we set `i` to mid + 1.

The converse holds true for find the ending index. However, we need to modify our `mid` computation to `(i + j) / 2 + 1` so that it's biased to the right. Doing so will avoid the never ending loop.

## Implementation

```java
public int[] searchRange(int[] nums, int target) {
  int i = 0, j = nums.length - 1, ans[] = new int[]{-1, -1};
  if (nums.length == 0) return ans;
  
  while (i < j) {
    int mid = (i + j) / 2;
    if (nums[mid] < target) i = mid + 1;
    else j = mid;
  }
  
  if (nums[i] != target) return ans;
  
  ans[0] = i;
  j = nums.length - 1;
  
  while (i < j) {
    int mid = (i + j) / 2 - 1;
    if (nums[mid] > target) j = mid + 1;
    else i = mid;
  }
  ans[1] = i;
  return ans; 
}
```

## Complexity Analysis
Immediately from the code, we can see our algorithm runs in O(LogN) time while the space requirement is constant.
