# Container With Most Water

Link to the problem: https://leetcode.com/problems/container-with-most-water/

## Description
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

## Approach

Naively, I did a quadratic solution, which failed on time limit. Then, I don't know what happened, I figured out a linear solution using two pointers. So, basically, we need to have two pointers one from the beginning, then other from the end.

Then, we compute the current area and update our answer accordingly. Now, the most important concept is that, if one side is of a larger value than the other, then we update the pointer of the shorter side, because we want to find out if there's any longer side than the shorter side we have right now.

## Implementation
```java
public int maxArea(int[] height) {
    int i = 0, j = height.length - 1, area = 0;
    while (i < j) {
        int currArea = (j - i) * Math.min(height[i], height[j]);
        if (currArea > area) area = currArea;
        if (height[i] > height[j]) j--;
        else i++;
    }        
    return area;
}
```

## Complexity Analysis
Immediatley from the code, this is a linear-runtime and constant-space algorithm. 