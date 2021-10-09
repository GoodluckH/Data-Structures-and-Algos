# Sqrt(x)

https://leetcode.com/problems/sqrtx/

## Description
Given a non-negative integer x, compute and return the square root of x.
Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.


## Approach
Basically we use the regular binary search template, make sure to set the left boundary to 1 instead of 0. And since the input maybe large, we can't check conditions using mid * mid, because that might cause integer overflow. So, we have to use the transitive property to get around that.

## Implementation
```java
public int mySqrt(int x) {
  int i = 1, j = x;
  while (i <= j) {
    int mid = i + (j - i) / 2;
    if (mid == x/mid) return mid;
    else if (mid < x/mid) i = mid + 1;
    else j = mid - 1;
  }
  return j;
}
```

## Complexity Analysis

This is an O(LogN) runtime and O(1) space algorithm.
