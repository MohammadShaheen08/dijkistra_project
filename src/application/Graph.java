package application;

import java.util.LinkedList;

import javafx.scene.shape.Circle;

public class Graph {
	private Vertex VList[]; // array of vertex
	private Edge EList[]; // array of edge
	private LinkedList<Edge> adj_list[];
	private int NumOfVerticies;
	private int NumOfEdges;
	private static int i = 0;
	private static int j = 0;

	public Graph(int v, int e) {
		super();
		NumOfVerticies = v;
		NumOfEdges = e;
		VList = new Vertex[NumOfVerticies];
		EList = new Edge[NumOfEdges];
		adj_list = new LinkedList[NumOfVerticies];
	}

	public void addVertex(Vertex v) {
		VList[i] = v;
		adj_list[i] = new LinkedList();
		adj_list[i++].addFirst(new Edge(v, v, 0));
	}

	public void addEdge(Edge e, int ind1, int ind2) { // Gaza>>--Bit-hanoun 
		EList[j++] = e;

		adj_list[ind1].addLast(e);
		adj_list[ind2].addLast(new Edge(e.getDestination(), e.getSource(), e.getWeight()));
	}

	public int getNumOfVerticies() {
		return NumOfVerticies;
	}

	public int getNumOfEdges() {
		return NumOfEdges;
	}

	public LinkedList<Edge>[] getAdj_list() {
		return adj_list;
	}

	public void setAdj_list(LinkedList<Edge>[] adj_list) {
		this.adj_list = adj_list;
	}

	public Vertex[] getVList() {
		return VList;
	}

	public void setVList(Vertex[] vList) {
		VList = vList;
	}

	public Edge[] getEList() {
		return EList;
	}

	public void setEList(Edge[] eList) {
		EList = eList;
	}
}
