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
		middlePane = new VBox(4);
		middlePane.getChildren().add(new Label("Log In"));
		GridPane formFields = new GridPane(2,2);
		formFields.add(new Label("Username: "),0,0);
		TextField usernameSubmitted = new TextField();
		formFields.add(usernameSubmitted, 1, 0);
		formFields.add(new Label("Password: "),0,1);
		TextField passwordSubmitted = new PasswordField();
		formFields.add(passwordSubmitted,1,1);
		middlePane.getChildren().add(formFields);
		HBox formSubmit = new HBox(3);
		formSubmit.getChildren().add(new Label("Register"));
		formSubmit.getChildren().add(new Label("Forgot Password"));
		Button formSubmitted = new Button("Log In");
		formSubmit.getChildren().add(formSubmitted);
		middlePane.getChildren().add(formSubmit);
		Label warning = new Label();
		middlePane.getChildren().add(warning);
		
		formSubmitted.setOnAction(e -> 
			{
				try {
					control.submitLogin(usernameSubmitted.getText(),
							passwordSubmitted.getText());
				} catch (InvalidLoginException e1) {
					warning.setText("Invalid Username or Password");
				}
			});
	}
	
	private void drawTop() {
		topPane = new VBox(2);
		Pane blankBar = new Pane();
		blankBar.setMinHeight(50);
		topPane.getChildren().add(blankBar);
		Pane banner = new Pane();
		banner.setBackground(new Background(new BackgroundFill(
				Color.DODGERBLUE,null,null)));
		
		banner.setMinHeight(50);
		
		Text bannerText = new Text(0,33,"Data Analytics Hub");
		bannerText.setStroke(Color.ALICEBLUE);
		bannerText.setFill(Color.ALICEBLUE);
		bannerText.setFont(new Font(24));
		bannerText.setWrappingWidth(400);
		bannerText.setTextAlignment(TextAlignment.CENTER);
		
		banner.getChildren().add(bannerText);
		topPane.getChildren().add(banner);
	}

	
	
	
}
