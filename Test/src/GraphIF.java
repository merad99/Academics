
public interface GraphIF {
	
	int V();
	int E();
	void addEdge(int v, int w);
	Iterable<Integer> adj(int v);


}
