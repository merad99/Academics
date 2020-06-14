// For cs310 pa4 Boston metro graph 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.*;

public class MetroGraph2 {

	private class Node {
		String name;
		int nodeId;
		String trainLine;
		double lat, lon;
		int inNodeId, outNodeId; // not needed once have G
	}

	private static final int MAXNODES = 200;
	Node[] nodes = new Node[MAXNODES];
	Graph G;
	EdgeWeightedDigraph DG;

	public MetroGraph2(String filePath) throws FileNotFoundException {
		Scanner in = null;
		in = new Scanner(new File(filePath));
		// placeholder Node for spot 0
		Node n0 = new Node();
		n0.name = "fake node";
		n0.trainLine = " ";
		nodes[0] = n0;
		int nodeId = 1;
		while (in.hasNextLine()) {
			String line1 = in.nextLine();
			String[] tokens = line1.split(",");
			int thisStation = Integer.parseInt(tokens[0]);
			int thisNodeId = nodeId++;
			// System.out.println("doing station " + thisStation + " node " + thisNodeId);
			assert thisNodeId == thisStation : "bad original nodeId";
			Node node = new Node();
			nodes[thisNodeId] = node;
			node.nodeId = thisNodeId;
			node.name = tokens[1];
			node.trainLine = tokens[2];
			node.inNodeId = Integer.parseInt(tokens[3]);
			node.outNodeId = Integer.parseInt(tokens[4]);
			if (tokens.length > 5 && !tokens[5].isEmpty())
				node.lat = Double.parseDouble(tokens[5]);
			if (tokens.length > 6 && !tokens[6].isEmpty())
				node.lon = Double.parseDouble(tokens[6]);
		}
		in.close();
		int nV = nodeId; // including fake node 0
		G = new Graph(nV);
		DG = new EdgeWeightedDigraph(nV);
		// Connect nodes together by inNodeIds and outNodeIds
		for (int i = 1; i < nV; i++) {
			Node n = nodes[i];
			if (n.outNodeId > 0) {
				// 0 at end-of-trainLine
				G.addEdge(n.nodeId, n.outNodeId);
				DG.addEdge(new DirectedEdge(n.nodeId, n.outNodeId, 1.0));
				DG.addEdge(new DirectedEdge(n.outNodeId, n.nodeId, 1.0));	
			}	
			if (n.inNodeId > 0) {
				G.addEdge(n.nodeId, n.inNodeId);
				DG.addEdge(new DirectedEdge(n.nodeId, n.inNodeId, 1.0));
				DG.addEdge(new DirectedEdge(n.inNodeId, n.nodeId, 1.0));
	
			}
				
		}
		
		// connect the nodes for one station all together
		// with edges for transfers
		Set<String> stations = new HashSet<String>();
		for (int i = 1; i < nV; i++)
			stations.add(nodes[i].name);
		for (String st : stations) {
			// Find all nodeIds for station
			Set<Integer> nodeIds = new HashSet<Integer>();
			for (int i = 1; i < nV; i++) {
				if (nodes[i].name.equals(st))
					nodeIds.add(i);
			}
			if (nodeIds.size() > 1) {
				// case of multiple nodes for station: link together
				for (int i : nodeIds)
					for (int j : nodeIds)
						if (i < j) {
							G.addEdge(i, j);
							DG.addEdge(new DirectedEdge(i, j, 7.0));
							DG.addEdge(new DirectedEdge(j, i, 7.0));
						}
							
			}
		}
		System.out.println("orig graph(G) #edges =" + G.E());
		deDup();
		System.out.println("after dedup, graph(G) #edges =" + G.E());
	}

	public Graph getGraph() {
		return G;
	}
	
	public EdgeWeightedDigraph getEdgeWeightedDigraph(){
		return DG;
	}
	
	// check that an edge between nodes x and y would be reasonable.
	// Edges should be between nodes on the same trainLine or
	// nodes at the same station, so check if neither is true
	// but allow a RedA station to connect directly to a Red station
	// because that's just following a split, no rider transfer needed.
	private void ckEdge(int x, int y) {
		// check edge: OK to match RedA with Red and GreenC with Green
		// since these connections allow trains to go through, no transfer
		if (!nodes[x].name.equals(nodes[y].name)
				&& !nodes[x].trainLine.substring(0, 3).equals(nodes[y].trainLine.substring(0, 3))) {
			System.out.println("link connects different stations on different lines: " + nodes[x].name + " on "
					+ nodes[x].trainLine + " and " + nodes[y].name + " on " + nodes[y].trainLine);
		}
	}

	// named SimpleEdge to be different from Edge of book
	private class SimpleEdge {
		int x, y;

		SimpleEdge(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object other) {
			// code on pg. 103, adapted
			if (this == other)
				return true;
			if (other == null)
				return false;
			if (this.getClass() != other.getClass())
				return false;
			SimpleEdge o = (SimpleEdge) other;
			return x == o.x && y == o.y || x == o.y && y == o.x;
		}

		@Override
		public int hashCode() {
			return Integer.valueOf(x).hashCode() & Integer.valueOf(y).hashCode();
		}

	}

	private void deDup() {
		Set<SimpleEdge> edges = new HashSet<SimpleEdge>();
		for (int i = 1; i < G.V(); i++) {
			for (int j : G.adj(i)) {
				if (i < j) {
					edges.add(new SimpleEdge(i, j));
				} else if (i > j) {
					edges.add(new SimpleEdge(j, i)); // smaller first
				} else
					System.out.println("Didn't expect i->i edge, i = " + i);
			}
		}
	    G = new Graph(G.V());  // recreate it
		for (SimpleEdge e : edges) {
			ckEdge(e.x, e.y);
			G.addEdge(e.x, e.y);		
		}
	}

	public Map<Integer, Platform> getPlatformData() {
		Map<Integer, Platform> platforms = new HashMap<Integer, Platform>();
		// System.out.println("G.V =" + G.V());
		for (int i = 1; i < G.V(); i++) {
			Platform p = new Platform(nodes[i].name, nodes[i].trainLine, nodes[i].lat, nodes[i].lon, nodes[i].nodeId);
			// System.out.println("getPlatformData: " + p.getNodeId());
			platforms.put(p.getPlatformId(), p);
		}
		return platforms;
	}
	
	void printLine(String line) {
		Set<String> StationNames = new HashSet<String>();
		for (int i = 1; i < G.V(); i++) {
			if (nodes[i].trainLine.equals(line))
				StationNames.add(nodes[i].name);
		}
		Set<String> treeSet = new TreeSet<String>(StationNames); 
		//Collections.sort(StationNames);
		System.out.print(treeSet);
	}
	
	public Platform endStation(String line) {
		String stationName = "";
		int id = 0;
		double lon = 0;
		double lat = 0;
		for (int i = 1; i < G.V(); i++) {
			
			if (nodes[i].trainLine.equals(line) && nodes[i].lat > lat) {

				stationName = nodes[i].name;
				id = nodes[i].nodeId;
				lat = nodes[i].lat;
				lon = nodes[i].lon;
			}
				
		}
		
		return new Platform(stationName, line, lat, lon, id);	
		
	}
	
	public Set<String> findAllLines() {
		Set<String> LinesNames = new HashSet<String>();
		for (int i = 1; i < G.V(); i++)
			LinesNames.add(nodes[i].trainLine);
			
		return LinesNames; 
	}
	

	public static void main(String[] args) {
		MetroGraph2 mG = null;
		try {
			mG = new MetroGraph2(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + args[0]);
		}
		Graph G=mG.getGraph();
		System.out.println(G.toString());
		System.out.println("Graph G has "+ G.V()  + " vertices, including unused vertex 0");
		Map<Integer,Platform> platformMap = mG.getPlatformData();
		Platform p = platformMap.get(1);
		System.out.println("Platform 1 is for station "+ p.getStationName());	
		
		for (String i : mG.findAllLines()) {
			System.out.println();
			System.out.print(i + " : "); mG.printLine(i);
			System.out.println();
		}
		
	}
}
