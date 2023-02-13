package search_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class Astar extends SearchAlgorithm{
	private boolean found = false;
	private Map<Node, Node> parents = new HashMap();
	private List<Node> discovered = new ArrayList();
	private List<Node> visited = new ArrayList();
	
	
	public Astar(Graph graph) {
		super(graph);
		// TODO Auto-generated constructor stub
	}
	
	//Comparador para determinar como vai ser escolhido o node correto
	class NodeComparator implements Comparator<Node> {
	    @Override
	    public int compare(Node n1, Node n2) {
	    	if(n1.getF()==n2.getF())
	    		return n1.getHeuristic()-n2.getHeuristic();
	        return n1.getF()-n2.getF();
	    }
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		discovered.add(n_initial);
		search(n_initial, n_final);
		
		if(found) {
			return getResult(n_initial,n_final);
		}
		
		//No caso do algoritmo não conseguir encontrar um caminho, devolve uma Node com o label que explica a situção
		discovered.clear();
		discovered.add(new Node("The algorithm did not find a path from " + n_initial.getLabel() + " to " + n_final.getLabel()));
		return discovered;
	}
	
	private void search(Node n_initial, Node n_final) {
		if(n_initial.equals(n_final)) {
			found=true;
			return;
		}
		if(found || visited.contains(n_initial))
			return;
		
		visited.add(n_initial);
		discovered.remove(n_initial);
		if(adjacencyOfNode(n_initial)!=null)
			for(Edge e:adjacencyOfNode(n_initial)) {
				parents.put(e.getN1(), e.getN0());
				discovered.add(e.getN1());
				e.getN1().setCost(e.getCost()+e.getN0().getCost());
			}
		
		if(discovered.size()>0){
			Collections.sort(discovered, new NodeComparator());
			search(discovered.get(0), n_final);
		}
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
