# Word Search II 

https://leetcode.com/problems/word-search-ii/

## Description
Given an m x n board of characters and a list of strings words, return all words on the board.
Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

## Approach
If we build all words into a trie, then we can just find words by traversing each cell of the board. Since we can't use the cells we already visited, we can technically use a marked[][] to indicate that but a more efficient way is to modify the board.

## Implementation
```java
public List<String> findWords(char[][] board, String[] words) {
  List<String> ans = new ArrayList<>();
  Trie trie = build(words);
  
  for (int i = 0; i < board.length; i++) {
    for (int j = 0; j < board[0].length; j++) {
      dfs(board, i, j, trie, ans);
    }
  }
  return ans;
}

void dfs(char[][] board, int i, int j, Trie p, List<String> ans) {
  char c = board[i][j];
  if (board[i][j] == '#' || p.next[c - 'a'] == null) return;
  p = p.next[c - 'a'];
  if (p.word != null) {
    ans.add(p.word);
    p.word = null;
  }
  
  board[i][j] = '#';
  if (i > 0) dfs(board, i - 1, j, p, ans);
  if (j > 0) dfs(board, i, j - 1, p , ans);
  if (i < board.length - 1) dfs(board, i + 1, j, p, ans);
  if (j < board[0].length - 1) dfs(board, i, j + 1, p, ans);
  board[i][j] = c;
}


Trie build(String[] words) {
  Trie root = new Trie();
  for (String w: words) {
    Trie p = root;
    for (char c: w.toCharArray()) {
      int i = c - 'a';
      if (p.next[i] == null) p.next[i] = new Trie();
      p = p.next[i];
    }
    p.word = w;
  }
  return root;
}

class Trie {
  Trie[] next = new Trie[26];
  String word;
}
```

## Complexity Analysis
Building the trie requires O(M * N) runtime for M is the average length of words and N is the number of words. Then, the board traversal is O(P * Q * 4M). As for space, the trie needs 2(N * M) space, and the dfs would recursively build a stack of M each time on average.
