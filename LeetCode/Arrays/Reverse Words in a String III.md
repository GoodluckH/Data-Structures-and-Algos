# Reverse Words in a String III

Link to the problem: https://leetcode.com/problems/reverse-words-in-a-string-iii/


## Description
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order. 

Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "God Ding"
Output: "doG gniD"

## Approach

It's quite simple, we need to identify each word and just reverse them. The constraints to this problem ensures that we don't need to do additional cleaning.

## Implementation
```java
public String reverseWords(String s) {
    char[] a = s.toCharArray();
    int n  = a.length;
    reverseWord(a, n);
    return new String(a);
    }

void reverseWord(char[] a, int n) {
    int i = 0, j = 0;
    while (i < n) {
        while (j < n && a[j] != ' ') j++;
        reverse(a, i , j-1);
        i = j + 1;
    }
}

void reverse(char[] a, int i, int j) {
    while (i < j) {
        char t = a[i];
        a[i++] = a[j];
        a[j--] = t;
    }
}
```