import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EWD {
    private final int V;
    private int E;
    private final Bag<DirectedEdge>[] adj;
    
    public EWD(int V) {
        this.V = V; this.E = 0;
        adj =  (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EWD(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int e = 0; e < E; e++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(v, w, weight);
        }
    }

    public int V() {return V;}
    public int E() {return E;}

    public void addEdge(int v, int w, double weight) {
        DirectedEdge e = new DirectedEdge(v, w, weight);
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {return adj[v];}
}
