module algorithm_project3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
