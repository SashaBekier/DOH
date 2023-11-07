package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Validators;
import view.controls.DateTimePicker;
import view.controls.ValidatedButton;
import view.controls.ValidatedTextField;
import model.Post;

public class PostsView extends View{
	private PostsController control;
	private GridPane postPane;
	
	private List<Post> posts;
	

	public PostsView(PostsController postController) {
		control = postController;
		//posts = control.getPosts();
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
					PostFilter.postIdF = Integer.parseInt(postIdFilter.getText().trim());
				} else {
					PostFilter.postIdF = null;
				}
				if(authorIdFilter.getText().trim().length() > 0) {
					PostFilter.authorIdF = authorIdFilter.getText();
				} else {
					PostFilter.authorIdF = null;
				}
				if(showReplies.isSelected()) {
					PostFilter.showRepliesF = true;
				} else {
					PostFilter.showRepliesF = false;
				}
				PostFilter.fromDate = fromDateTimePicker.getDateTime();
				PostFilter.toDate = toDateTimePicker.getDateTime();
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
		
		Button export = new Button("Export Posts to CSV");
		export.setOnAction(e->{
			control.callExportCsvView(posts);
		});
		
		
		
		
		updatePostPane();
		
		
		
		
	}

	private void updatePostPane() {
		initPostPane();
		posts = control.getPosts();
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
			if(PostFilter.postIdF != null) {
				return (p.getId()==PostFilter.postIdF || p.getParentId()==PostFilter.postIdF) ;
			} else {
				return true;
			}
		}).filter(p-> {
			if(PostFilter.authorIdF != null && PostFilter.authorIdF.trim().length() > 0) {
				return p.getAuthorId().trim().toUpperCase().equals(PostFilter.authorIdF.trim().toUpperCase());
			} else {
				return true;
			}
		}).filter(p-> {
			if(PostFilter.showRepliesF) {
				return true;
			} else {
				return p.getParentId()==0;
			}
		}).filter(p-> {
			if(PostFilter.fromDate != null) {
				return p.getPostedAt().compareTo(PostFilter.fromDate) >= 0;
			} else {
				return true;
			}
		}).filter(p-> {
			if(PostFilter.toDate != null) {
				return p.getPostedAt().compareTo(PostFilter.toDate) <= 0;
			} else {
				return true;
			}
		}).collect(Collectors.toList());
		return posts;
	}

	private List<Post> sortPosts(List<Post> posts,int sortBy){
		switch(sortBy) {
			case Post.BY_POST_ID: 
				posts = posts.stream().collect(Collectors.toList());
				break;
			case Post.BY_AUTHOR: 
				posts = posts.stream().collect(Collectors.toList());
				break;
			case Post.BY_LIKES:
				posts = posts.stream().collect(Collectors.toList());
				break;
			case Post.BY_SHARES:
				posts = posts.stream().collect(Collectors.toList());
				break;
			case Post.BY_DATE:
				posts = posts.stream().collect(Collectors.toList());
				break;
			case Post.BY_PARENT:
				posts = posts.stream().collect(Collectors.toList());
				break;
		}
		PostFilter.ascending[sortBy] = !PostFilter.ascending[sortBy];
		return posts;
	}
	
	private void clearFilters() {
		for(boolean asc: PostFilter.ascending) {
			asc = !asc;
		}
		PostFilter.postIdF = null;
		PostFilter.authorIdF = null;
		PostFilter.showRepliesF = true;
		PostFilter.fromDate = null;
		PostFilter.toDate = null;
	}
	
}

class PostFilter{
	static boolean[] ascending = {true, true, true, true, true, true};
	static Integer postIdF = null;
	static String authorIdF = null;
	static boolean showRepliesF = true;
	static LocalDateTime fromDate = null;
	static LocalDateTime toDate = null;
	

}

