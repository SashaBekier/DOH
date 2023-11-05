package view;

import java.time.LocalDateTime;

import controller.Controller;
import controller.HomeController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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

public class PostsView extends View{
	private PostsController control;

	public PostsView(PostsController postController) {
		control = postController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new VBox(2);
		
		HBox filters = new HBox();
		ValidatedButton submit = new ValidatedButton("Apply");
		
		ValidatedTextField postIdFilter = new ValidatedTextField(
				s -> Validators.isIntBetweenOrBlank(1, Integer.MAX_VALUE, s), submit);
		
		TextField authorIdFilter = new TextField();
		
		CheckBox showReplies = new CheckBox();
		
		DateTimePicker fromDateTimePicker = new DateTimePicker(0, 0);
		HBox fromDate = fromDateTimePicker.getControl();
		
		DateTimePicker toDateTimePicker = new DateTimePicker(23, 59);
		HBox toDate = toDateTimePicker.getControl();
		
		
		
		filters.getChildren().addAll(
				new Label("Filter by - Post ID:"), postIdFilter,
				new Label("Author ID:"), authorIdFilter,
				showReplies, new Label("Include Replies"),
				new Label("From:"),fromDate,
				new Label("to:"), toDate);
		
		
		
		middlePane.getChildren().add(filters);
		
		GridPane postPane = new GridPane(6,100); //needs to know postcount to set rows.....
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	


	
}

class postsFilter{
	
}

class postsSort{
	
}
