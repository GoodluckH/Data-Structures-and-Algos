# Implement Trie (Prefix Tree)

https://leetcode.com/problems/implement-trie-prefix-tree/

## Description
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
Implement the Trie class:
Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

## Approach
You learned this in the algo class. Basically, we just need to use an array that denotes all 26 letters.

## Implementation

```java
class Trie {
    Trie[] next;
    String word;

    public Trie() {
        next = new Trie[26];
    }
    
    public void insert(String word) {
        Trie p = this;
        for (char c: word.toCharArray()) {
          c -= 'a';
          if (p.next[c] == null) p.next[c] = new Trie();
          p = p.next[c];
        }
        p.word = word;
    }
    
    public boolean search(String word) {
        Trie p = this;
        for (char c: word.toCharArray()) {
          c -= 'a';
          if (p.next[c] == null) return false;
          p = p.next[c];
        }
        return p.word != null;
    }
    
    public boolean startsWith(String prefix) {
        Trie p = this;
        for (char c: prefix.toCharArray()) {
          c -= 'a';
          if (p.next[c] == null) return false;
          p = p.next[c];
        }
        if (p.word != null) return true;
        for (Trie t: p.next) {
          if (t != null) return true;
        }
        return false;
    }
}
```

## Complexity Analysis
For insert, we are basically using O(C) runtime where C is the length of the word. For search, it's at worst O(C), and prefix is O(C), too.

For space, it's O(26C + C * W) where C is the average length of all words inserted and W is the number of words.
