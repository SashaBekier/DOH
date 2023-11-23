package view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.AddController;
import controller.ViewController;
import controller.HomeController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Validators;
import view.controls.DateTimePicker;
import view.controls.ValidatedButton;
import view.controls.ValidatedTextField;

public class AddView extends DAHView{
	private AddController control;

	public AddView(AddController addController) {
		control = addController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		Label heading = new Label("Add New Post");
		container.getChildren().add(heading);
		
		GridPane form = new GridPane(2,8);
		
		ValidatedButton submit = new ValidatedButton("Add Post");
		
		ComboBox authorId = new ComboBox();
		ArrayList<String> alias = control.getAuthorIds();
		for(String author: alias) {
			authorId.getItems().add(author);
		}
		if(control.userHasAdmin()) {
			authorId.setEditable(true);
		}
		authorId.setValue(alias.get(0));
		form.add(new Label("Author ID:"),0,0);
		form.add(authorId, 1, 0);
		ValidatedTextField postId = new ValidatedTextField(s -> Validators.isPostIdValidAndAvailable(s), submit);
		ValidatedTextField content = new ValidatedTextField(s -> Validators.hasContent(s),submit);
		ValidatedTextField likes = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		likes.setValidatedText("0");
		ValidatedTextField shares = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		shares.setValidatedText("0");
		ValidatedTextField replyTo = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		replyTo.setValidatedText("0");
		DateTimePicker dateTimePicker = new DateTimePicker(LocalDateTime.now());
		HBox dateTime = dateTimePicker.getControl();
		form.add(new Label("Post ID:"),0,1);
		form.add(postId, 1, 1);
		form.add(new Label("Post Content:"),0,2);
		form.add(content, 1, 2);
		form.add(new Label("Likes:"),0,3);
		form.add(likes, 1, 3);
		form.add(new Label("Shares:"),0,4);
		form.add(shares, 1, 4);
		form.add(new Label("Reply To:"),0,5);
		form.add(replyTo, 1, 5);
		form.add(new Label("Post Date & time:"),0,6);
		form.add(dateTime, 1, 6);
		
		form.add(submit, 1, 7);
		
		submit.setOnAction(e -> {
			control.submitPost( postId.getText(),
					content.getText(),(String)authorId.getValue(),likes.getText(), shares.getText(),
					dateTimePicker.getDateTime().toString(), replyTo.getText());
			control.showPostsView();
		});
		
		container.getChildren().add(form);
		middlePane.getChildren().add(container);
		
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	
}



