# Valid Palindrome

https://leetcode.com/problems/valid-palindrome/


## Description
Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

## Approach
We can just use two pointers scanning from both ends of the string toward the middle. The trick is to ignore invalid characters and to compare characters regardless of their cases.

## Implementation
```java
public boolean isPalindrome(String s) {
  int i = 0, j = s.length() - 1;
  while (i < j) {
    while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
    while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
    if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
  }
  return true;
}
```

## Complexity Analysis

Immediately from the code, this is a linear time solution. The space requirement is constant.
