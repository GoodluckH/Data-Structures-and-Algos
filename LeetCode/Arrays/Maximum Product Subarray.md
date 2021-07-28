# Maximum Product Subarray

Link to the problem: https://leetcode.com/problems/maximum-product-subarray/

## Description
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

It is guaranteed that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

## Approach

This problem is an extention on the Maximum Sum Subarray problem, in which we can solve it with Kadane's algorithm. However, the tricky thing about this problem is the existence of negative numbers, which makes multiplication trickier since two negative numbers yield positive result. Also, we need to take care of any 0 elements. The solution is not that complicated, though. 

We need to have two variables tracking the max so far as well as the min so far. We do this because whenever we encounter a negative number, we should swap max with min since any big number multiplied by a negative number will be smaller than a small number multiplied by the negative number. Then, we just apply Kadane's algo to both variables accordingly, and update our result variable.

## Implementation
```java
public int maxProduct(int[] nums) {
    int max = Integer.MIN_VALUE, imax = 1, imin = 1; // we set them to 1 because we are multiplying things
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] < 0) {int t = imax; imax = imin; imin = t;}
        imax = Math.max(nums[i], imax * nums[i]);
        imin = Math.min(nums[i], imin * nums[i]);
        max = Math.max(imax, max);
    }
    return max;
}
```

## Complexity Analysis

Immediately from the code, this is a linear solution with constant space requirements.