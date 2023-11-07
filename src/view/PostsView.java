package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private Image sortArrow = new Image("assets/arrow.png");
	private int activeSort = 0;
	private HashMap<Integer, Button> sortButtons = new HashMap<Integer, Button>();
	

	public PostsView(PostsController postController) {
		control = postController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new VBox(2);
		
		HBox filters = new HBox();
		Button submit = new Button("Apply");
		
		
		
		TextField postIdFilter = new TextField();
		
		
		TextField authorIdFilter = new TextField();
		
		CheckBox showReplies = new CheckBox();
		showReplies.setSelected(true);
		
		DateTimePicker fromDateTimePicker = new DateTimePicker(0, 0);
		HBox fromDate = fromDateTimePicker.getControl();
		
		DateTimePicker toDateTimePicker = new DateTimePicker(23, 59);
		HBox toDate = toDateTimePicker.getControl();
		
		submit.setOnAction(e -> {
				
				if(postIdFilter.getText().length() > 0) {
					try {
						PostFilter.postIdF = Integer.parseInt(postIdFilter.getText().trim());
					} catch (NumberFormatException nfe) {
						PostFilter.postIdF = null;
					}
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
				posts = filterPosts();
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
	
	private void updateSortButton(Button b,int sortBy) {
		
		if(activeSort == sortBy) {
			b.getGraphic().setVisible(true);
			if(!PostFilter.ascending[sortBy]) {
				b.getGraphic().setRotate(180);
			} else {
				b.getGraphic().setRotate(0);
			}
		} else {
			b.getGraphic().setVisible(false);
		}
	}

	private void updatePostPane() {
		if(postPane == null) {
			initPostPane();
		}
		
		postPane.getChildren().removeIf(child -> GridPane.getRowIndex(child)>0);
		for(int i = 0; i < sortButtons.size();i++) {
			updateSortButton(sortButtons.get(i),i);
		}
		
		
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
	
	private Button getSortButton(String text,int sortBy) {
		Button button = new Button(text);
		ImageView bArrow = new ImageView(sortArrow);
		button.setGraphic(bArrow);
		
		button.setContentDisplay(ContentDisplay.RIGHT);
		button.setOnAction(e -> {
			activeSort = sortBy;
			PostFilter.ascending[activeSort] = !PostFilter.ascending[activeSort];
			sortPosts();
			updatePostPane();			
		});
		sortButtons.put(sortBy, button);
		return button;
	}
	
	private void initPostPane() {
		posts = filterPosts();
		middlePane.getChildren().remove(postPane);
		
		postPane = new GridPane();
		
		Button byPostId = getSortButton("Post ID", Post.BY_POST_ID);
		Button byAuthor = getSortButton("Author", Post.BY_AUTHOR);
		Button byLikes = getSortButton("Likes", Post.BY_LIKES);
		Button byShares = getSortButton("Shares", Post.BY_SHARES);
		Button byDate = getSortButton("Date Posted", Post.BY_DATE);
		Button byParent = getSortButton("Reply To", Post.BY_PARENT);
		
		
		postPane.add(byPostId, 0, 0);
		postPane.add(byAuthor, 1, 0);
		postPane.add(new Button("Content"), 2, 0);
		postPane.add(byLikes, 3, 0);
		postPane.add(byShares, 4, 0);
		postPane.add(byParent, 5, 0);
		postPane.add(byDate, 6, 0);
		
		middlePane.getChildren().add(postPane);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	
	private List<Post> filterPosts(){
		posts = control.getPosts();
		posts = posts.stream().filter(p -> {
			if(PostFilter.postIdF != null) {
				return (p.getId()==PostFilter.postIdF) ;
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
		}).collect(Collectors.toList());
		
		//add any dropped replies if requested
		if(PostFilter.showRepliesF) {
			HashSet<Integer> postIds = new HashSet<Integer>();
			for(Post p1: posts) {
				postIds.add(p1.getId());
			}
			for(Post p2: control.getPosts()) {
				if(postIds.contains(p2.getParentId()) &&
						!postIds.contains(p2.getId())){
					posts.add(p2);
				}
			}
		}
		
		//refilter for date range after replies have been added to list
		posts = posts.stream().filter(p-> {
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

	private void sortPosts(){
		switch(activeSort) {
			case Post.BY_POST_ID: 
				posts.sort(Comparator.comparing(Post::getId));
				break;
			case Post.BY_AUTHOR: 
				posts.sort(Comparator.comparing(Post::getAuthorId));
				break;
			case Post.BY_LIKES:
				posts.sort(Comparator.comparing(Post::getLikes)
						.thenComparing(Post::getPostedAt));
				break;
			case Post.BY_SHARES:
				posts.sort(Comparator.comparing(Post::getShares)
						.thenComparing(Post::getPostedAt));
				break;
			case Post.BY_DATE:
				posts.sort(Comparator.comparing(Post::getPostedAt));
				
				break;
			case Post.BY_PARENT:
				posts.sort(Comparator.comparing(Post::getParentId));
				break;
		}
		if(PostFilter.ascending[activeSort] == false) {
			Collections.reverse(posts);
		}
		
		
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

