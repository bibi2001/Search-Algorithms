package search_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class BreadthFirst extends SearchAlgorithm {
	private Map<Node, Node> parents = new HashMap();
	private List<Node> visited = new ArrayList();
	private boolean found = false;
	private final int numberOfNodes = getGraph().numberOfNodes();

	
	public BreadthFirst(Graph graph) {
		super(graph);
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		visited.add(n_initial);
		search(adjacencyOfNode(n_initial), n_final);
		
		if(found) 
			return getResult(n_initial, n_final);
		
		//No caso do algoritmo não conseguir encontrar um caminho, devolve uma Node com o label que explica a situção
		visited.clear(); 
		visited.add(new Node("The algorithm did not find a path from " + n_initial.getLabel() + " to " + n_final.getLabel()));
		return visited;
		
	}
	
	private void search(List<Edge> nodes, Node n_final) {
		if(found || nodes==null || numberOfNodes==visited.size())
			return;
		
		for(Edge e : nodes) {
			parents.put(e.getN1(), e.getN0());
			if(e.getN1().equals(n_final)) {
				found = true;
				return;
				}
			}
		search(adjacencyOfAllNodes(nodes), n_final);
	}
	
	//Função auxiliar para juntar todos os "filhos" de uma lista de Nodes numa lista 
	private List<Edge> adjacencyOfAllNodes(List<Edge> nodes){
		List<Edge> aux = new ArrayList();
		for(Edge e:nodes)
			if(adjacencyOfNode(e.getN1())!=null && !visited.contains(e.getN1())) {
				aux.addAll(adjacencyOfNode(e.getN1()));
				visited.add(e.getN1());
			
		}
		return aux;
	}

	//Função auxiliar para passar do map parents para uma lista com os Nodes corretos a partir do objetivo
	private List<Node> getResult(Node n_initial, Node n_final) {
		List<Node> result = new ArrayList<Node>();
		Node aux = n_final;
		while(aux!=n_initial) {
			result.add(aux);
			aux=parents.get(aux);
		}
		result.add(n_initial);
		Collections.reverse(result);
		return result;
	}
}
	
