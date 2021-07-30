# Search in Rotated Sorted Array

Link to the problem: https://leetcode.com/problems/search-in-rotated-sorted-array/

## Description
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4


## Approach
Apparently, the question wants us to solve it using binary search. However, the problem here is our sorted array is rotated, so we have to set up some conditional statements to take care of those cases. The first example is to look at the left part of the array, partitioned by `mid`. So if arr[lo] <= arr[mid], that means we have a sorted part. Then, we want to see if our target is within this subarray, if so, we update `hi`, otherwise, update `lo` to search the right part of the `mid`.

Conversely, if we don't have a sorted array from `lo` to `mid`, in the example of [5,1,2,3,4], then we need to check if a[j] >= target and a[mid] < target, if so, we update `i`, otherwise, we search the other half of the array.

## Implementation
```java
public int search(int[] nums, int target) {
    int lo = 0, hi = nums.length-1;
    while (lo < hi) {
        int mid = (lo + hi) / 2;
        if (nums[lo] <= nums[mid]) {
            if (nums[lo] <= target && nums[mid] >= target) hi = mid;
            else lo = mid + 1;
        } else {
            if (nums[hi] >= target && nums[mid] <= target) lo = mid;
            else hi = mid - 1;
        } 
    }     
    return nums[lo] == target? lo: -1;   
}
```

## Complexity Analysis
Since we are doing a binary search, the runtime is proportional to O(log N). The space requirements are constant.