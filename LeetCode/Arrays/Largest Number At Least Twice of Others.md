#  Largest Number At Least Twice of Others

Link to the problem: https://leetcode.com/problems/largest-number-at-least-twice-of-others/

## Description
```
You are given an integer array nums where the largest integer is unique.

Determine whether the largest element in the array is at least twice as much as every other number in the array. If it is, return the index of the largest element, or return -1 otherwise.
```

## Approach

We can keep track of the largest number and the second-largest number in the array and such computation can be done in one loop. Since the question is asking for an index number, we need to have another variable to keep track of index position of the largest number. Then, we can simply see if the second-largest number doubled is larger than the largest number.

## Implementation

```java
    public int dominantIndex(int[] nums) {
       int id = -1, max = -1, sec = -1;

       for (int i = 0; i < nums.length; i++) {
           if (nums[i]>max){
               sec = max;
               max = nums[i];
               id = i;
           }
           else if (nums[i] > sec) sec = nums[i];   // in case the second largest comes AFTER the largest
       }
       return sec*2 > max? -1: id;
    }
```

## Complexity Analysis
Since we are looping the array just once so our runtime is O(N), the space requirement is constant.