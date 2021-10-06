# Design Add and Search Words Data Structure

https://leetcode.com/problems/design-add-and-search-words-data-structure/

## Description
Design a data structure that supports adding new words and finding if a string matches any previously added string.
Implement the WordDictionary class:
WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

## Approach
Implement a trie, and then for the `search` method, we need to find a way to recursively check each valid trie node.

## Implementation

```java
class WordDictionary {
    Trie root;
    
    class Trie {
      Trie[] next = new Trie[26];
      boolean isWord;
    }
    public WordDictionary() {
        this.root = new Trie();
    }
    
    public void addWord(String word) {
        Trie p = root;
        for (char c: word.toCharArray()) {
          c -= 'a';
          if (p.next[c] == null) p.next[c] = new Trie();
          p = p.next[c];
        }
        p.isWord = true;
    }
    
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] chs, int i, Trie p) {
      if (i == chs.length) return p.isWord;
      
      if (chs[i] == '.') {
        for (Trie t: p.next) {
          if (t != null && match(chs, i + 1, t)) return true;
        }
      } else {
        return p.next[chs[i] - 'a'] != null && match(chs, i + 1, p.next[chs[i] -'a']);
      }
      return false;
    }
}
```


## Complexity Anlaysis
Let C be the average number of characters, N is the number of words inserted. For the insertion operation, the runtime is O(C) and the space compexity is O(26C * N).

When it comes to search, the worst case runtime is O(26^C) when we query with many wildcards as prefix and that every slot in p.next is not null. The space complexity is O(C).
