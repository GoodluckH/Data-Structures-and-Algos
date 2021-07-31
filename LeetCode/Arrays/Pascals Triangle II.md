# Pascal's Triangle II

Link to the problem: https://leetcode.com/problems/pascals-triangle-ii/

## Description

Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]

## Approach 

My stupid approach was way to complicated as I was trying to get an in-place solution. Basically, I was noting the symmetry property of each row, so to update the new row, I only need to compute the first half of the array using the current array's second half, then mirror the elements to the second half to update entirely. 

Then for rows that have odd number of elements, I need to update the middle element.

Clearly, this is an error prone technique, and makes this easy level question way more complicated.

Instead, we can pursue another approach. For each new row, we can see that each element, except the first one, is the sum of the current element and the one before it. So for row [1,4,6,4,1] to [1,5,10,10,5,1], we can see this relationship very clearly. But we need to do the summing from the end to the front because otherwise, we will be summing the updated values. 

Since the problem is asking for kth row, then we know in Pascal's triangle, the kth row has k+1 elements, so we need to initiate an Integer array of such size, then fill it with 0s so we can do the computation.

## Implementation

```java
public List<Integer> getRow(int rowIndex) {
    Integer[] result = new Integer[rowIndex+1];
    Arrays.fill(result, 0);
    result[0] = 1;
    for (int i = 1; i <= rowIndex; i++)
        for (int j = i; j > 0; j--)         // since we are looping from the end, 1 will always be at the end.
            result[j] += result[j-1];
    return Arrays.asList(result);
    }
```

## Complexity Analysis

This is an O(N^2) runtime algo with space requirement proporational to rowIndex.