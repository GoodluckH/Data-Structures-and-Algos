# Substring Search

Substring search has very wide and useful applications. Aside from the brute force implementation for matching a pattern, there are other elegent yet fascinating algorithms.

## Brute Force

The brute force algo takes *MN* in runtime in the worst case. The best case scenario is *N*. But such algorithm involves backing up the counter whenever we find a mismatch. In the real world, many times we don't have the luxury and time to back the counter. But for now, the brute force implementation is as follow:

```java
public int match(String txt, String pat) {
    int N = txt.length();
    int M = pat.length();
    
    for (int i = 0; i < N-M; i++)
        int j;
        for (j = 0; j < M; j++)
            if(txt.charAt(i+j) != pat.charAt(j)) break;
        if (j == M) return i; // found a match
    return N;  // not found
}
```

The alternative implementation explicitly shows us the counter backup:

```java
public int match(String txt, String pat) {
    int N = txt.length();
    int M = pat.length();
    int i, j;
    for (i = 0, j = 0; i < N && j < M; i++) {
        if (txt.charAt(i) == pat.charAt(j)) j++;
        else {i -= j; j = 0;}
    }
    if (j == M) return i - M;  // found
    return N;  // not found
}
```

## Knuth-Morris-Pratt Substring Search (KMP)

The KMP algorithm is built upon the concept of "deterministic finite-state automation", DFA for short. This allows us to find out what we need in runtime of *N* with no pointer backups. It basically works as such:

```java
public int search(String txt){
    int i, j, N = txt.length();
    for (i = 0, j = 0; i < N && j < M; i++)
        j = dfa[txt.charAt(i)][j];
    if (i == M) return i - M;
    else return N;
}
```
We represent DFA using a two-dimentional array, it basically tells us where our `j` counter should be for each `char` in the *txt* we examine. Next, we will build thid DFA efficiently to complete our implementation of the KMP algorithm.

```java
public KMP(String pat) {
        this.pat = pat;
        M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];  // copy over the mismatch case from state X
            dfa[pat.charAt(j)][j] = j + 1; // set the next state for current char in pat
            X = dfa[pat.charAt(j)][X];    // update X;
        }
    }
```
It is a fast algorithm but our dfa construction takes time and space proportional to *RM*, if *R* (in the case of unicode) and/or *M* is huge, the constrcutor will be inefficient. There are workaround solutions involved using NFA, or nondeterministic finite-state automation, that improves the performance to *M*. But it's too complicated for our learning purpose. 