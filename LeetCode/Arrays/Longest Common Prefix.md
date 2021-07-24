# Longest Common Prefix

Link to the problem: https://leetcode.com/problems/longest-common-prefix/

## Description
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

## Approach 

Basically two approaches here, one is to set the prefix to be the same as the first string in the input array. Then, for the rest of the strings, we trim our prefix until it matches all. This approach uses `indexOf` method that helps us determine whether a substring is valid in a string. This will have a clean and short implementation code.

The downside, however, is that we need to run `indexOf` for every strings. So, if there's short string at the end of the array, then we will be taking a very long time to execute the algo.

Another approach then, scan first character of all strings, and then second character, and so on. So, once we find a string that doesn't have a matching character, we can just terminate the algo. But this one doesn't yield an aesthetically appleasing code.

## Implementation
Horizontal scan.

```java
public String longestCommonPrefix(String[] strs) {
    String pre = strs[0];
    for (int i = 1; i < strs.length; i++) 
        while(strs[i].indexOf(pre) != 0) pre = pre.substring(0, pre.length()-1);
    return pre;
}
```

## Complexity Analysis

The for-loop is proportional to O(N), and `indexOf` is in O(M*N), the substring method is also O(N). So, overral the runtime is O(N^2). The space requirement is at most O(M). 