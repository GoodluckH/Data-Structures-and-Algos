# Add Binary

Link to the problem: https://leetcode.com/problems/add-binary/

## Description

Given two binary strings a and b, return their sum as a binary string.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

## Approach

Very naively, I was looping from the end of both arrays, and maintain a boolean variable that indicates whether there's carry-over. However, I had to set up so many conditional statements, which is quite in effective and ugly to look at. 

It appears that for binary problems, we can just exploit the number 2. If there's a carry-over, then that means the sum must be 2, so 2/2 = 1, that will tell us that there's a carry-over. And we can just store that value to another variable called 'sum'.


## Implementation

```java
public String addBinary(String a, String b) {
    int i = a.length() - 1, j = b.length() - 1;
    int carry = 0;

    StringBuilder result = new StringBuilder();

    while (i >= 0 && j >= 0) {
        int sum = carry;
        if (i >= 0) sum += a.charAt(i--) - '0';
        if (j >= 0) sum += b.charAt(j--) - '0';
        result.append(sum%2); 
        carry = sum/2;
    }
    if (carry == 1) result.append("1");
    return result.reverse.toString();
}

```

## Complexity Analysis
Immediately from the code, we are O(N) proportional and use an external data structure `StringBuilder` to store the result, which is O(N).