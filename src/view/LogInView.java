package view;

import controller.Controller;
import controller.LogInController;
import dao.InvalidLoginException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LogInView extends View{
	private LogInController control;
	
	public LogInView(LogInController control) {
		this.control = control;
		drawTop();
		drawMiddle();
		
	}
	
	
	

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox(4);
		middlePane.getChildren().add(container);
		container.getChildren().add(new Label("Log In"));
		GridPane formFields = new GridPane(2,2);
		formFields.add(new Label("Username: "),0,0);
		TextField usernameSubmitted = new TextField();
		formFields.add(usernameSubmitted, 1, 0);
		formFields.add(new Label("Password: "),0,1);
		TextField passwordSubmitted = new PasswordField();
		formFields.add(passwordSubmitted,1,1);
		container.getChildren().add(formFields);
		HBox formSubmit = new HBox(3);
		Label register = new Label("Register");
		formSubmit.getChildren().add(register);
		formSubmit.getChildren().add(new Label("Forgot Password"));
		Button formSubmitted = new Button("Log In");
		formSubmit.getChildren().add(formSubmitted);
		container.getChildren().add(formSubmit);
		Label warning = new Label();
		container.getChildren().add(warning);
		formSubmitted.setOnAction(e -> 
			{
				try {
					control.submitLogin(usernameSubmitted.getText(),
							passwordSubmitted.getText());
				} catch (InvalidLoginException e1) {
					warning.setText("Invalid Username or Password");
				}
			});
		
		register.setOnMouseClicked( e -> {
			control.callRegisterView();}
			);
	}
	
	private void drawTop() {
		topPane = new HBox();
		VBox banner = Banner.getBanner();
		banner.prefWidthProperty().bind(topPane.widthProperty());
		topPane.getChildren().add(banner);
	}

	
	
	
}
