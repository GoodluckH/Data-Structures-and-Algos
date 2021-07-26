# Plus One

Link to the problem: https://leetcode.com/problems/plus-one/

## Description
```
Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.
```

## Approach

This one might be really tricky, no wonder the downvotes. However, all we need is to start at the end of the array and increment the digit by one. The hard part is to deal with 9s. We can simply address this in a loop. So, if the digit is less than 9, we just increment it by one, and immediately return the resulting array. Otherwise, we set the current digit to 0, and continue to the next digit. 

The if-statement helps us increment digit when there's a 9 in the lesser significant digit. In case we have an array like [9,9,9], then we know the result should be [1,0,0,0]. So we have to create a new array of size n+1, and set the first element to 1.

## Implementation

```java
public int[] plusOne(int[] digits) {
    for (int i = digits.length-1; i >= 0; i--){
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
    }

    int[] result = new int[digits.length+1];
    result[0] = 1;
    return result;

}
```

## Complexity Analysis
Immediately from the code, our runtime is linear. However, the space requirement in the worst case is linear, and the best or average case should be constant since our algo terminates before `result` gets initiated.