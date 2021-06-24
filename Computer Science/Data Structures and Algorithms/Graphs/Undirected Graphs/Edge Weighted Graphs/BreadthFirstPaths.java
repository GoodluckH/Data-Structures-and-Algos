import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
	private final int s;
    
	public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
		this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        Queue<Integer> queue = new Queue<Integer>();
   		queue.enqueue(v);
		
		while (!queue.isEmpty()) {
			int w = queue.dequeue();
			for (int x: G.adj(w)) {
				if (!marked[x]){
					marked[x] = true;
					edgeTo[x] = w;
					queue.enqueue(x);
					}
				}
			}
    	}

	public boolean hasPathTo(int v) { return marked[v]; }
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) 
			path.push(x);
		path.push(s);
		return path;	
	}
}
