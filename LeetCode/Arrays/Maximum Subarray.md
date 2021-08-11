# Maximum Subarray

Link to the problem: https://leetcode.com/problems/maximum-subarray/

## Description
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

## Approach

Apparently, there's something called Kadane's algorithm that we can use to solve this problem.

Kadane's algorithm is based on dynamic programming concept that basically works like this: for every element at i-th index, we need to see decide to update our local maximum value to either nums[i] or localMax + nums[i]. It's quite tempting at the beginning to update it to max(localMax, localMax+nums[i]). But it will produce wrong answers because we do have negative numbers in our array. Doing so will ignore those negative numbers, thereby overstating our result. 

We check nums[i] instead of localMax because say our localMax so far is -5, then the next element is -6, so, apparently, -6 > -11, so we need to reset our localMax to -6. This corresponds to setting an invisible pointer to where -6 is, so we are discarding the previous subarray.

## Implementation

```java
public int maxSubArray(int[] nums) {
    int localMax = 0, globalMax = Integer.MIN_VALUE;     // we cannot initiate localMax to MIN because if we add a negative number, it will turn to TMAX.
    for (int i = 0; i < nums.length; i++) {
        localMax = Math.max(localMax, localMax + nums[i]);
        if (localMax > globalMax) globalMax = localMax;
    }        
    return globalMax;
}
```

## Complexity Analysis
Immediately from the code, this is a linear solution in runtime and our space requirement is constant.