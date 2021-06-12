# Undirected Graphs

Undirected graphs are simply made of vertices and edges with no extra information. We can only know if any two vertices are connected or not. We want to represent an undirected graph using data structures that are space and time efficient. There are a couple of candidates:

*An adjacency matrix*: we construct a V by V 2D-array with boolean values to represent whether two vertices are adjacent. However, the space requirement is V^2, which is very inefficient for large-scale applications like Facebook. More, we can only see if two vertices are connected but cannot see if there are parallel edges.

*An array of edges*: we can create an Edge class with two `int` instance variables denoting the vertices at either end of the edge, and store these Edge classes in an array (note that an array is fixed-sized, you initiate it by saying int[n]; a list, on the other hand, is a generic collection with no size limits because it automatically resizes). If we want to find something, though, we need to iterate through everything, which will be slow.

*An array of adjacency list*: we have an array of lists that store the vertices connected to a specific vertice. The array indices correspond to the vertice number, so we can quickly find a vertice and check its adjacent vertices. We can use `Bag` ADTs to represent the adjacency list. Note that a `Bag` is implemented using linked list, so that means it takes constant time to add an edge. And the space requirement would be proportional to `V + E`, to get adjacent vertices, it simply takes `degree(v)` time. 

*A symbol table of adjacency list*: using a symbol table allows us to add and delete a vertex, and frees us from the restriction of representing vertices using integers only.

We can also use `Set` to implement our adjacency list to disallow parallel edges. However, it will comes at logarithmic costs for adding edges. 

**Performance Summary**

| Data Structure | Space |Add Edge v-w |Check if v-w | adj(v) |
| :-----------: | :-----------: |:-----------: |:-----------: |:-----------: |
| *List of edges* | E | 1 | E | E |
| *Adjacency matrix* | V^2 | 1 | 1 | V |
| *Adjacency lists* | V + E | 1 | degree(v) | degree(v)|
| *Adjacency sets* | V + E | log(V) | log(V) | log(V) + degree(v)|

##Undirected Graph Implementation using Adjacency Lists##

*requires algs4.jar for Bag*.

```java
public class Graph {
    private final int V;         // number of vertices
    private int E;               // number of edges
    private Bag<Integer>[] adj;  // adjacency lists  

    public Graph(int V) {
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // build an array of bag objects
        for (int v = 0; v < V; v++)         // for every slot
            adj[v] = new Bag<Integer>();    // build an empty bag
        }
    
    public Graph(In in) {
        this.(in.readInt());             // get V and constract the graph
        int E = in.readInt();            // get E
        for (int i= 0; i < E; i++) {
            int v = in.readInt();
            int w = int.readInt();
            addEdge(v, w);
            }
        }

    public int V() {return V;}
    public int E() {return E;}

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
        }

    public Iterable<Integer> adj(int v) 
    { return adj[v];}
    }
```

# Depth-First Search


