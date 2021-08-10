# LeetCode

## Java Native Data Structures 

`HashMap`: Implemented by *Seperate Chaining* method. It has a constant runtime for insert and search due to the hashCode method. But worst case scenario might result in logarithmic runtime of lg*N*.

`Arrays.sort()`: Implemented by using *dual-pivot quicksort* which has an O(*n*log*n*) runtime, and is faster than single pivot quicksort. 

`Collections.sort()`: Uses merge sort to ensure stability.

More for sorting: https://stackoverflow.com/questions/15154158/why-collections-sort-uses-merge-sort-instead-of-quicksort


`String.substring()`: this should take about O(M) in runtime in the newest Java, but in Java 6 this was constant time.

`String.indexOf()`: seems like this is O(MN), too.

`String.split()`: https://stackoverflow.com/questions/13081527/splitting-string-on-multiple-spaces-in-java.
```
    `+` - Represents 1 or more
    `*` - Represents 0 or more
    `?` - Represents 0 or 1
`{n,m}` - Represents n to m
```
So, if we want to split by multiple spaces, we can call `split("\\s+")`. This is an O(N) algo.

If the string has leading space, then we need to call `trim()` first to get rid of the space. This method takes at worst O(N) time.

`String.join(delimitor, String[])`: https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string This is essentially an O(N) built-in function to join strings from an array. 




# Curated Lists of Top 75 LeetCode Questions

This is a backup of the original post: https://www.teamblind.com/post/New-Year-Gift---Curated-List-of-Top-100-LeetCode-Questions-to-Save-Your-Time-OaM1orEU


Array

- Two Sum - https://leetcode.com/problems/two-sum/
- Best Time to Buy and Sell Stock - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- Contains Duplicate - https://leetcode.com/problems/contains-duplicate/
- Product of Array Except Self - https://leetcode.com/problems/product-of-array-except-self/
- Maximum Subarray - https://leetcode.com/problems/maximum-subarray/
- Maximum Product Subarray - https://leetcode.com/problems/maximum-product-subarray/
- Find Minimum in Rotated Sorted Array - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
- Search in Rotated Sorted Array - https://leetcode.com/problems/search-in-rotated-sorted-array/
- 3Sum - https://leetcode.com/problems/3sum/
- Container With Most Water - https://leetcode.com/problems/container-with-most-water/

---

Binary

- Sum of Two Integers - https://leetcode.com/problems/sum-of-two-integers/
- Number of 1 Bits - https://leetcode.com/problems/number-of-1-bits/
- Counting Bits - https://leetcode.com/problems/counting-bits/
- Missing Number - https://leetcode.com/problems/missing-number/
- Reverse Bits - https://leetcode.com/problems/reverse-bits/

---

Dynamic Programming

- Climbing Stairs - https://leetcode.com/problems/climbing-stairs/
- Coin Change - https://leetcode.com/problems/coin-change/
- Longest Increasing Subsequence - https://leetcode.com/problems/longest-increasing-subsequence/
- Longest Common Subsequence -
- Word Break Problem - https://leetcode.com/problems/word-break/
- Combination Sum - https://leetcode.com/problems/combination-sum-iv/
- House Robber - https://leetcode.com/problems/house-robber/
- House Robber II - https://leetcode.com/problems/house-robber-ii/
- Decode Ways - https://leetcode.com/problems/decode-ways/
- Unique Paths - https://leetcode.com/problems/unique-paths/
- Jump Game - https://leetcode.com/problems/jump-game/

---

Graph

- Clone Graph - https://leetcode.com/problems/clone-graph/
- Course Schedule - https://leetcode.com/problems/course-schedule/
- Pacific Atlantic Water Flow - https://leetcode.com/problems/pacific-atlantic-water-flow/
- Number of Islands - https://leetcode.com/problems/number-of-islands/
- Longest Consecutive Sequence - https://leetcode.com/problems/longest-consecutive-sequence/
- Alien Dictionary (Leetcode Premium) - https://leetcode.com/problems/alien-dictionary/
- Graph Valid Tree (Leetcode Premium) - https://leetcode.com/problems/graph-valid-tree/
- Number of Connected Components in an Undirected Graph (Leetcode Premium) - https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

---

Interval

- Insert Interval - https://leetcode.com/problems/insert-interval/
- Merge Intervals - https://leetcode.com/problems/merge-intervals/
- Non-overlapping Intervals - https://leetcode.com/problems/non-overlapping-intervals/
- Meeting Rooms (Leetcode Premium) - https://leetcode.com/problems/meeting-rooms/
- Meeting Rooms II (Leetcode Premium) - https://leetcode.com/problems/meeting-rooms-ii/

---

Linked List

- Reverse a Linked List - https://leetcode.com/problems/reverse-linked-list/
- Detect Cycle in a Linked List - https://leetcode.com/problems/linked-list-cycle/
- Merge Two Sorted Lists - https://leetcode.com/problems/merge-two-sorted-lists/
- Merge K Sorted Lists - https://leetcode.com/problems/merge-k-sorted-lists/
- Remove Nth Node From End Of List - https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- Reorder List - https://leetcode.com/problems/reorder-list/

---

Matrix

- Set Matrix Zeroes - https://leetcode.com/problems/set-matrix-zeroes/
- Spiral Matrix - https://leetcode.com/problems/spiral-matrix/
- Rotate Image - https://leetcode.com/problems/rotate-image/
- Word Search - https://leetcode.com/problems/word-search/

---

String

- Longest Substring Without Repeating Characters - https://leetcode.com/problems/longest-substring-without-repeating-characters/
- Longest Repeating Character Replacement - https://leetcode.com/problems/longest-repeating-character-replacement/
- Minimum Window Substring - https://leetcode.com/problems/minimum-window-substring/
- Valid Anagram - https://leetcode.com/problems/valid-anagram/
- Group Anagrams - https://leetcode.com/problems/group-anagrams/
- Valid Parentheses - https://leetcode.com/problems/valid-parentheses/
- Valid Palindrome - https://leetcode.com/problems/valid-palindrome/
- Longest Palindromic Substring - https://leetcode.com/problems/longest-palindromic-substring/
- Palindromic Substrings - https://leetcode.com/problems/palindromic-substrings/
- Encode and Decode Strings (Leetcode Premium) - https://leetcode.com/problems/encode-and-decode-strings/

---

Tree

- Maximum Depth of Binary Tree - https://leetcode.com/problems/maximum-depth-of-binary-tree/
- Same Tree - https://leetcode.com/problems/same-tree/
- Invert/Flip Binary Tree - https://leetcode.com/problems/invert-binary-tree/
- Binary Tree Maximum Path Sum - https://leetcode.com/problems/binary-tree-maximum-path-sum/
- Binary Tree Level Order Traversal - https://leetcode.com/problems/binary-tree-level-order-traversal/
- Serialize and Deserialize Binary Tree - https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
- Subtree of Another Tree - https://leetcode.com/problems/subtree-of-another-tree/
- Construct Binary Tree from Preorder and Inorder Traversal - https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
- Validate Binary Search Tree - https://leetcode.com/problems/validate-binary-search-tree/
- Kth Smallest Element in a BST - https://leetcode.com/problems/kth-smallest-element-in-a-bst/
- Lowest Common Ancestor of BST - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
- Implement Trie (Prefix Tree) - https://leetcode.com/problems/implement-trie-prefix-tree/
- Add and Search Word - https://leetcode.com/problems/add-and-search-word-data-structure-design/
- Word Search II - https://leetcode.com/problems/word-search-ii/

---

Heap

- Merge K Sorted Lists - https://leetcode.com/problems/merge-k-sorted-lists/
- Top K Frequent Elements - https://leetcode.com/problems/top-k-frequent-elements/
- Find Median from Data Stream - https://leetcode.com/problems/find-median-from-data-stream/
