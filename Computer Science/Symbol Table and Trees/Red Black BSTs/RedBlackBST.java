import java.util.Queue;
import java.util.LinkedList;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public void put(Key key, Value val){
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public int size(){
       return size(root);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());    
    }

    public Key max() {
        return max(root).key;
    }
 
    public Key min() {
        return min(root).key;
    }
//************** private methods and classes **************
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);    
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);    
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;

        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        if (cmpLo < 0) keys(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.add(x.key);
        if (cmpHi > 0) keys(x.right, queue, lo, hi);
    }

    private class Node {
        boolean color;
        int N;
        Node left, right;
        Key key; 
        Value val;

        Node(Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.N = 1 + size(x.right) + size(x.left);
        return x;
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        else return x.val;
    }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
     
    private int size(Node x) {
       if (x == null) return 0;
       return x.N;
    } 
    
    private Node rotateLeft(Node x) {
        Node h = x.right;
        x.right = h.left; 
        h.left = x;
        h.color = x.color;
        x.color = RED;
        h.N = x.N;
        x.N = 1 + size(x.right) + size(x.left);
        return h;
    }

    private Node rotateRight(Node x) {
        Node h = x.left;
        x.left = h.right; 
        h.right = x;
        h.color = x.color;
        x.color = RED;
        h.N = x.N;
        x.N = 1 + size(x.right) + size(x.left);
        return h;
    }

    private void flipColors(Node x) {
        x.color = RED;
        x.right.color = BLACK;
        x.left.color = RED;
    }

//*********** Testing *****************

    public static void main (String[] args){
        RedBlackBST<String, Integer> test = new RedBlackBST<>();

		test.put("A", 1);
		test.put("B", 2);
		test.put("C", 3);
		test.put("D", 4);
		test.put("E", 5);
		test.put("F", 6);
		test.put("G", 7);
        for (String s: test.keys()){
            System.out.println(s);
        }

        System.out.println();
//        test.deleteMin();
//        test.deleteMax();
//        test.delete("E");
//        System.out.println("Deleted max and min and E.");
//        System.out.println("The new table looks like: ");

        for (String s: test.keys()){
            System.out.println(s);
        }

        RedBlackBST<Character, Character> large = new RedBlackBST<>();

        for (int i = 1; i <= 20000; i++) {
            large.put((char)i, (char)i);
        }

        System.out.println((int)large.max());
    }
}
