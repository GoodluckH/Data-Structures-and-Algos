import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class SP {
    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final IndexPQ<Double> pq;
    
    public SP(EWD G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexPQ<Double>(G.V());

        for (int i = 0; i < distTo.length; i++)
            {distTo[i] = Double.POSITIVE_INFINITY;}
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    private void relax(EWD G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, e.weight());
            }
        }
    }

    public boolean hasPathTo(int v) {return distTo[v] < Double.POSITIVE_INFINITY;}

    public double distTo(int v) {return distTo[v];}

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args)
  {
     EWD G;
     G = new EWD(new In(args[0]));
     int s = Integer.parseInt(args[1]);
     SP sp = new SP(G, s);
     for (int t = 0; t < G.V(); t++)
     {
        StdOut.print(s + " to " + t);
        StdOut.printf(" (%4.2f): ", sp.distTo(t));
        if (sp.hasPathTo(t))
           for (DirectedEdge e : sp.pathTo(t))
              StdOut.print(e + "   ");
        StdOut.println();
     }
}
}
