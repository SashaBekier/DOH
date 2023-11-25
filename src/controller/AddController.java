package controller;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import model.DAHModel;
import model.Post;
import view.AddView;

public class AddController implements ViewController {
	private AddView add;
	private DAHController control;
	private DAHModel model;

	public AddController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		add = new AddView(this);
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

	@Override
	public HBox[] getPanes() {
		return add.getPanes();
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
