package view;

import controller.ViewController;
import controller.LogInController;
import dao.InvalidLoginException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import view.controls.DAHStyles;

public class LogInView extends DAHView{
	private LogInController control;
	
	public LogInView(LogInController control) {
		this.control = control;
		drawTop();
		drawMiddle();
		
	}
	
	
	

	private void drawMiddle() {
		middlePane = new HBox();
		
		BorderPane layout = new BorderPane();
		VBox container = new VBox(4);
		layout.setCenter(container);
		
		layout.setTop(DAHStyles.verticalSpacer(50));
		Button formSubmitted = new Button("Log In");
		formSubmitted.setDefaultButton(true);
		GridPane formFields = new GridPane(2,2);
		formFields.add(new Label("Username: "),0,0);
		TextField usernameSubmitted = new TextField();
		formFields.add(usernameSubmitted, 1, 0);
		formFields.add(new Label("Password: "),0,1);
		TextField passwordSubmitted = new PasswordField();
		formFields.add(passwordSubmitted,1,1);
		container.getChildren().add(formFields);
		HBox form = new HBox(3);
		Label register = new Label("Register");
		form.getChildren().add(register);
		form.getChildren().add(new Label("Forgot Password"));
		
		form.getChildren().add(formSubmitted);
		container.getChildren().add(form);
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
		
		register.setOnMouseClicked( e -> 
			{
				control.callRegisterView();
			});
		middlePane.getChildren().add(layout);
		
	}
	
	private void drawTop() {
		topPane = new HBox();
		VBox banner = Banner.getBanner();
		banner.prefWidthProperty().bind(topPane.widthProperty());
		topPane.getChildren().add(banner);
	}

	
	
	
}
