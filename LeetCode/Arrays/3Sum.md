# 3Sum

Link to the problem: https://leetcode.com/problems/3sum/

## Description

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

## Approach

Apparently, we've been told repeated that we need to reduce n-Sum problems to a 2-sum problem. But since our runtime will be quadratic anyways, we might as well just sort the array so that we can cap our space requirement to just the output array.

Basically, for each element from 0 to n - 2 index positions, we run the modified 2-sum on the rest of the array.

## Implementation

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    if (nums.length == 0 || nums[0] > 0) return ans; //optimization

    for (int i = 0; i < nums.length - 2; i++) {
        int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
        if (i == 0 || i > 0 && nums[i] != nums[i-1]) {
            while (lo < hi) {
                if (nums[lo] + nums[hi] == sum) {
                    ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo + 1] == nums[lo]) lo++;
                    while (lo < hi && nums[hi - 1] == nums[hi]) hi--;
                    lo++; hi--;
                } else if (nums[lo] + nums[hi] > sum) hi--;
                else lo++;
            }
        }
    }
    return ans;
}
```
## Complexity Analysis

The sorting algo takes linearithmic time, the for-loop is linear time but for each element, we are accessing possibly all elements in the rest of the array. So, the overall runtime is O(N^2). As for space requirement, except the O(N) output data structure, we have a constant requirement. 