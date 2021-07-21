# Minimum Size Subarray Sum

Link to the problem: https://leetcode.com/problems/minimum-size-subarray-sum/

## Description
```
Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
```

## Approach
I couldn't figure out a perfect solution by myself at the beginning. I only got a quadratic solution at the end. My brute force approach involves checking all subarrays can be formed starting at a certain letter, and try the same for every element in the array.

```java
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 1) return (nums[0] < target)? 0:1;
        
        int result = Integer.MAX_VALUE, sum = 0, i, j;
        
        for (i = 0; i < nums.length-1; i++){
            sum = nums[i];
             if (sum >= target) {result = 1;break;}
            for (j = i+1; j < nums.length; j++) {
                if (nums[j] >= target) {result = 1;break;}
                sum += nums[j]; 
                if (sum >= target) {result = Math.min(result, j-i+1);break;}
            }
        }
        
        return (result == Integer.MAX_VALUE)? 0: result;
    }
```

Then, I had to resort to solutions to find out a very ingenuous solution in linear time. Remarkably, the linear time is very similar to my setup.

### O(N) Approach

The linear time approach involves reducing the interval from the left side, which is something I never thought before. The theory behind it is that as long as I have an interval whose sum is `>= target`, we can then trim the left side until the sum is smaller than `target`. In this way, we have one pointer looping through every element, and another reducing the subarray. So in total, we only need to loop through the array once.

In the trimming process, we use a min function to update our `result`. 

## Implementation

```java
    public int minSubArrayLen(int target, int[] nums) {        
        int result = Integer.MAX_VALUE, sum = 0, j = 0;
        
        for (i = 0; i < nums.length; i++){
            sum += nums[i];
            while (sum >= target) {
                result = Math.min(result, i-j+1);   // now i is the end-of-subarray pointer
                sum -= nums[j++];
            }
        }
        
        return (result == Integer.MAX_VALUE)? 0: result;
    }
```
