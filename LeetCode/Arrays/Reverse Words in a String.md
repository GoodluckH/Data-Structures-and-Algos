# Reverse Words in a String

Link to the problem: https://leetcode.com/problems/reverse-words-in-a-string/

## Description
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

 

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"

## Approach

The cheating way to solve this problem is to use bunch of built-in functions:
```java
public String reverseWords(String s) {
        String[] sp = s.split("\\s+"); // using regex expression to get rid of all spaces except the leading one
        for (int i = 0, j = sp.length - 1; i < j; i++, j--) {  //reverse the list
            String t = sp[i];
            sp[i] = sp[j];
            sp[j] = t;
        }
        return String.trim().join(" ", sp);
    }
```

However, as a medium level problem, I doubt interviewers would want the cookie-cutting way of solving this. We need to implement our own way to solve the problem. First, we need to reverse the string, but doing so will also reverse each word. So, we need to reverse each word back to the correct order. Finally, we need to clean up our solution by git rid of unnecessary spaces.

## Implementation

```java
public String reverseWords(String s) {
    char[] a = s.toCharArray();
    int n = a.length;
    reverse(a, 0, n-1);
    reverseWord(a, n);
    return cleanUp(a, n);
}

String cleanUp(char[] a, int n){
    int i = 0, j = 0;
    while (i < n) {
        while(i<n && a[i] == ' ') i++;
        while(i<n && a[i] != ' ') a[j++] = a[i++];
        while(i<n && a[i] == ' ') i++;
        if(j < n) a[j++] = ' ';
    }
    String result = new String(a);
    return result.substring(0, j);
}

void reverseWord(char[] a, int n) {
    int i = 0, j = 0;
    while (i < n) {
        while(i<n && a[i] == ' ') i++;
        j = i;
        while(j<n && a[j] != ' ') j++;
        reverse(a, i, j-1);
        i = j;
    }
}

void reverse(char[] a, int i, int j) {
    while(i < j) {
        char t = a[i];
        a[i++] = a[j];
        a[j--] = t;
    }
}
```

## Complexity Analysis
The runtime is linear and space is linear as well.