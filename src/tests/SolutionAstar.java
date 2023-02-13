package tests;

import graph_utils.*;
import search_algorithms.*;

public class SolutionAstar {
	public static void main(String[] args) {
		/*
		 * TO DO Aqui deves: 
		 * 		- criar a instancia do grafo que representa o mapa das
		 * 		masmorras (ver classe Exemplo.java) e explicar a funcao heuristica escolhida
		 * 		bem como os custos associados aos arcos (edges) 
		 * 		- colocar o cï¿½digo para testar o algoritmo criado
		 * 
		 */

		Graph graph = new Graph();
		/* a heuristica utilizada foi baseada no numero de quadrados aproximado de distância do respetivo Node ao Node final (neste caso o n11)
		 * esta maneira é muito pouco eficiente e prática, pelo que se a masmorra fosse muito maior e, consequentemente, tivesse muito mais salas,
		 * seria quase impossível calcular a heuristica desta maneira. Se houvesse um mapa com as coordenadas das salas, ou seja se cada Node
		 * tivesse como atributo as suas coordenadas x e y pelo menos, daria para calcular a heuristica com uma função que medisse
		 * a distância entre o Node em questão e o Node final! A função seria algo do género (sem ter atenção a excessoes):
		 * 		public void setHeuristic(Node n_final) {
		 * 			double x = (n_final.getX()-getX())
		 * 			double y = (n_final.getY()-getY())
		 *			this.heuristic = Math.sqrt((x*x)+(y*y));
		 * 		}
		 */
        Node n1 = new Node("1", 2);
        Node n2 = new Node("2", 14);
        Node n3 = new Node("3", 13);
        Node n4 = new Node("4", 27);
        Node n5 = new Node("5", 1);
        Node n6 = new Node("6", 31);
        Node n7 = new Node("7", 18);
        Node n8 = new Node("8", 14);
        Node n9 = new Node("9", 9);
        Node n10 = new Node("10", 30);
        Node n11 = new Node("11", 0);
        
        //o custo dos edges foi calculado a contar os quadrados de distância de uma sala à outra
        graph.addEdge(n6, n10, 1);
        graph.addEdge(n10, n4, 3);
        graph.addEdge(n4, n3, 15);
        graph.addEdge(n3, n9, 4);
        graph.addEdge(n3, n11, 1);
        graph.addEdge(n9, n7, 9);
        graph.addEdge(n9, n11, 9);
        graph.addEdge(n4, n8, 13);
        graph.addEdge(n8, n5, 13);
        graph.addEdge(n5, n1, 1);
        graph.addEdge(n5, n2, 13);
        graph.addEdge(n5, n11, 1);

        System.out.println("-----ASS-----");
        SearchAlgorithm dfsAlg = new Astar(graph);
	    dfsAlg.printResult(dfsAlg.startSearch(n6, n11));
	}
}
