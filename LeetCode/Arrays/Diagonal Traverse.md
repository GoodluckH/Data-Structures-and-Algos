# Diagonal Traverse

Link to problem: https://leetcode.com/problems/diagonal-traverse/

## Description
```
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
```

## Approach

In the beginning, I thought this is a pretty simple problem because I had some experience dealing with 2D arrays. So naturally, I had a variable that indicates the direction, and basically figure out the edge cases for top, left, right, and bottom. But then, when I looked up the discussion, someone found out something more convenient.

We know our direction is going up if the sum of index i and j is even. Given this property, we just need to code the corner cases for either direction:
    ```
    if going up
        if it's on the right border, we increment i to the next row
        else if it's on the top border, we increment j to the next column
        else we decrement i and increment j
    else
        if it's on the bottom border, we increment j to the next column
        else if it's on the left border, we increment i to the next row
        else we increment i and decrement j
    ```

## Implementation
```java
public int[] findDiagonalOrder(int[][] mat) {
    int row = mat.length, col = mat[0].length, i=0, j=0;
    int[] result = new int[row * col];

    for (int r = 0; r < result.length; r++) {
        result[r] = mat[i][r];
        if ((i+j)%2 == 0) {  // going up
            if (j == col - 1) i++;
            else if (i == 0) j++;
            else {i--;j++;}
        }else{
            if (i == row - 1) j++;
            else if (j == 0) i++;
            else (i++; j--;)
        }
    }
    return result;
}
```

## Complexity Analysis

Immediately from the code, this is O(N) in runtime. Since we need an extra array to store the result, our space requirement is O(N) as well.