# Tries

A trie is a data structure for string symbol tables. The runtime performance is proportional to the length of the key we are searching/inserting for. It is important to note that such data structure does not contain any characters, only links and values.

## R-Way Trie

This is the rudimentary implementation of a trie where, starting from root, each node points to an R-sized array containing null or non-null values. The implementation is straightforward. Starting with `size()`, we can have three options:
1. An eager implementation where we maintain a variable *N* to keep track of the size
2. A very eager implementation where each `Node` class has a size variable to keep track of the size of all its subnodes
3. A lazy approach that simply traverses through the trie to compute the size

The lazy approach should be avoided because it can lead to performance problems, but the implementation is instructive enough for us to learn:

```java
public int size() {
    return size(root);
}

private int size(Node x) {
    if (x == null) return 0;
    int cnt = 0;

    if (x.val != null) cnt++;
    for (char c = 0; c < R; c++)
        cnt += size(x.next[c])
    return cnt;
}
```

The implementation for get and put are straightforward with recursion:

```java
public class TrieST<Value> {
    private static int R = 256;
    private Node root;
    
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root,key,0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }
    
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {x.val = val; return x;}
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
}
```
### keysWithPrefix
Sometimes we want to get keys with certain prefixes. It is tricky because our data structure is implicit, meaning we don't have characters stored. So we need a `collect` method to help us examine and enqueue keys that match our criteria.

```java
public Iterable<String> keys() 
{ return keysWithPrefix(""); }   // calling empty string will return the whole trie for collect to iterate through

public Iterable<String> keysWithPrefix(String pre) {
    Queue<String> q = new Queue<String>();
    collect(get(root, pre, 0), pre, q);     
    return q;
}

private void collect(Node x, String pre, Queue<String> q) {
    if (x == null) return;
    if (x.val != null) q.enqueue(pre);
    for (char c = 0; c < R; c++)
        collect(x.next[c], pre+c, q);
    }
```

### keysThatMatch
It is also helpful to have a `keysThatMatch` method to perform wildcard matches. We need to modify our `collect` method a bit by adding a `pat` argument to match the given pattern.

```java
public Iterable<String> keysThatMatch(String pat) {
    Queue<String> q= new Queue<>();
    collect(root, "", pat, q);
    return q;
}

private void collect(Node x, String pre, String pat, Queue<String> q) {
    int d = pre.length();
    if (x == null) return;
    if (d == pat.length() && x.val != null) {q.enqueue(pre); return;}

    char next = pat.charAt(d);
    for (char c = 0; c<R; c++)
        if (next == "." || next == c)
            collect(x.next[c], pre+c, pat, q);
}
```
### longestPrefixOf
Now, we want to recursively search the trie to return a valid key that is the longest prefix of a given string. To do this, we need a `search` private method to keep track of a length parameter.

```java
public String longestPrefixOf(String s) {
    int length = search(root, 0, s, 0);
    return s.substring(0, length);
}

private int search(Node x, int d, String s, int length) {
    if (x == null) return length;
    if (x.val != null) length = d;
    if (d == s.length()) return length;
    char c = s.charAt(d);
    return search(x.next[c], d+1, s, length);   
}
```

### Delete

The deletion operation is pretty simple to implement as we just set the value to the end of the string to null. However, if doing so will result in a parent of null values, we need to set the parent to null as well. And so forth.

```java
public void delete(String key) {
    root = delete(root, key, 0);
}

private Node delete(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) x.val = null;
    else {
        char c = key.charAt(d);
        x.next[c] = delete(x.next[c], key, d+1);
    }

    if (x.val != null) return x;
    for (char c = 0; c < R; c++)
        if (x.next[c] != 0) return x;
    return null;
}
```

### Properties of Tries
Unlike other search trees, a trie's structure is independent to the order of key insertion and deletions. That means for every set of keys, we can obtain a unique subtrie.

The runtime it takes for worst case search and insert operations is 1 + the length of the key. That is because we use an argument `d` to keep track of the length. When `d` is equal to key length, we stop. This is important because the number of keys in a trie does not affect our runtime. So, it also means that if our keys are very long, the runtime performance will suffer.

But in case of search miss, we don't need to examine the whole key. Usually we can just examine a couple to see if there's such key exists. The runtime for average search miss is about ~log*_RN* where R is the alphebet size and N is the number of random keys. 

Another drawback is that we have so many null values, which would take up *RN* to *RNw* links, where *w* is the average key length. So, we can improve our trie data structure to save some space.

## Ternary Search Tries

We can borrow the concept from binary search trees for our TST implementation. Basically, we modify our Node class so that each node contains a character, a value, and three Node classes that contains characters less than, equal to, and greater than the current node.

To search for a key, we just start with the root, if the first character is less than the root, then we go to the left, and vice versa. We terminate the recursion when we encounter null nodes or when we stop search on a null-value node.

```java
public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }
    
    public Value get(String key) {
        Node v = get(root, key, 0);
        if (v == null) return null;
        return (Value) v.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length()-1) return get(x.mid, key, d+1);
        else return x;
    }


    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, String val, int d) {
        char c = key.charAt(d);
        if (x == null){ x = new Node(); x.c = c;}

        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length()-1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;
        return x;
    }
}
```


### keysWithPrefix

```java
    public Iterable<String> keys() {
        Queue<String> q = new Queue<String>();
        collect(root, "", q);
        return q;
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        Node x = get(root, pre, 0);
        if (x == null) return q;
        if (x.val != null) q.enqueue(pre);
        collect(x.mid, pre, q);
        return q;
    }   

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre + x.c);

        if (x.mid != null) collect(x.mid, pre + x.c, q);
        if (x.left != null) collect(x.left, pre, q);
        if (x.right != null) collect(x.right, pre, q);
    }
```

### Properties of TSTs

Now the TST structure is dependent on the order of insertion and deletion, just like BSTs. Also, we can significantly reduce the space requirement to *3N* to *3Nw* with the new implementation. The search miss runtime is now ln*N*, which is still fast. The worst case is similar to that of a BST, where we have a spindly tree, so that we have to multiply our runtime by a factor of *R*.

