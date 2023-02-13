package search_algorithms;

import java.util.ArrayList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class DepthFirst extends SearchAlgorithm {
	private List<Node> result = new ArrayList();
	private List<Node> visited = new ArrayList();
	private boolean found = false;
	
	public DepthFirst(Graph graph) {
		super(graph);
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		search(n_initial, n_final);
		
		//No caso do algoritmo não conseguir encontrar um caminho, devolve uma Node com o label que explica a situção
		if(!found) {
			result.clear();
			result.add(new Node("The algorithm did not find a path from " + n_initial.getLabel() + " to " + n_final.getLabel()));
		}
		
		return result;
	}
	
	private void search(Node n_initial, Node n_final) {
		if(found || visited.contains(n_initial))
			return;
		visited.add(n_initial);
		result.add(n_initial);
		
		if(n_initial.equals(n_final)) {
			found = true;
			return;
		}
		
		List<Edge> adjNodes = adjacencyOfNode(n_initial);
		if(adjNodes==null) 
			result.remove(n_initial);
		else 
			for(Edge e : adjNodes)
				search(e.getN1(),n_final);
		
		if(!found)
			result.remove(n_initial);
	}

}

