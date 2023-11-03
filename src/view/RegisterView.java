package view;

import controller.RegisterController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class RegisterView extends View{
	private RegisterController control;
	
	public RegisterView(RegisterController control) {
		this.control = control;
		drawTop();
		drawMiddle();
	}
	
	private void drawTop() {
		topPane = new Pane();
		
	}

	private void drawMiddle() {
		middlePane = new Pane();
		
		GridPane form = new GridPane(2,5);
		
		form.add(new Label("Username: "),0,0);
		TextField usernameText = new TextField();
		form.add(usernameText, 1, 0);
		
		form.add(new Label("Password: "),0,1);
		TextField passwordText = new PasswordField();
		form.add(passwordText, 1, 1);
		
		form.add(new Label("First Name: "),0,2);
		TextField firstNameText = new TextField();
		form.add(firstNameText, 1, 2);
		
		form.add(new Label("Last Name: "),0,3);
		TextField lastNameText = new TextField();
		form.add(lastNameText, 1, 3);
		
		Button submitButton = new Button("Submit");
		form.add(submitButton, 1, 4);
		
		
		middlePane.getChildren().add(form);
		
		submitButton.setOnAction(e -> {
			control.registerUser(usernameText.getText(),
					passwordText.getText(),
					firstNameText.getText(),
					lastNameText.getText());
		});
	}
}
