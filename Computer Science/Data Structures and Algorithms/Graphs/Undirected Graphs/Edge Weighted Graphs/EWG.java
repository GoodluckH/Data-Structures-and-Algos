import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.In;

public class EWG {
    private ST<Integer, Double>[] adj;
    private final int V;
    private int E;

    public EWG(int V) {
        this.V = V; this.E = 0;
        adj = (ST<Integer, Double>[]) new ST[V];
        for (int v = 0; v < V; v++) adj[v] = new ST<Integer, Double>();
    }

    public EWG(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double e = in.readDouble();
            addEdge(v, w, e);
        }
    }

    public int V() {return V;}
    public int E() {return E;}

    public void addEdge(int v, int w, double e) {
        adj[v].put(w, e);
        adj[w].put(v, e);
        E++;
    }
    
    public ST<Integer, Double> adj(int v) { return adj[v]; }

    public void toS() {
        for (int v = 0; v < V; v++) {
            System.out.print(v + " -- ");
            ST<Integer, Double> st = adj(v);
            for (int k: st) {
                    System.out.print("(" + k + ", " + st.get(k) +")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EWG EG = new EWG(in);
        EG.toS();
                
    }
}
