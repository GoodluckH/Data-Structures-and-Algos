# Longest Palindromic Substring

https://leetcode.com/problems/longest-palindromic-substring/

## Description
Given a string s, return the longest palindromic substring in s.

## Approach
So, for this problem, we can use what I call "center-out" technique in which for each character, we treat it as the center of a palindrome, and then have two pointers expand outward simultaneously.

## Implementation
```java
public String longestPalindrome(String s) {
  if (s.length() < 2) return s;
  int start = 0, end = 0, len = 0; 
  for (int i = 0; i < s.length(); i++) {
    // Call extend twice to take care of palindromes with odd and even number of characters
    int len1 = extend(s, i, i), len2 = extend(s, i, i + 1);
    if (len1 > len) {len = len1; start = i - len/2; end = i + len/2;}
    if (len2 > len) {len = len2; start = i - len/2 + 1; end = i + len/2;}
  }
  return s.substring(start, end + 1);
}

private int extend(String s, int i, int j) {
  while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {i--; j++;}
  return j - i - 1; // Or (j - i + 1) - 2 to backtrack both pointers
}

```

## Complexity Analysis

For each character in the string, we are finding palindromes, so the worst case runtime is O(N^2). We don't need any extra space here so it's constant.
