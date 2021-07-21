# Two Sum II - Input array is sorted

Link to the problem: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

## Description
Given an array of integers `numbers` that is already ***sorted in non-decreasing order***, find two numbers such that they add up to a specific `target` number.

Return *the indices of the two numbers (**1-indexed**) as an integer array `answer` of size `2`, where `1 <= answer[0] < answer[1] <= numbers.length`.*

The tests are generated such that there is **exactly one solution**. You **may not** use the same element twice.

## Approach
It is good that the array is sorted, so natually we want to have two pointers for this approach. Also, it makes our life simpler since it's guaranteed that there's exactly one solution.

We can have two pointers `i` and `j` start at the beginning and the end of the array, respectively. Then, if `nums[i] + nums[j] < target`, we know that we need to increment the `i` pointer since the array is in non-descending order. Conversely, we need to decrement the `j` pointer. As soon as we get our target number, we can return the two pointers in an array, and don't forget to add 1 to each because the problem says the answer must be 1-indexed.

## Implementation

```java
public int twoSumII(int[] nums, int target) {
    int i = 0, j = nums.length-1;
    while (true) {
        if (nums[i]+nums[j] < target) i++;
        else if (nums[i]+nums[j] > target) j--;
        else return new int[]{i+1, j+1};
    }
}
```

## Complexity Analysis

Since we don't have any external data structures, our space complexity is constant. For runtime in the worst case, it's O(n), assuming we have to increment or decrement n-1 times to get to our target. 