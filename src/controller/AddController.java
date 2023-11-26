package controller;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import view.AddView;

public class AddController extends ViewController {
	private AddView add;

	public AddController(DAHController cont) {
		super(cont);
		add = new AddView(this);
		super.setView(add);
	}

	public String getActiveUserDisplayName() {
		return model.getActiveUser().getDisplayName();
	}

	public ArrayList<String> getAuthorIds() {
		return model.getActiveUser().getAuthorIDs();
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	public void showPostsView() {
		control.updateStage(DAHScreen.POSTS);
	}

	public void submitPost(String postId, String content, String authorId,
			String likes, String shares, String dateTime, String parent) {
		model.submitPost(postId, content, authorId, likes, shares, dateTime, parent);
	}

	public boolean userHasAdmin() {
		return model.getActiveUser().hasAdmin();
	}

}
