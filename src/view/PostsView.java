package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import controller.PostsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Validators;
import view.controls.DAHStyles;
import view.controls.DashButton;
import view.controls.DateTimePicker;
import view.controls.ValidatedButton;
import view.controls.ValidatedTextField;
import model.Post;

public class PostsView extends DAHView{
	private PostsController control;
	private GridPane postPane;
	private VBox container;
	private List<Post> posts;
	private Image sortArrow = new Image("assets/arrow.png");
	private int activeSort = 0;
	private HashMap<Integer, Button> sortButtons = new HashMap<Integer, Button>();
	private TextField postIdFilter = new TextField();
	private Button submit = new DashButton("Filter Posts","assets/filter.png");

	public PostsView(PostsController postController) {
		control = postController;
		drawTop();
		drawMiddle();
	}
	
	@Override
	public HBox[] getPanes() {
		posts = control.getPosts();
		return super.getPanes();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		container = new VBox();
		
		HBox controlsContainer = new HBox();
		controlsContainer.setSpacing(5);
		
		FlowPane filters = new FlowPane();
		filters.setHgap(5);
		filters.setVgap(5);
		
		TextField authorIdFilter = new TextField();
		
		postIdFilter.setPrefWidth(80);
		authorIdFilter.setPrefWidth(80);
		
		CheckBox showReplies = new CheckBox();
		showReplies.setSelected(true);
		
		DateTimePicker fromDateTimePicker = new DateTimePicker();
		HBox fromDate = fromDateTimePicker.getControl();
		
		DateTimePicker toDateTimePicker = new DateTimePicker();
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
		
		HBox dateContainer = new HBox(5);
		dateContainer.getChildren().addAll(new Label("Posted Between: "),fromDate, new Label(" and: "), toDate);
		dateContainer.setAlignment(Pos.CENTER_LEFT);
		HBox repliesContainer = new HBox(5);
		repliesContainer.getChildren().addAll(showReplies, new Label("Include Replies"));
		repliesContainer.setAlignment(Pos.CENTER_LEFT);
		
		filters.getChildren().addAll(
				new Label("Filter by - Post ID: "), postIdFilter,
				new Label(" Author ID: "), authorIdFilter,
				repliesContainer,
				dateContainer);
		filters.setBackground(DAHStyles.BUTTON_BG);
		filters.setBorder(DAHStyles.BUTTON_BORDER);
		controlsContainer.getChildren().addAll(filters,submit);
		controlsContainer.setPadding(new Insets(5));
		
		
		if(control.getActiveUser().hasVIP()) {
			Button showPie = new DashButton("Show Pie Chart","assets/pie.png");
			showPie.setOnAction(e -> {
				new PostPieChart(posts);
			});
			Button export = new DashButton("Export CSV","assets/disk.png");
			export.setOnAction(e->{
				control.callExportCsvView(posts);
			});
			controlsContainer.getChildren().addAll(export, showPie);
		}
		
		container.getChildren().add(controlsContainer);
		
		
		
		middlePane.getChildren().add(container);
		
		
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

	public void updatePostPane() {
		if(postPane == null) {
			initPostPane();
		}
		
		
		if(PostFilter.postIdF != null && !PostFilter.postIdF.toString().equals(postIdFilter.getText())) {
			postIdFilter.setText(PostFilter.postIdF.toString());
			submit.fire();
		}
		
		postPane.getChildren().removeIf(child -> GridPane.getRowIndex(child)>0);
		
		
		
		int row = 1;
		Button[] deleteButtons = new Button[posts.size()+1];
		for(Post post: posts) {
			
			postPane.add(new Label(String.valueOf(post.getId())), 0, row);
			postPane.add(new Label(post.getAuthorId()), 1, row);
			Label content = new Label(post.getContent());
			content.setWrapText(true);
			
			postPane.add(content, 2, row);
			postPane.add(new Label(String.valueOf(post.getLikes())), 3, row);
			postPane.add(new Label(String.valueOf(post.getShares())), 4, row);
			postPane.add(new Label(String.valueOf(post.getParentId())), 5, row);
			postPane.add(new Label(post.getPostedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))), 6, row);
			if(control.getActiveUser().hasAdmin() || post.getAuthorId().equals(control.getActiveUser().getUserName())) {
				deleteButtons[row] = new Button("X");
				deleteButtons[row].setBackground(DAHStyles.INVALID_BG);
				deleteButtons[row].setBorder(DAHStyles.INVALID_BORDER);
				deleteButtons[row].setMinWidth(50);
				deleteButtons[row].setOnAction(e -> {
					control.deletePost(post);
					posts.remove(post);
					updatePostPane();
					
					
				});
				
				postPane.add(deleteButtons[row], 7, row);
			}
			row++;
		}
		if(posts.size()<1) {
			postPane.add(new Label("No Posts match your filters"), 2, row);
		}
		
		for(int i = 0; i < sortButtons.size();i++) {
			updateSortButton(sortButtons.get(i),i);
		}	
	
	}
	
	private Button getSortButton(String text,int sortBy) {
		Button button = new Button(text);
		ImageView bArrow = new ImageView(sortArrow);
		button.setGraphic(bArrow);
		
		button.setContentDisplay(ContentDisplay.RIGHT);
		button.setMinWidth(70);
		button.setMaxWidth(Double.MAX_VALUE);
		
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
		ScrollPane wrapper = new ScrollPane();
		//container.getChildren().remove(wrapper);
		wrapper.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		wrapper.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		wrapper.setPrefHeight(5000);
		wrapper.maxWidthProperty().bind(control.getStage().widthProperty());
		
		
		postPane = new GridPane();
		postPane.setVgap(2);
		postPane.setHgap(3);
		postPane.setAlignment(Pos.BASELINE_LEFT);
		postPane.setPadding(new Insets(5));
		postPane.maxWidthProperty().bind(wrapper.widthProperty().subtract(1));
		
		
		Button byPostId = getSortButton("Post ID", Post.BY_POST_ID);
		Button byAuthor = getSortButton("Author", Post.BY_AUTHOR);
		Button content = getSortButton("Content", Post.BY_CONTENT);
		Button byLikes = getSortButton("Likes", Post.BY_LIKES);
		Button byShares = getSortButton("Shares", Post.BY_SHARES);
		Button byDate = getSortButton("Date Posted", Post.BY_DATE);
		Button byParent = getSortButton("Reply To", Post.BY_PARENT);
		
		GridPane.setFillWidth(byPostId, true);
		GridPane.setFillWidth(byAuthor, true);
		GridPane.setFillWidth(content, true);
		GridPane.setFillWidth(byLikes, true);
		GridPane.setFillWidth(byShares, true);
		GridPane.setFillWidth(byDate, true);
		GridPane.setFillWidth(byParent, true);
		
		
		
		
		postPane.add(byPostId, 0, 0);
		
		postPane.add(byAuthor, 1, 0);
		postPane.add(content, 2, 0);
		postPane.add(byLikes, 3, 0);
		postPane.add(byShares, 4, 0);
		postPane.add(byParent, 5, 0);
		postPane.add(byDate, 6, 0);
		
		wrapper.setContent(postPane);
		
		container.getChildren().add(wrapper);
		
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
				posts.sort(Comparator.comparing(Post::getAuthorId)
						.thenComparing(Post::getPostedAt));
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
				posts.sort(Comparator.comparing(Post::getParentId)
						.thenComparing(Post::getPostedAt));
				break;
		}
		if(PostFilter.ascending[activeSort] == false) {
			Collections.reverse(posts);
		}
		
		
	}
	
	
	
}



