# Find Pivot Index

Link to the problem: https://leetcode.com/problems/find-pivot-index/

## Description
```
Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.
```

## Approach

Since we are dealing with sums, it is natually helpful to get the sum of the entire array, which has to be ran in linear time. Then, we can just have a variable tracking the sum of the left part of the pivot index to see if it matches with the right part.

## Implementation

```java
public int pivotIndex(int[] nums) {
    int sum = 0, left = 0;
    for (int n: nums) sum += n; 
    for (int i = 0; i < nums.length; i++) {
        if (left == sum-left-nums[i]) return i;
        left += nums[i];
    }
    return -1;
}
```

## Complexity Analysis

Immediately from the code, our runtime is linear and space requirement is constant.