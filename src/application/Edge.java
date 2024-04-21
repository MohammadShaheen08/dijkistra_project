package application;



public class Edge {
	private Vertex Source;
	private Vertex Destination;
	private double Weight;

	public Edge(Vertex source, Vertex destination, double d) {
		super();
		Source = source;
		Destination = destination;
		Weight = d;
	}

	public Vertex getSource() {
		return Source;
	}

	public void setSource(Vertex source) {
		Source = source;
	}

	public Vertex getDestination() {
		return Destination;
	}

	public void setDestination(Vertex destination) {
		Destination = destination;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

}