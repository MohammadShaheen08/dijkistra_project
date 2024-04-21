package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller implements Initializable {

	@FXML
	private AnchorPane ap; // ال Layout الي موجود فيها الصورة

	@FXML
	private ComboBox<String> cbSource; // التكست فيلد الي بنحط فيه السورس

	@FXML
	private ComboBox<String> cbDistnation; // التكست فيلد الي بنحط فيه الدستنيشن

	
	@FXML
	private Text tfDistance;// التكست الي راح نطبع فيه الدستنس

	@FXML
	private TextArea taPath;// التكست اريا الي راح نطبع فيه الباث (يعني المدن الي راح نمشي عليهم )

	private Vertex Source;// الاوبجيكت الي راح يكون لسورس
	private Vertex Destination;// الاوبجيكت الي راح اخزن فيه الدستنيشن
	Graph g;
	double MaxX=659;
	double MinX=0;
	double MaxY=767;
	double MinY=0;
	double MxMin=34.17670;
	double MyMin=31.61593;
	double MxMax=34.56977;
	double MyMax=31.19620000;
	  ObservableList<String> optionsList;
	public double getX(double Mx) {
		double x = ((((MaxX - MinX) * (Mx - MxMin)) / (MxMax - MxMin))) + MinX;
		System.out.println(x);
		return x;
	}

	public double getY(double My) {
		double x = ((((MaxY - MinY) * (My - MyMin)) / (MyMax - MyMin))) + MinY;
		System.out.println(x);

		return x;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		File f = new File("input.txt");// بقرأ الفايل الي فيه الداتا من هان

		try {
			Scanner sc;// اوبجيكت سكنر
			int status;
			sc = new Scanner(f);// بقرأ الملف باوبجيكت السكنر
			int NumOfVerticies = 0, NumOfEdges = 0;// عدد الفيرتكس وعدد الاجيز
			NumOfVerticies = sc.nextInt();// بوخذ اول اشي قيمة عدد الفيرتكس من الملف
			NumOfEdges = sc.nextInt();// هان بوخذ عدد الادجيز الموجودة بين المدن
			g = new Graph(NumOfVerticies, NumOfEdges);// بعمل اوبجيكت الجراف بعدد الفيرتكس والاندجيز
			sc.nextLine();//بوخذ هان السطر الثاني بالسكنر
			String split[];
			Vertex v;
			 optionsList = FXCollections.observableArrayList();
			
			for (int i = 0; i < NumOfVerticies; i++) {
				Circle c;
				split = sc.nextLine().split(" ");
				 
				v = new Vertex(i, split[0], Double.parseDouble(split[2]), Double.parseDouble(split[1]));
			
			
				c = new Circle();

				c.setRadius(3);
				c.setLayoutX(getX(v.getX()));
				c.setLayoutY(getY(v.getY()));
				c.setFill(Color.GRAY);
				Label l = new Label(split[0]);
				l.setFont(Font.font(10));
				l.setLayoutY(c.getLayoutY() - 6);
				l.setLayoutX(c.getLayoutX() + 8);
				if( Integer.parseInt(split[3])==1) {
					optionsList.add(split[0]);
				ap.getChildren().addAll(c, l);}
				
				c.setId(i + "");
				v.setC(c);
				g.addVertex(v);
				final Vertex v2 = v;
				cbSource.setItems(optionsList);
				
				cbDistnation.setItems(optionsList);;

				c.setOnMouseClicked(mouseEvent -> {
					if (Source == null && cbSource.getValue()==null) {
						Source = v2;
						cbSource.setValue(Source.getName());
						v2.getC().setFill(Color.YELLOWGREEN);
						v2.getC().setRadius(8);
					} else if (Destination == null && cbDistnation.getValue()==null) {
						Destination = v2;
						cbDistnation.setValue(Destination.getName());
						v2.getC().setFill(Color.PALEVIOLETRED);
						v2.getC().setRadius(8);
					}
					if (Source == Destination) {
						Alert a = new Alert(AlertType.WARNING);
						a.setContentText("Error, Source And Destination Are The Same");
						a.show();
						Clear();
					}
				});

			
			}

			Edge e;
			for (int i = 0; i < NumOfEdges; i++) {
				split = sc.nextLine().split(" ");
				int ind1 = 0, ind2 = 0;
				for (int j = 0; j < NumOfVerticies; j++) {
					if (g.getVList()[j].getName().compareTo(split[0]) == 0) {
						ind1 = j;
					} else if (g.getVList()[j].getName().compareTo(split[1]) == 0) {
						ind2 = j;
					}
				}
				e = new Edge(g.getVList()[ind1], g.getVList()[ind2], distance(g.getVList()[ind1].getY(),
						g.getVList()[ind1].getX(), g.getVList()[ind2].getY(), g.getVList()[ind2].getX()));
				g.addEdge(e, ind1, ind2);
			}
			sc.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void Run(ActionEvent event) throws Exception {
		
		for (int i = 0; i < g.getVList().length; i++) {
			if (((String) cbSource.getValue()).compareToIgnoreCase(g.getVList()[i].getName()) == 0) {
				Source = g.getVList()[i];
			} else if (((String) cbDistnation.getValue()).compareToIgnoreCase(g.getVList()[i].getName()) == 0) {
				Destination = g.getVList()[i];
			}
		}
		Alert a = new Alert(AlertType.WARNING);
		if (Source == null) {
			a.setContentText("Source Isn't A Valid Name");
			a.show();
			cbSource.setValue("Select Source ");
			Clear();
			return;
		} else if (Destination == null) {
			a.setContentText("Destination is Same As Source, Or its Invalid");
			a.show();
			cbDistnation.setValue("Select Destination");
			Clear();
			return;
		}
		Source.getC().setFill(Color.YELLOWGREEN);
		Source.getC().setRadius(8);
		Destination.getC().setFill(Color.PALEVIOLETRED);
		Destination.getC().setRadius(8);
		Dijkstra d = new Dijkstra();
		d.intializeTable(Source, g);
		d.find(Source, Destination);
		d.printPath(Destination);
		if (d.distance == Integer.MAX_VALUE) {
			tfDistance.setText("");
			taPath.appendText("No Path");
			return;
		}
		tfDistance.setText(String.format("%.1f", d.distance));
		taPath.appendText(Source.getName());
		Line l = new Line();
		l.setStartX(x_axis(Source.getX()));
		l.setStartY(y_axis(Source.getY()));
		for (int i = d.Path.size() - 1; i >= 0; i--) {
			l.setEndX(x_axis(d.Path.get(i).getX()));
			l.setEndY(y_axis(d.Path.get(i).getY()));
			l.setStrokeWidth(3);
			l.setOpacity(0.60);
			ap.getChildren().add(l);
			l = new Line();
			l.setStartX(x_axis(d.Path.get(i).getX()));
			l.setStartY(y_axis(d.Path.get(i).getY()));
			taPath.appendText("\n -> " + d.Path.get(i).getName());
		}
	}

	public void Clear() {
		cbSource.setValue(null);
		cbDistnation.setValue(null);
		taPath.clear();
		tfDistance.setText("");
		if (Source != null) {
			Source.getC().setFill(Color.GREY);
			Source.getC().setRadius(3);
		}
		if (Destination != null) {
			Destination.getC().setFill(Color.GREY);
			Destination.getC().setRadius(3);
		}
		Source = null;
		Destination = null;

		 ap.getChildren().removeIf(node -> node instanceof Line);

		
		
		}
	

	public void Exit() {
		System.exit(0);
	}

	public int x_axis(double Longitude) {
		double x = (getX(Longitude));
		
		return (int) x;
	}

	public int y_axis(double Latitude) {
		double y = (getY(Latitude));
		
		return (int) y;
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {

		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// معادلة هافرساين
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371; // The radius of the Earth
		return (c * r);
	}

	

}
