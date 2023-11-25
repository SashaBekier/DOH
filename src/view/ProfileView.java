package view;

import java.util.ArrayList;

import controller.ViewController;
import controller.ProfileController;
import controller.LogInController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.User;
import model.Validators;
import view.controls.DAHStyles;
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
		middlePane = new HBox(5);
		VBox container = new VBox(5);
		User activeUser = control.getActiveUser();
		
		GridPane form = new GridPane();
		form.setVgap(5);
		form.setHgap(5);
		
		Label userL = new Label("Username: "); 
		form.add(userL, 0, 0);
		GridPane.setHalignment(userL, HPos.RIGHT);
		form.add(new Label(activeUser.getUserName()), 1, 0);
		Button changePassword = new Button("Change Password");
		form.add(changePassword, 0, 3);
		
		ArrayList<Node> resetPasswordNodes = new ArrayList<Node>(); 
		
		ValidatedButton resetPassword = new ValidatedButton("Reset Password");
		resetPasswordNodes.add(resetPassword);
		ValidatedButton updateUser = new ValidatedButton("Update User");
		updateUser.setMaxWidth(1000);
		GridPane.setFillWidth(updateUser, true);		
		Label oldPasswordL = new Label("Old Password: ");
		form.add(oldPasswordL, 0, 6);
		GridPane.setHalignment(oldPasswordL, HPos.RIGHT);
		resetPasswordNodes.add(oldPasswordL);
		 
		ValidatedPasswordField oldPasswordF = new ValidatedPasswordField(s -> Validators.isOldPassword(s),resetPassword);
		form.add(oldPasswordF, 1, 6);
		resetPasswordNodes.add(oldPasswordF);
		
		Label newPasswordL = new Label("New Password: ");
		form.add(newPasswordL, 0, 7);
		GridPane.setHalignment(newPasswordL, HPos.RIGHT);
		resetPasswordNodes.add(newPasswordL);
		
		ValidatedPasswordField newPassword1F = new ValidatedPasswordField(s -> Validators.hasContent(s),resetPassword);
		form.add(newPassword1F, 1, 7);
		resetPasswordNodes.add(newPassword1F);
		
		Label newPassword2L = new Label("Retype Password: ");
		form.add(newPassword2L, 0, 8);
		GridPane.setHalignment(newPassword2L, HPos.RIGHT);
		resetPasswordNodes.add(newPassword2L);
		
		ValidatedPasswordField newPassword2F = new ValidatedPasswordField(newPassword1F, resetPassword);
		form.add(newPassword2F, 1, 8);
		resetPasswordNodes.add(newPassword2F);
		
		Label passwordUpdated = new Label("Your password has been updated.");
		passwordUpdated.setVisible(false);
		
		form.add(resetPassword, 1, 9);
		form.add(passwordUpdated, 0, 10);
		GridPane.setColumnSpan(passwordUpdated, 2);
		showNodes(resetPasswordNodes, false);
		
		changePassword.setOnAction(e -> {
			showNodes(resetPasswordNodes, true);
			passwordUpdated.setVisible(false);
			});
		
		resetPassword.setOnAction(e -> {
			control.updatePassword(newPassword1F.getText());
			oldPasswordF.setValidatedText("");
			newPassword1F.setValidatedText("");
			newPassword2F.setValidatedText("");
			showNodes(resetPasswordNodes, false);
			passwordUpdated.setVisible(true);
			});
		
		Label fNameL = new Label("First Name :");
		form.add(fNameL, 0, 1);
		GridPane.setHalignment(fNameL, HPos.RIGHT);
		ValidatedTextField firstNameF = new ValidatedTextField(s-> Validators.hasContent(s),updateUser);
		firstNameF.setValidatedText(activeUser.getFirstName());
		form.add(firstNameF, 1, 1);

		Label lNameL = new Label("Last Name :");
		form.add(lNameL, 0, 2);
		GridPane.setHalignment(lNameL, HPos.RIGHT);
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
		VBox vipContainer = new VBox(5);
		vipContainer.setAlignment(Pos.CENTER);
		container.getChildren().add(DAHStyles.verticalSpacer(50));
		container.setAlignment(Pos.CENTER);
		vipContainer.getChildren().addAll(new ImageView(vipImage),vipButton);
		container.getChildren().add(form);
		
		VBox layout = new VBox(5);
		Label greeting = new Label(activeUser.getDisplayName() + "- User Profile");
		greeting.setFont(DAHStyles.HEADING);
		layout.setAlignment(Pos.CENTER);
		HBox mainpanel = new HBox(20);
		mainpanel.getChildren().addAll(container,vipContainer);
		layout.getChildren().addAll(DAHStyles.verticalSpacer(20),greeting, mainpanel);
		
		middlePane.getChildren().add(layout);
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
