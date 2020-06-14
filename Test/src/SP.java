import edu.princeton.cs.algs4.DirectedEdge;

public interface SP {
	
	double distTo(int v);
	boolean hasPathTo(int v);
	Iterable<DirectedEdge> pathTo(int v);

}
