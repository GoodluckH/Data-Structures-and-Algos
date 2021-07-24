# Spiral Matrix

Link to problem: https://leetcode.com/problems/spiral-matrix/

## Description
```
Given an m x n matrix, return all elements of the matrix in spiral order.

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
```

## Approach 

The spiral traversal is basically traversing the top, right, bottom, and left borders each time. But after one round-trip, we need to add "padding" to our borders so that we can loop through the inner matrix. The code is very simple and straightforward.

## Implementation

```java
public List<Integer> spiralOrder(int[][] matrix) {
    int padding = 0,
     r = matrix.length, 
     c = matrix[0].length, 
     n = r*c, 
     i = 0, 
     j = 0;
    List<Integer> result = new ArrayList<>();

    while (result.size() != n) {
        for (j = padding; j < c - padding; j++) //top
            if (result.size() != n) result.add(matrix[i][j]);
        j--; // offset j by one
        
        for (i = i + 1; i < r - padding; i++) //right
            if (result.size() != n) result.add(matrix[i][j]);
        i--;

        for (j = c - padding - 2; j >= padding; j--) //bottom
            if (result.size() != n) result.add(matrix[i][j]);
        j++;

        for (i = i - 1; i >= padding + 1; i--)  // left
            if (result.size() != n) result.add(matrix[i][j]);
        i++;
        
        j++; // start with a new matrix
        padding++;
    }

    return result;
    }
```

## Complexity Analysis
Immediately from the code, we are looping all elements with an extra list to store the result. The space and runtime complexity are both O(N).