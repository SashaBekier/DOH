package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExceptionPopUp {
	private Stage stage = new Stage();
	
	public ExceptionPopUp(Exception ex) {
		VBox container = new VBox(5);
		container.setAlignment(Pos.CENTER);
		
		Label message = new Label(ex.getMessage());
		Button ok = new Button("OK");
		ok.setOnAction(e-> stage.close());
		ok.setPrefWidth(100);
		container.getChildren().addAll(message,ok);
		
		Scene scene = new Scene(container, 400,100);
		stage.setScene(scene);
		stage.setTitle("System Error");
		stage.show();
		stage.requestFocus();
		stage.toFront();
	}
}
