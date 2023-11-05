package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import model.Post;

public class PostsView extends View{
	private PostsController control;
	private GridPane postPane;
	private PostFilter filter = new PostFilter();
	private PostSorter sorter = new PostSorter();
	

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
		submit.addValid();
		
		TextField authorIdFilter = new TextField();
		
		CheckBox showReplies = new CheckBox();
		showReplies.setSelected(true);
		
		DateTimePicker fromDateTimePicker = new DateTimePicker(0, 0);
		HBox fromDate = fromDateTimePicker.getControl();
		
		DateTimePicker toDateTimePicker = new DateTimePicker(23, 59);
		HBox toDate = toDateTimePicker.getControl();
		
		submit.setOnAction(e -> {
				if(postIdFilter.getText().length() > 0) {
					filter.postIdF = Integer.parseInt(postIdFilter.getText().trim());
				} else {
					filter.postIdF = null;
				}
				if(authorIdFilter.getText().trim().length() > 0) {
					filter.authorIdF = authorIdFilter.getText();
				} else {
					filter.authorIdF = null;
				}
				if(showReplies.isSelected()) {
					filter.showRepliesF = true;
				} else {
					filter.showRepliesF = false;
				}
				filter.fromDate = fromDateTimePicker.getDateTime();
				filter.toDate = toDateTimePicker.getDateTime();
				updatePostPane();
				
			});
				
		
		filters.getChildren().addAll(
				new Label("Filter by - Post ID: "), postIdFilter,
				new Label(" Author ID: "), authorIdFilter,
				showReplies, new Label(" Include Replies "),
				new Label(" Between: "),fromDate,
				new Label(" and: "), toDate,
				submit);
		
		
		
		middlePane.getChildren().add(filters);
		
		
		
		
		updatePostPane();
		
		
		
		
	}

	private void updatePostPane() {
		initPostPane();
		List<Post> posts = control.getPosts();
		posts = filterPosts(posts);
		int row = 1;
		for(Post post: posts) {
			postPane.add(new Label(String.valueOf(post.getId())), 0, row);
			postPane.add(new Label(post.getAuthorId()), 1, row);
			postPane.add(new Label(post.getContent()), 2, row);
			postPane.add(new Label(String.valueOf(post.getLikes())), 3, row);
			postPane.add(new Label(String.valueOf(post.getShares())), 4, row);
			postPane.add(new Label(String.valueOf(post.getParentId())), 5, row);
			postPane.add(new Label(String.valueOf(post.getPostedAt())), 6, row);
			row++;
		}
		if(row == 1) {
			postPane.add(new Label("No Posts match your filters"), 2, row);
		}
	}
	
	private void initPostPane() {
		middlePane.getChildren().remove(postPane);
		
		postPane = new GridPane();
		postPane.add(new Label("Post ID"), 0, 0);
		postPane.add(new Label("Author"), 1, 0);
		postPane.add(new Label("Content"), 2, 0);
		postPane.add(new Label("Likes"), 3, 0);
		postPane.add(new Label("Shares"), 4, 0);
		postPane.add(new Label("Reply To"), 5, 0);
		postPane.add(new Label("Date Posted"), 6, 0);
		
		middlePane.getChildren().add(postPane);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	
	private List<Post> filterPosts(List<Post> posts){
		
		posts = posts.stream().filter(p -> {
			if(filter.postIdF != null) {
				return (p.getId()==filter.postIdF || p.getParentId()==filter.postIdF) ;
			} else {
				return true;
			}
		}).filter(p-> {
			if(filter.authorIdF != null && filter.authorIdF.trim().length() > 0) {
				return p.getAuthorId().toUpperCase().equals(filter.authorIdF.trim().toUpperCase());
			} else {
				return true;
			}
		}).filter(p-> {
			if(filter.showRepliesF) {
				return true;
			} else {
				return p.getParentId()==0;
			}
		}).filter(p-> {
			if(filter.fromDate != null) {
				return p.getPostedAt().compareTo(filter.fromDate) >= 0;
			} else {
				return true;
			}
		}).filter(p-> {
			if(filter.toDate != null) {
				return p.getPostedAt().compareTo(filter.toDate) <= 0;
			} else {
				return true;
			}
		}).collect(Collectors.toList());
		
		
		
		

		return posts;
	}

	
}

class PostFilter{
	Integer postIdF;
	String authorIdF = null;
	boolean showRepliesF = true;
	LocalDateTime fromDate;
	LocalDateTime toDate;
}

class PostSorter{
	
}