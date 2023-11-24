package view;

import controller.RegisterController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Validators;
import view.controls.DAHStyles;
import view.controls.ValidatedButton;
import view.controls.ValidatedPasswordField;
import view.controls.ValidatedTextField;

public class RegisterView extends DAHView{
	private RegisterController control;
	
	public RegisterView(RegisterController control) {
		this.control = control;
		drawTop();
		drawMiddle();
	}
	
	private void drawTop() {
		topPane = new HBox();
		VBox banner = Banner.getBanner();
		banner.prefWidthProperty().bind(topPane.widthProperty());
		topPane.getChildren().add(banner);
		
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		Text regText = new Text("Welcome to the Data Analytics Hub.\n\n"
				+ "To register fill in the form below. \n\n"
				+ "Throughout DAH validated inputs will display red when invalid and green when valid.\n"
				+ "Any form field that is red is required.");
		regText.setTextAlignment(TextAlignment.CENTER);
		regText.maxWidth(300);
	
		GridPane form = new GridPane();
		
		form.setVgap(5);
		
		ValidatedButton submitButton = new ValidatedButton("Register");
		
		form.add(new Label("Username: "),1,1);
		ValidatedTextField usernameText = new ValidatedTextField(s -> Validators.isUsernameAvailable(s),submitButton);
		form.add(usernameText, 2, 1);
		
		form.add(new Label("Password: "),1,2);
		ValidatedPasswordField passwordText = new ValidatedPasswordField(s -> Validators.hasContent(s),submitButton);
		form.add(passwordText, 2, 2);
		
		form.add(new Label("First Name: "),1,3);
		ValidatedTextField firstNameText = new ValidatedTextField(s -> Validators.hasContent(s),submitButton);
		form.add(firstNameText, 2, 3);
		
		form.add(new Label("Last Name: "),1,4);
		ValidatedTextField lastNameText = new ValidatedTextField(s -> Validators.hasContent(s),submitButton);
		form.add(lastNameText, 2, 4);
		
		Button cancel = new Button("Back");
		form.add(cancel, 1, 5);
		cancel.setOnAction(e -> control.loadLogIn());
		
		form.add(submitButton, 2, 5);
		GridPane.setHalignment(submitButton, HPos.RIGHT);
		
		
		container.getChildren().add(DAHStyles.verticalSpacer(DAHStyles.STAGE_HEIGHT / 16));
		container.getChildren().add(regText);
		container.getChildren().add(DAHStyles.verticalSpacer(DAHStyles.STAGE_HEIGHT / 16));
		container.getChildren().add(form);

		form.setAlignment(Pos.CENTER);
		middlePane.getChildren().add(container);
		
		submitButton.setOnAction(e -> {
			control.registerUser(usernameText.getText(),
					passwordText.getText(),
					firstNameText.getText(),
					lastNameText.getText());
		});
	}
}
