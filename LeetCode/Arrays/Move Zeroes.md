# Move Zeroes

Link to the problem: https://leetcode.com/problems/move-zeroes/

## Description

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]

## Approach

At the beginning, I was thinking that if I encounter a non-zero integer, I will move it to the left end of the array until it meets another non-zero element. It's kind of like how insertion sort works. However, if we have an array like [0, 0, ...0, 1], we'd unnecessarily write the array too n-1 times. 

Instead, we can simply maintain two pointers, one is the normal looping pointer, the other keeps track of where we should put the next non-zero element. In this way, we can just read the array n times and dramatically reduce the write operation. That is, we are doing a long-distance swapping, so array write per swapping is only 2.


## Implementation
```java
public void moveZeroes(int[] nums) {
    int nonZeroPos = 0;
    for (int i = 0; i <nums.length; i++)
        if (nums[i] != 0) swap(nums, nonZeroPos++, i);
}

void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = nums[i];
}
```

## Complexity Analysis
Space is constant because we are modifying the input array in-place. The runtime is linear because of the whole operation is dominated by the for-loop.