import java.util.Queue;
import java.util.LinkedList;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public int size()
    {return size(root);} 

    public Value get(Key key)
    {return get(root, key);}

    public void put(Key key, Value val)
    { root = put(root, key, val); }
    
    public Key max() 
	{ return max(root).key; }

    public Key min() 
	{ return min(root).key; }

	public Key floor(Key key){
		Node x = floor(root, key);
		if (x == null) return null;
		return x.key;
	}
	
	public Key ceiling(Key key){
		Node x = ceiling(root, key);
		if (x == null) return null;
		return x.key;
	}
	
	public Key select(int k)
	{return select(root, k).key;}

    public int rank(Key key)
    {return rank(root, key);}

    public void deleteMin()
    {root = deleteMin(root);}

    public void deleteMax()
    {root = deleteMax(root);}

    public void delete(Key key)
    {root = delete(root, key);}

    public Iterable<Key> keys()
    {return keys(min(), max());}

    public Iterable<Key> range(Key lo, Key hi)
    {return keys(lo, hi);}

//************** private methods and classes **************
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int n;

        public Node(Key key, Value val, int n)
        {this.key = key; this.val = val; this.n = n;}
    }

    private Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMax(Node x){
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node delete(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
       }
       x.n = size(x.left) + size(x.right) + 1;
       return x; 
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Value get(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private int rank(Node x, Key key){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.n;
    }
    
	private Node select(Node x, int k){
		if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        if (t < k) return select(x.right, k-t-1);
        else return x;
	} 

	private Node floor(Node x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else return x;
	}

	private Node ceiling(Node x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null) return t;
		else return x;
	}

	private Node min(Node x){
		if (x.left == null) return x;
		return min(x.left);
	}

	private Node max(Node x){
		if (x.right == null) return x;
		return max(x.right);
	}

//*********** Testing *****************

    public static void main (String[] args){
        BST<String, Integer> test = new BST<>();

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
        System.out.println("The rank for letter E is: " + test.rank("E"));
        System.out.println("The ceiling for D is: " + test.ceiling("D"));
        System.out.println("The floor for D is: " + test.floor("D"));
        System.out.println("The rank-4 key is: " + test.select(4));
        System.out.println("F's value is: " + test.get("F"));
        System.out.println("The min key is: " + test.min());
        System.out.println("The max key is: " + test.max());

        System.out.println();
        test.deleteMin();
        test.deleteMax();
        test.delete("E");
        System.out.println("Deleted max and min and E.");
        System.out.println("The new table looks like: ");

        for (String s: test.keys()){
            System.out.println(s);
        }
    }
       
}

    
