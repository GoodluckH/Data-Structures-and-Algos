# Product of Array Except Self

Link to the problem: https://leetcode.com/problems/product-of-array-except-self/

## Description
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.
 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

## Approach

The problem specifies that we have to do this in O(N) time without using division. An interesting observation is that each answer is the sum of the product of the left subarray and the product of the right subarray. For example, given an input array of [1, 2, 3, 4, 5], and for number 3, our output should be 40, which is the sum of 1*2 and 4*5. Now we can just compute, for each number, its left product sum, and its right product sum, we can just solve the problem in linear time.

How do we do this? We start by computing the left (or right, whichever you'd prefer) product sum for each element. To do this, we initiate a variable `temp` in the for-loop setup. And set `temp` to 1. For each element in our output array, we set it to be temp, then we update the temp value by multiplying it with the element in the input array corresponding to the same index position.

Then, we do the similar thing for the other way, except this time we are updating the output array elements, so we can't just assign temp to ans[i]. Rather, we multiply it with temp.

## Implementation
```java
public int[] productExceptSelf(int[] nums) {
    int[] ans = new int[nums.length]; 
    for (int i = 0, temp = 1; i < nums.length; i++) {
        ans[i] = temp;
        temp *= nums[i];
    }        
    for (int i = nums.length-1, temp = 1; i >= 0; i--) {
        ans[i] *= temp;
        temp *= nums[i];
    }
    return ans;
}
```

## Complexity Analysis
Linear runtime with linear space. 