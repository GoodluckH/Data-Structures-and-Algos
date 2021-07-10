import edu.princeton.cs.algs4.Queue;

public class TST<Value> {
    private Node root;

    private class Node {
        Node left, mid, right;
        char c;
        Value val;
    }

    public Value get(String key) {
        Node v = get(root, key, 0);
        if (v == null)
            return null;
        return v.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        char c = key.charAt(d);
        if (c < x.c)
            return get(x.left, key, d);
        else if (c > x.c)
            return get(x.right, key, d);
        else if (d < key.length()-1)
            return get(x.mid, key, d + 1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c)
            x.left = put(x.left, key, val, d);
        else if (c > x.c)
            x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d + 1);
        else
            x.val = val;
        return x;
    }

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
        if (x == null)
            return;
        if (x.val != null)
            q.enqueue(pre + x.c);

        if (x.mid != null)
            collect(x.mid, pre + x.c, q);
        if (x.left != null)
            collect(x.left, pre, q);
        if (x.right != null)
            collect(x.right, pre, q);
    }

    public String longestPrefixOf(String key) {
      /*   if (key == null) throw new IllegalArgumentException();
        Node x = root;
        int length = 0;
        int i = 0;
        while (x != null && i < key.length()) {
            char c = key.charAt(i);
            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid; 
            }
        } */
        int length = search(root, key, 0, 0);
        return key.substring(0, length+1);
    }

    private int search(Node x, String key, int d, int length) {
        if (x == null) return length;
        if (d == key.length()) return length;
        char c = key.charAt(d);
        if (c < x.c) return search(x.left, key, d, length);
        else if (c > x.c) return search(x.right, key, d, length);
        else { 
            if (x.val !=  null) length = d; 
            return search(x.mid, key, d+1, length);}
    }

    public static void main(String[] args) {
        TST<Integer> t = new TST<>();
        t.put("by", 0);
        t.put("sea", 1);
        t.put("sells", 2);
        t.put("she", 3);
        t.put("shells", 4);
        t.put("shore", 5);
        t.put("the", 6);

        for (String s : t.keys()) {
            System.out.println(s);
        }

        System.out.println("Keys with prefix of she: ");
        for (String s : t.keysWithPrefix("she")) {
            System.out.println(s);
        }
        System.out.println("Longest prefix of shellsort: ");
        System.out.println(t.longestPrefixOf("shellsort"));


    }
}