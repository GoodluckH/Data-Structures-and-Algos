# Valid Anagram

https://leetcode.com/problems/valid-anagram/

## Description

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

## Approach

We need to have some map that keeps track of each character's count in one of the string inputs. Then, we loop through the other string input to see if we can reduce all count to 0. If not, then return false.

## Implementation
```java
public boolean isAnagram(String s, String t) {
  if (s.length() != t.length()) return false;
  int[] map = new int[26];
  for (int i = 0; i < s.length(); i++) map[s.charAt(i) - 'a']++;
  for (int i = 0; i < t.length(); i++) if (--map[t.charAt(i) - 'a'] < 0) return false;
  return true;
}
```

## Complexity Analysis

Runtime is linear and space is constant.
