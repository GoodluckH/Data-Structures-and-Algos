# Shortest Paths

Just like what we did on an undirected graph using BFS, we want to find the shortest paths for an edge-weighted digraph (EWD). First of all, we need to modify the edge class to support directions:

```java
public class DirectedEdge {
    public DirectedEdge(int v, int w) {...}
    public int from() {return v;}
    public int to() {return w;}
}
```
With this, we can proceed to our shortest path finding algorithm using a concept called *relaxation*, which essentially test if the current distance to a vertex is the shortest:

```java
private void relax(DirectedEdge e) {
    int v = e.from(), w = e.to();
    if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
    }
}
```

Similarly, we can also relax a given vertex:

```java
private void relaxVertex(Digraph G, int v) {
    for (DirectedEdge e: G.adj(v)) {
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight) {
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
        }
    }
}
```
With these in mind, we can proceed with some shortest-path-finding algorithms. 

## Dijkstra's Algorithm

Interestingly, Dijkstra's algo is similar to Prim's algo using the eager approach. All we need to do is to visit each vertex in the priority queue, and perform the relaxation operation accordingly.

```java
public class SP {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final IndexPQ<Double> pq;

    public SP(EWD G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexPQ<Double>(G.V());

        for (int i = 0; i < G.V(); i++)
            distTo[i] = Double.POSITIVE_INIFINITY;
        
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        while(!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    private void relax(EWD G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY;}

    public double distTo(int v) {return distTo[v]; }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }
} 
```
It's easy to assess the runtime for Dijkstra's algorithm. Here, we are using a PQ that at worst costs *logV* in runtime due to sinking or swimming. And since our `relax` method checks for each vertex's edges, the worst time is when we perform the PQ operation for each edge, therefore incurring a total time complexity of *ElogV*.

So, in target finding scenario, we can just stop the algo once the target is deleted from the PQ.

Note that Dijkstra's algorithm does not apply for negative weights because if we have a path A -> B -> C, where B -> C is negative, but also have another path A -> B' that has a lower weight than A -> B, then Dijkstra's algo won't even bother to explore the A -> B -> C path.

## Acyclic Shortest Path

If our digraph does not have any cycles, then we can use topological sort to find the shortest path even when negative-weighted edges are present. To do this is quite simple:

```java
class AcyclicSP {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;

    public AcyclicSP(EWD G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        
        for (int i = 0; i < distTo.length; i++) 
            distTo[i] = Double.POSITIVE_INFINITY;
        
        distTo[s] = 0.0;
        
        TopologicalSort top = new TopologicalSort(G);

        for (int v: top.order())
            for (DirectedEdge e: G.adj(v))
                relax(e)
    }

    private void relax(DirectedEdge e) {
        int w = e.to(), v = e.from();
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
``` 

The runtime is linear because it takes V + E runtime to run the topological sort (post-order traversal), then we are visiting all edges to relax, therefore the total runtime is proportional to V + E.

## Bellman-Ford Algorithm

If a digraph has no negative cycles, then we can run the Bellman-Ford algorithm to solve our shortest-path finding problems. It's quite simple. We just need to relax all edges for V times. So the code is simple, too.

```java
for (int pass = 0; pass < G.V(); pass++)
    for (int v = 0; v < G.V(); v++)
        for (DirectedEdge e: G.adj(v))
            relax(e);
```
Bellman-Ford algorithm takes care of negative edges because every pass, we have the opportunity to update each vertex's weight. And this works quite like BFS but only for each vertex only in numeric order. The runtime is immediately **V*E**.