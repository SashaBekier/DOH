package view;

import java.util.ArrayList;

import controller.AddController;
import controller.Controller;
import controller.HomeController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Validators;

public class AddView extends View{
	private AddController control;

	public AddView(AddController addController) {
		control = addController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new VBox(2);
		Label heading = new Label("Add New Post");
		middlePane.getChildren().add(heading);
		
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
		TextField postId = new TextField();
		ValidatedTextField content = new ValidatedTextField(s -> Validators.hasContent(s));
		content.registerButton(submit);
		TextField likes = new TextField();
		TextField shares = new TextField();
		TextField replyTo = new TextField();
		TextField dateTime = new TextField();
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
					dateTime.getText(), replyTo.getText());
		});
		
		middlePane.getChildren().add(form);
		
		
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	
}



