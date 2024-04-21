package application;



import javafx.scene.shape.Circle;

public class Vertex {
	private int Index;
	private String Name;
	private double X;
	private double Y;
	private Circle c;

	public Vertex(int index, String name, double x, double y) {
		Index = index;
		Name = name;
		X = x;
		Y = y;
	}

	public Circle getC() {
		return c;
	}

	public void setC(Circle c) {
		this.c = c;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

}
