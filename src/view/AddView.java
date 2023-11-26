package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.AddController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
		container.setAlignment(Pos.CENTER);
		Label heading = new Label("New Post");
		heading.setFont(new Font(16));
		VBox.setMargin(heading, new Insets(20));
		container.getChildren().add(heading);
		
		GridPane form = new GridPane(5,5);
		
		ValidatedButton submit = new ValidatedButton("Add Post");
		
		ComboBox<String> authorId = new ComboBox<String>();
		ArrayList<String> alias = control.getAuthorIds();
		for(String author: alias) {
			authorId.getItems().add(author);
		}
		if(control.userHasAdmin()) {
			authorId.setEditable(true);
		}
		authorId.setValue(alias.get(0));
		addFormRow(new Label("Author ID:"), authorId, form, 0);

		ValidatedTextField postId = new ValidatedTextField(s -> Validators.isPostIdValidAndAvailable(s), submit);
		addFormRow(new Label("Post ID:"), postId, form, 1);
		
		ValidatedTextField content = new ValidatedTextField(s -> Validators.hasContent(s),submit);
		addFormRow(new Label("Post Content:"), content, form, 2);
		
		ValidatedTextField likes = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		likes.setValidatedText("0");
		addFormRow(new Label("Likes:"), likes, form, 3);
		
		ValidatedTextField shares = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		shares.setValidatedText("0");
		addFormRow(new Label("Shares:"), shares, form, 4);
		
		ValidatedTextField replyTo = new ValidatedTextField(s -> Validators.isIntBetween(0, Integer.MAX_VALUE, s),submit);
		replyTo.setValidatedText("0");
		addFormRow(new Label("Reply To:"), replyTo, form, 5);
				
		DateTimePicker dateTimePicker = new DateTimePicker(LocalDateTime.now());
		HBox dateTime = dateTimePicker.getControl();
		Label dateTimeL = new Label("Post Date & time:");
		form.add(dateTimeL,0,6);
		form.add(dateTime, 1, 6);
		
		form.add(submit, 1, 7);
		
		submit.setOnAction(e -> {
			control.submitPost( postId.getText(),
					content.getText(),(String)authorId.getValue(),likes.getText(), shares.getText(),
					dateTimePicker.getDateTime().toString(), replyTo.getText());
			PostFilter.clearFilters();
			PostFilter.postIdF = Integer.parseInt(postId.getText());
			control.showPostsView();
		});
		
		container.getChildren().add(form);
		middlePane.getChildren().add(container);
	}

	private void drawTop() {
		topPane = control.getDashboard();
	}
	
	private void addFormRow(Label label, Control control, GridPane form, int row) {
		form.add(label,0,row);
		GridPane.setHalignment(label, HPos.RIGHT);
		form.add(control, 1, row);
	}
}



