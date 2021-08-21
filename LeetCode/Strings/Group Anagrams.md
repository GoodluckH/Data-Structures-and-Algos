# Group Anagrams
https://leetcode.com/problems/group-anagrams/


## Description
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Approach
We can basically do this in one loop. But obviously, we'd need a map to know if a valid anagram already exists, if so, add it to the array list. The fun thing about it is that we can maintain a char array as a frequency counter for each word, then by converting it to string, we can see if it exists in the map. By using char array, we don't need to worry about the orderings of each word because our char array is already initialized in alphabetical order, implicitly.

## Implementation
```java
public List<List<String>> groupAnagrams(String[] strs) {
  if (strs == null || strs.length == 0) return new ArrayList<>();
  Map<String, List<String>> map = new HashMap<>();
  for (String word: strs) {
    char[] count = new char[26];
    for (char c: word.toCharArray()) count[c - 'a']++;
    String keyStr = String.valueOf(count);
    if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
    map.get(keyStr).add(word);
  }
  return new ArrayList<>(map.values());
}
```

## Complexity Analysis
The outer for-loop runs in linear time proportional to N, for N is the number of word in the input array. Then, for each word, we need to loop through each character within that word. So, if M represents the average length of the words, our runtime complexity is O(MN).

As for space, since we are using a map of array lists, our space is proporational to O(NM), or O(number of chars in input array).
