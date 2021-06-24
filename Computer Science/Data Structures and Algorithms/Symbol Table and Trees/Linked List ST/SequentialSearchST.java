import java.util.Stack;

//***************************
//
// An implementation of Symbol Table using linked list.
//
//***************************


public class SequentialSearchST<Key, Value>{
	private Node first;
	private int size;

	public SequentialSearchST(){
		this.size = 0;
		this.first = new Node(null, null,null);
	}
	
	private class Node{
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key, Value val, Node next){
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public void put(Key key, Value val){
		if (key == null) throw new IllegalArgumentException();
		if (val == null) {delete(key); return;}
		for (Node x = first.next; x != null; x = x.next)
			if (x.key.equals(key)) 
			{x.val = val; return;}
		first.next = new Node(key, val, first.next);
		size++;	
	}
	
	public Value get(Key key){
		for (Node x = first.next; x != null; x = x.next)
			if (x.key.equals(key))
				{return x.val;}
		return null;
	}


	public boolean contains(Key key) {
		if (get(key) != null) return true;
		return false;
	}

	public boolean isEmpty(){return size() == 0;}

	public void delete(Key key) {
		if (!contains(key)) return;

		for (Node x = first; x.next != null; x = x.next)
			if (x.next.key.equals(key))
			{
				x.next = x.next.next;
			}
		size--;
	}

	public int size() {return size;}
	
	public Iterable<Key> keys(){
		Stack<Key> q = new Stack<Key>();
		for (Node x = first.next; x != null; x = x.next){
			q.add(x.key);
		}
		return q;
	}

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> a = new SequentialSearchST<>();
		a.put("One", 1);
		a.put("Two", 2);
		a.put("Three", 3);
		a.put("Four", 4);
		a.put("Five", 5);
		a.put("Six", 6);
		a.put("Seven", 7);

		for (String s: a.keys()){
			System.out.println(s + " "+ a.get(s));
		}
		
		a.delete("Four");
		a.put("Five", 55555);
		
		for (String s: a.keys()){
			System.out.println(s + " "+ a.get(s));
		}
		System.out.println("Contains Six? " + a.contains("Six"));
		System.out.println("Size should be 6. Actual size: " + a.size());

	}
}
