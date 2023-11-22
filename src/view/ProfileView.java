package view;

import java.util.ArrayList;

import controller.ViewController;
import controller.ProfileController;
import controller.LogInController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.User;
import model.Validators;
import view.controls.ValidatedButton;
import view.controls.ValidatedPasswordField;
import view.controls.ValidatedTextField;

public class ProfileView extends DAHView{
	private ProfileController control;

	public ProfileView(ProfileController ProfileController) {
		control = ProfileController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		User activeUser = control.getActiveUser();
		
		GridPane form = new GridPane();
		form.add(new Label("Username"), 0, 0);
		form.add(new Label(activeUser.getUserName()), 1, 0);
		Button changePassword = new Button("Change Password");
		form.add(changePassword, 0, 3);
		
		ArrayList<Node> resetPasswordNodes = new ArrayList<Node>(); 
		
		ValidatedButton resetPassword = new ValidatedButton("Reset Password");
		resetPasswordNodes.add(resetPassword);
		ValidatedButton updateUser = new ValidatedButton("Update User");
				
		Label oldPasswordL = new Label("Old Password");
		form.add(oldPasswordL, 0, 5);
		resetPasswordNodes.add(oldPasswordL);
		
		ValidatedPasswordField oldPasswordF = new ValidatedPasswordField(s -> Validators.isOldPassword(s),resetPassword);
		form.add(oldPasswordF, 1, 5);
		resetPasswordNodes.add(oldPasswordF);
		
		Label newPasswordL = new Label("New Password");
		form.add(newPasswordL, 0, 6);
		resetPasswordNodes.add(newPasswordL);
		
		ValidatedPasswordField newPassword1F = new ValidatedPasswordField(s -> Validators.hasContent(s),resetPassword);
		form.add(newPassword1F, 1, 6);
		resetPasswordNodes.add(newPassword1F);
		
		Label newPassword2L = new Label("Retype Password");
		form.add(newPassword2L, 0, 7);
		resetPasswordNodes.add(newPassword2L);
		
		ValidatedPasswordField newPassword2F = new ValidatedPasswordField(newPassword1F, resetPassword);
		form.add(newPassword2F, 1, 7);
		resetPasswordNodes.add(newPassword2F);
		form.add(resetPassword, 1, 8);
		
		showNodes(resetPasswordNodes, false);
		
		changePassword.setOnAction(e -> 
			showNodes(resetPasswordNodes, true));
		
		resetPassword.setOnAction(e -> {
			control.updatePassword(newPassword1F.getText());
			oldPasswordF.setValidatedText("");
			newPassword1F.setValidatedText("");
			newPassword2F.setValidatedText("");
			showNodes(resetPasswordNodes, false);
			});
		
		
		form.add(new Label("First Name :"), 0, 1);
		TextField firstNameF = new TextField();
		firstNameF.setText(activeUser.getFirstName());
		form.add(firstNameF, 1, 1);

		form.add(new Label("Last Name :"), 0, 2);
		ValidatedTextField lastNameF = new ValidatedTextField(s -> Validators.hasContent(s),updateUser);
		lastNameF.setValidatedText(activeUser.getLastName());
		form.add(lastNameF, 1, 2);
		
		form.add(updateUser, 1, 3);
		
		
		updateUser.setOnAction(e -> {
			control.updateUser(firstNameF.getText(), lastNameF.getText());
			control.updateView();
		});
		
		Image vipImage;
		Button vipButton;
		
		if(activeUser.hasVIP()) {
			vipImage = new Image("assets/vipActive.png");
			vipButton = new Button("Unsubscribe");
		} else {
			vipImage = new Image("assets/vipAvailable.png");
			vipButton = new Button("Subscribe");
		}
		vipButton.setOnAction(e -> control.goVip());
		
		container.getChildren().addAll(form,new ImageView(vipImage),vipButton);
		middlePane.getChildren().add(container);
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}
	
	private void showNodes(ArrayList<Node> nodes, boolean visible) {
		for(Node node: nodes) {
			node.setVisible(visible);
			if(visible) {
				node.setScaleY(1);
			} else {
				node.setScaleY(0);
			}
		}
	}

	
}
