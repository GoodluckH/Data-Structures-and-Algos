# Minimum Spanning Tree
If we are dealing with undirected graphs with weighted edges, then we want to find out the tree in the graph whose sum of edges is no larger than other tree subgraphs.

To do this, we need to first define an `Edge` object:

```java
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v; this.w = w; this.weight = weight;
        }

    public double weight() {return weight;}
    public int either() { return v; }
    public int other(int v) {
        if (v == this.v) return w;
        else return v;
        }

    public int compareTo(Edge that) {
        return Double.compareTo(this.weight(), that.weight());
        }    
    }
```

Then, all we need to do is to create an edge weighted graph class that slightly modifies our undirected graph implementation.

```java
public class EWG {
    private final int V;
    private int E;
    private Bag<Edge> adj[];

    public EWG(int V) {
        this.V = V;
        this.E = 0;
        adj[] = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) 
            adj[v] = new Bag<Edge>();
        }
    
    public int V() {return V;}
    public int E() {return E;}
    
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
        }
    
    public Iterable<Edge> adj(int v) {return adj[v];}
    
    public Iterable<Edge> edges() {
        Bag edges = new Bag<>();
        for (int v = 0; v < V; v++) 
            for (Edge e: adj(v))
                if (e.other(v) > v) edges.add(e);
        return edges;
        }

    }
```

Now we are ready to implement an algorithm to find MSTs. But before that, it's worth mentioning that there's something called *cut property* in a graph. Basically, we can arbitrarily partition the graph into two connected sets, and there may be several cross-edges connecting two sets. The cut property says that the cross-edge with the minimal weight must be a path on the minimal spanning tree.

So, using this property, we can basically color the MST edges in black and keep finding cuts that don't have have black edge as cross-edge until *V - 1* edges have been colored. Why *V - 1*? Well, the resultant is a tree, and to qualify as a tree, there must be V - 1 edges connecting all vertices with no cycles. Adding one edge would result in a cylce, and deleting one edge would separate the tree into two smaller trees.


## Prim's Algorithm
### Lazy Approach
We can first try a *lazy* implementation of MST using Prim's Algorithm. Basically, we need to maintain a boolean array `marked` to keep track of whether a vertex has been visited, a queue that stores all edges in the tree, and a MinPQ to determine the next vertex to visit. 

What we end up with is an iterative solution where we visit a vertex, adds all eligible edges to the MinPQ, and delete the min to add it to the queue, and so on until our queue is empty.

```java
public class LazyPrimMST {
    private final boolean[] marked;
    private final Queue<Edge> mst;
    private final MinPQ<Edge> pq;

    public LazyPrimMST(EWG G) {
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        
        visit(G, 0);     // visit the first vertex

        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;    // eligibility check
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EWG G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Integer> mst() { return mst; }

    public double weight() {
        double weight = 0.0;
        for (Edge e: mst) {
            weight += e.weight();
        }
        return weight;
    }
}
```
The space requirement for such lazy implementation is proportional to E, and the runtime upper bound is *ElogE* because each insert or delete operation in MinPQ takes *~lgN* time, and the worst case is when we have to insert and delete for every edge. But in practice, the number of edges on MinPQ should be fewer than E.

### Eager Approach
The lazy approach would leave some ineligible edges on the MinPQ, and only takes care of them with the eligibility test. However, we can improve our solution by implementing an eager approach where we proactively delete the ineligible edges, so that our pq only maintains the crossing edges between tree vertices and non-tree vertices. 

So, we need to replace queue by distTo[] and edgeTo[], and we also need to change our MinPQ to IndexMinPQ to represent vertices. Basically, when we are adding edges to our pq, we need to see if the weight of an edge is lower than the current weight on distTo, we only add the edge that has the minimal weight. 

```java
public class PrimMST {
    private final Edge[] edgeTo;
    private final double[] distTo;
    private final IndexMinPQ<Double> pq;    // compared by weights
    private final boolean[] marked;

    public PrimMST(EWG G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(G, pq.delMin());
    }

    private void visit(EWG G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            int w = w.other(v);
            if (marked[w]) continue; 
            if(e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Interable<Edge> edges() {
        Stack<Edge> edges = new Stack<Edge>();
        for (int v = 1; v < G.V(); v++) 
            edges.push(edgeTo[v]);
        return edges;
    }

    public double weight(){
        double weight = 0.0;
        for (Edge e: edges()) {
            weight += e.weight();
        }
        return weight;
    }
}
```

The most significant improvement of the eager approach is our space requirement has reduced to proportional to V. The runtime will be proportional to `~ElogV` because our pq will have at max V items, and for `change` operation, it will take E times in worst case. 


## Kruskal's Algorithm

Another MST algorithm is the Kruskal's algorithm. Prim's algo checks each vertex's edges one by one, so it looks like a BFS when we trace the process. Kruskal's algo, however, checks edges by weights first. So, it starts with the minimal-weight edges, and to check if adding a new vertex would form a cycle. So, we need to use a Union Find data structure to help us determine cycles.

```java
public class KruskalMST {
    private final Queue<Edge> mst;

    public KruskalMST(EWG G) {
        mst = new Queue<Edge>();
        UF uf = new UF(G.V());
        MinPQ<Edge> pq = new MinPQ<Edge>(G.edges()); // add all edges to pq

        while (!pq.isEmpty & mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;    // check for eligibility
            uf.union(v, w);
            mst.enqueue(e);   
        }
    }
}
```

This simple implementation has a space requirement proportional to E, because we are adding all edges to a pq. The runtime will be proportional to `ElogE` in worst case. Although it seems similar to the laze approach of the Prim's Algorithm, it's slower in practice because we have to call `uf.connected()` every time. 

