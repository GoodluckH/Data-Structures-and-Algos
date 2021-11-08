# Pow(x, n) 

https://leetcode.com/problems/powx-n/

## Description
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

## Approach
We can use recursion to do this problem. Basically, we want to multiple `x` by `myPow()` whenever our `n` is odd, and every recursion call we half the `n`. This way, we are essentially running the algo in logarithmic time.

## Implementation
```java
public double myPow(double x, int n) {
  if (n == 0) return 1;
  if (n < 0) return 1/x * myPow(1/x, -(n+1));
  return (n & 1) == 0? myPow(x * x, n >> 1) : x * myPow(x * x, n >> 1);
}
```

## Complexity
Runtime and implicit stacks are both O(logN).
