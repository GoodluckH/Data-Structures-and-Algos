# Minimum Window Substring

https://leetcode.com/problems/minimum-window-substring/

## Description
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
A substring is a contiguous sequence of characters within the string.

## Approach
For substring problems, we should try to think about using the sliding window technique first. For this problem, we will have an array of integers serve as a map for characters in `t`. Then, the crux of the problem is to find out a condition for which the "window contracting" operation is triggered.

To do this, we need to keep track of the number of valid characters in the current window. But at first, we need to count the frequency of each character in `t` and update the value in the map. For all other characters, they should be defaulted at 0.

Then, as we are looping through the string, we need to see if the current character is one of those in `t` by checking if its count is larger than 0. If so, we decrement the `counter` variable that was set to equal to `t`'s length. Meanwhile, we need to decrement the current valid character by 1 to show that we have just included it in our substring.

And immediately afterward, we increment the `end` pointer.

So, if the current window contains all characters in the `t` string, we should have a `counter` of 0. And we need to perform the window contraction in a while-loop. 

Inside of that loop, we need to update the `minStart` pointer if the current window is smaller than the previous window, and update the window to the current size. Then, we simply increment the character that `start` points to in order to imply that we've excluded it from our window.

If a `start` character counter is larger than 0 after the increment, then we know this character is in `t`, therefore, we increment the `counter` by one.

## Implementation
```java
public String minWindow(String s, String t) {
    int[] map = new int[128];
    for (char c: t.toCharArray()) map[c]++;
    int start = 0, end = 0, minStart = 0, counter = t.length(), minLen = Integer.MAX_VALUE;

    while (end < s.length()) {
        final char cEnd = s.charAt(end);
        if (map[cEnd]-- > 0) counter--;
        end++;

        while (counter == 0) {
            if (minLen > end - start) {
                minLen = end - start;
                minStart = start;
            }
            final char cStart = s.charAt(start);
            if (++map[cStart] > 0) counter++;
            start++; 
        }
    }
    return minLen == Integer.MAX_VALUE? "": s.substring(minStart, minStart + minLen);
}
```

## Complexity Analysis
Our space requirement is O(128) which is essentially constant. As for runtime complexity, we are looping through every character in `s`, so that's O(N) for N is the length of `s`. Then, the contraction comes in, which would take at worst O(M) for M is the length of `t`. So in total, our runtime is O(N + M) but since N is usually larger than M, we can say this is a linear algorithm.