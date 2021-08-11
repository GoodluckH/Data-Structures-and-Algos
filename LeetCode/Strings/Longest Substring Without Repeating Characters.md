# Longest Substring Without Repeating Characters

https://leetcode.com/problems/longest-substring-without-repeating-characters/

## Description

Given a string s, find the length of the longest substring without repeating characters.

## Approach

So this problem is similar to the array problem for finding the longest subarray. There, we use Kadane's algorithm to update our value. We can do the similar thing here by using a hash map that keep track of index positions of each character. If we meet a repeating character, then we need to update our `j` index by setting it to where we've seen the character previously.

However, we do need to set up a `Math.max` to prevent setting our `j` to a position smaller than the current `j` position. Otherwise, it wouldn't be a subarray.

## Implementation

```java
public int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;
    for (int i = 0, j = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (map.containsKey(c)) j = Math.max(j, map.get(c) + 1);
        map.put(c, i);
        max = Math.max(max, i-j+1);
    }
    return max;
}
```

## Complexity Analysis
Immediately from the code, we are running in linear time. And since we are using a hash map, our space requirement would be linear as well.
