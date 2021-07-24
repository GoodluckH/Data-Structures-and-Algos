# Pascal's Triangle

Link to problem: https://leetcode.com/problems/pascals-triangle/

## Description
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

![description](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

```
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
```

## Approach 

We can observe that for each row, we put "1" at the beginning and the end of the row. For those in between, it's simply the sum of the (j-1)th and jth numbers from the previous row. Having this in mind, we can just write two for-loops to solve the problem.

## Implementation

```java
public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>(); 
    for (int i = 0; i < numRows; i++) {
        List<Integer> row = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            if (j == 0 || j == i) row.add(1);
            else if (i > 1) {
                int left = result.get(i-1).get(j-1);
                int right = result.get(i-1).get(j);
                row.add(left+right);
            }
        }
        result.add(row);
    }
    return result;
}
```

## Complexity Analysis
We are essentially filling out the area of a triangle, which is portionate to row * row * c, or row^2. This is our runtime complexity. And the space complexity is proportional to row^2 as well.