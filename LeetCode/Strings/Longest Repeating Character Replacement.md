# Longest Repeating Character Replacement

https://leetcode.com/problems/longest-repeating-character-replacement/

## Description
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.


## Approach
So, this problem wants us to use the sliding window technique rather than the quadratic brute-force approach. It works like this: we need to have a data structure that counts the frequence of characters within our "window". So, if we have a string "ABBABB", when `i` pointer is at 0 and `j` pointer is at 3, we should have 2 "A"s and 2 "B"s in the array or map. Suppose our `k` is 2, then we want to find out whether we are obeying the invariant.

To do that, we need to maintain a variable `maxf` that keeps track of the number of dominant characters in the current window. That means the number of characters to replace is the current window length substracts the number of dominant characters, or `j-i+1 - maxf`. If this value is larger than `k`, then we know we need to shrink our window by incrementing our left pointer, `i`, and meanwhile, decrement the count of the character that `i` points to.

Once `j` has reached the end of the string, we can get the max window length by returning `n - i` because our window will be fixed at certain point, and it will "slide" all the way to the end. So `n-i` gives us the length of the window, which is the answer we are looking for. 
## Implementation
```java
public int characterReplacement(String s, int k) {
    int i = 0, maxf = 0, n = s.length(), count[] = new int[26];
    for (int j = 0; j < n; j++){
        maxf = Math.max(maxf, ++count[s.charAt(j) - 'A']);
        if (j - i + 1 - maxf > k) --count[s.charAt(i++) - 'A'];
    }
    return n - i;
}
```

## Complexity Analysis
This is an O(N) algorithm since we are just passing the `j` pointer through the string. As for space, it's O(26), which is essentially constant.