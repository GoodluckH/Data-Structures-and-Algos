# Rotate Array

Link to the problem: https://leetcode.com/problems/rotate-array/

## Description
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

## Approach
Note that if we reverse the array, our input will become [7,6,5,4,3,2,1], so the first kth elements will be in their right place but reversed. So, all we need to do is to reverse the 0th to k - 1 th subarray as well as the rest of the array.

However, we cannot just set a stopper at k for our pointers, because for a small array like [1,2] but with a large k, like 45, we will be out of bound. So, we have to use the mathematical property for this. Note that we can get the pivot index by using k%n, where n is the array length. This will help us locate where the stopper should be. For small array and large k, this modulo operation will work as well.


## Implementation
```java
public void rotate(int[] nums, int k) {
    for (int i = 0, j = nums.length - 1; i < j; i++, j--) swap(nums, i, j);  
    for (int i = 0, j = (k % nums.length) - 1; i < j; i++, j--) swap(nums, i, j);
    for (int i = k % nums.length, j = nums.length - 1; i < j; i++, j--) swap(nums, i, j);
}

void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i++] = nums[j];
    nums[j--] = t;
}
```

## Complexity Analysis

Immediately from the code, this is a O(N) time and O(1) space algo.