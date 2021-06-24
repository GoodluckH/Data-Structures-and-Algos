import java.util.NoSuchElementException;

public class IndexPQ<Key extends Comparable<Key>> {
    private final Key[] keys;
    private final int[] pq;
    private final int[] qp;      // return the index position of an index in pq;
    private int size;

    public IndexPQ(int s) {
        keys = (Key[]) new Comparable[s + 1];
        pq = new int[s + 1];
        qp = new int[s + 1];
        for (int i = 0; i <= s; i++)
            {qp[i] = -1;}
        size = 0;
    }

    public boolean contains(int i) {return qp[i] != -1;}

    public int delMin() {
        int min = pq[1];
        swap(1, size--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[size+1] = -1;        
        return min;
    }

    public boolean isEmpty() {return size == 0;}

    public int parent(int k) {
        return k / 2;
    }

    public void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    public int size() {return size;}

    public void insert(int k, Key key) {
        size++;
        keys[k] = key;
        qp[k] = size;
        pq[size] = k;
        swim(size);
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public void swim(int k) {
        while (k > 1 && greater(parent(k), k)) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    public void change(int k, Key key) {
        if (!contains(k)) throw new NoSuchElementException("no such key");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }
    
    private void swap(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        IndexPQ<Double> pq = new IndexPQ<>(5);
        pq.insert(1, 0.3);
        pq.insert(2, 2.3);
        pq.insert(3, 1.5);
        pq.insert(4, 0.1);
        for (int i: pq.pq){
            System.out.print(i + " ");
        }
        System.out.println("size: " + pq.size());
        pq.change(4, 5.0);
        System.out.println("size: " + pq.size());
        System.out.println(" ");



        for (int i: pq.pq){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        System.out.println(pq.delMin());
        System.out.println(" ");
        pq.change(2, 0.1);
        System.out.println(pq.delMin());
    }
}
