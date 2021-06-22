# Directed Graphs
**Table of Content**
- [Directed Graphs](#directed-graphs)
  * [Cycle Detection](#cycle-detection)
  * [DAGs and Topological Sort](#dags-and-topological-sort)
  * [Kosaraju's Algorithm](#kosarajus-algorithm)
    + [Proofs](#proofs)
  * [All-Pair Reachability](#all-pair-reachability)

Directions can have profound implication to graph processing. However, the implementation of a digraph is very simple. Like undirected graphs, we want to use adjacency lists to represent our data structure. The only modification we need to make is to only add a pair of vertices once in our `addEdge` method (in an undirected graph, we have to add v-w and w-v both).

However, if we call `adj(v)`, we will only get vertices that `v` is point to, not from. So, we might as well add a `reverse()` function that returns a copy of the graph with all directions reversed so that `adj(v)` on this reversed graph will give us all vertices pointing to `v`.

```java
public Digraph reverse() {
    Digraph R = new Digraph(V);
    for (int v = 0; v < V; v++) {
        for (int w: adj(v)) addEdge(w, v);
        }
    return R;
    }
```

Another query problem might be "given a set of sources, find any of the sources can reach v". So, we can just modify our DFSPathFinding algorithm to achieve this by just adding a new constructor:

```java
public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s: sources){
                if (!marked[v]) dfs(G, s);
            }
    }
```

And we can just call `marked(v)` to find out if the query vertext `v` is reacheable from our sources:

```java
for (int s: sources) {
    if (marked(v))
    {"do something like shouting out s can reach v"}
    }
```

## Cycle Detection

For scheduling problems or anything with that involves precedence constraints, we need to detect if there's any cycle in the graph to see if the graph is valid to represent such problem at hand. We can write a `DirectedCycle` class to do this. If there's a cycle, we want to potentially retrieve the cycle's path. Therefore, a new array called `onStack[]` of boolean values is needed. That is because the algorithm is based on DFS, a recursive function. So, we can use `onStack` to keep track of vertices that are still on the call stack maintained by the system. If we encounter a vertex both on stack and marked, then we've detected a cycle.

```java
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++){
            if (!marked[v]) dfs(G, v);
            }
        }

    private void dfs(Digrpah G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w: adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w])
            { edgeTo[w] = v; dfs(G, w);}
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x)
                cycle.push(w);
                cycle.push(v);
                }
            }
        onStack[v] = false;
        }

    public boolean hasCycle() {return cycle != null;}
    public Iterable<Integer> cycle() {return cycle;}
    }
```
## DAGs and Topological Sort

These two concepts go together. We can only perform a topological sort if a graph is a directed acyclic graph (DAG). To determine if a graph is a DAG, we simply run the `DirectedCycle` algo to see if any cycle exists. If not, we can traverse the graph to store the paths in some data structures, and the topological sort is done by simply return one of the data structures we decide to use. There are basically three orderings when traversing a directed graph:

+ *pre-order*: store the current vertex to a queue before the recursion call
+ *post-order*: store the current vertex to a queue after the recursion call
+ *reverse post-order*: store the current vertex to a stack after the recursion call

```java
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirst(Digraph G) {
        marked = new boolean[G.V()];
        pre = new Queue<Integer>();  
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre.enqueue(V);
        
        for (int w: adj(v)){
            if (!marked[w]) dfs(G, w);
            }
        
        post.enqueue(v);
        reversePost.push(v);
        }
    
   public Queue<Integer> pre() {return pre;}
   public Queue<Integer> post() {return post;}
   public Stack<Integer> reversePost() {return reversePost;}
```
Now, we can write the topological sort class:

```java
public class TopologicalSort {
    private Iterable<Integer> order;

    public TopologicalSort(Digraph G) {
        DirectedCycle checkCycle = new DirectedCycle(G);
        if (!checkCycle.hasCycle()) {
            DepthFirstOrder sort = new DepthFirstOrder(G); 
            order = sort.reversePost(); 
            }
        }
    public Iterable<Integer> order() {return order;}
    public boolean isDAG() {return order != null;}
    }
```
## Kosaraju's Algorithm

For undirected graph we have the CC class to get connected components in a graph. For digraphs, we have the concept of *strong* components. A strong component contains vertices that can reach any other vertices in that component. Kosaraju's algorithm allows us to find strong components in linear runtime proportional to *V + E* by just making slight modifications to the CC class.

In short, the algorithm has the following steps:
1. Create the reverse digraph G' and get its order using reverse post-order traversal
2. Run DFS on the original graph G but in an order of that reverse post-order traversal of G'
3. All vertices reached on a call to the dfs() from the *constructor* are in strong component, so we can mark them in our `id[]` array

```java
public class SCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public SCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int v: order.reversePost())
            if (!marked[v])
               { dfs(G, s); count++; }
        }
    "the rest is the same as CC implemented in undirected graphs"
    }
```

### Proofs
Its simple implementation does not overshadow the complexity involved in understanding of such algorithm. We can prove this algo using two ways. The first is to prove by contradiction:

> **Proof**: every vertex *v* that is strongly connected to *s* is reached by the call `dfs(G, s)` in the constructor.
> **Prove by contradiction**: suppose there exists a vertex *v* strongly connected to *s* that is not reached by the call `dfs(G, s)`. We know that if there is a path from *s* to *v*, then that means *v* is marked. But if path *v->s* exists,  we would have marked *s* on the way when calling `dfs(G, v)`, so we will never call `dfs(G, s)` in the constructor. Contradiction.
 
The second way involves cases:

> **Proof**: every vertex *v* that is strongly connected to *s* is reached by the call `dfs(G, s)` in the constructor.
> **Prove by cases**: suppose that *v* is reached by *s* when calling `dfs(G, s)`, that means there is a path from *s* to *v* in G, we just need to prove that there's also a path from *v* to *s*.  We can apply the same logic to G': if there's a path from *v* to *s* in G', then we need to prove that there's also a path from *s* to *v* in G'. 
> 
> It's important to note that since we use the reverse postorder structure,  `dfs(G, v)` must be done before `dfs(G, s)` is done in G', so that will give us two cases to consider for `dfs(G, v)`, which must have been called:
> + *Before* `dfs(G, s)` was called and was done before `dfs(G, s)` was called
> + *After* `dfs(G, s)` was called and was done before `dfs(G, s)` was done
> 
> The first case is impossible because there is a path from *v* to *s* in G', and the second implies that there is a path from *s* to *v*, which satisfies what we need to complete the proof.

## All-Pair Reachability

Sometimes we want to see if there is a path exists from any vertex *v* to *s*, and we want to perform such query in constant time. However, unlike an undirected graph, we need to run `DirectedDFS` everytime, which takes V + E runtime, to find our answer. So far, the solution is to pre-compute all vertices in a digraph to support the constant time query, at the cost of V^2 space and V(V+E) runtime at the set-up.

We can write a class `TransitiveClosure` to achieve this. Note that Transitive Closure means a copy of a digraph with the same vertices but with all edges added to every vertex *v* and every other *w*, if and only if *v* has a path to *w*.

```java
public class TransitiveClosure {
    private DirectedDFS[] all;
    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) 
            all[v] = new DirectedDFS(G, v);
        }

    boolean reachable(int v, int w)
    { return all[v].marked(w); }
    }
```

