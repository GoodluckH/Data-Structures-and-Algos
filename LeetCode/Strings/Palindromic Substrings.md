# Palindromic Substrings

https://leetcode.com/problems/palindromic-substrings/

## Description
Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

## Approach
If you know the center-out technique used in finding the [longest panlindrome substring](https://github.com/GoodluckH/learn/blob/main/LeetCode/Strings/Longest%20Palindromic%20Substring.md), then this problem is very simple. We just need to have counter that records the result.

## Implementation
```java
public int countSubstrings(String s) {
  int count = 0;
  for (int i = 0; i < s.length(); i++) 
    count += cOut(s, i, i) + cOut (s, i, i + 1)
  return count;
}

private int cOut(String s, int i, int j) {
  int count = 0;
  while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j))
    {count++; i--; j++;}
  return count;
}
```

## Complexity Analysis 
Immediately from the code, we are looping through the string in O(N) time, where N is the number of characters. Then, for each char, we run the "center out" algorithm to find palindromes. That would take O(M) time for M is the average length of all panlindromes. Ergo, the runtime is O(MN). Space is constant.         

