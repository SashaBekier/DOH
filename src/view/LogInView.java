package view;

import controller.ViewController;
import controller.LogInController;
import dao.InvalidLoginException;
import javafx.geometry.HPos;
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
		
		layout.setTop(DAHStyles.verticalSpacer(DAHStyles.STAGE_HEIGHT / 6));
		
		GridPane formFields = new GridPane();
		formFields.setVgap(5);
		formFields.add(new Label("Username: "),0,0);
		TextField usernameSubmitted = new TextField();
		formFields.add(usernameSubmitted, 1, 0);
		GridPane.setColumnSpan(usernameSubmitted, 2);
		formFields.add(new Label("Password: "),0,1);
		TextField passwordSubmitted = new PasswordField();
		formFields.add(passwordSubmitted,1,1);
		GridPane.setColumnSpan(passwordSubmitted, 2);
		Label register = new Label("Register New Account");
		register.setTextFill(Color.BLUE);
		register.setUnderline(true);
		formFields.add(register,0,2);
		GridPane.setColumnSpan(register, 2);
		Button formSubmitted = new Button("Log In");
		formSubmitted.setDefaultButton(true);
		formFields.add(formSubmitted, 2, 2);
		GridPane.setHalignment(formSubmitted, HPos.RIGHT);
		
		container.getChildren().add(formFields);
		
		Label warning = new Label();
		warning.setTextFill(Color.RED);
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
		
		register.setOnMouseClicked( e -> control.callRegisterView());
		middlePane.getChildren().add(layout);
		
	}
	
	private void drawTop() {
		topPane = new HBox();
		VBox banner = Banner.getBanner();
		banner.prefWidthProperty().bind(topPane.widthProperty());
		topPane.getChildren().add(banner);
	}

	
	
	
}
