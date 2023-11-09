package view.controls;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class ValidatedPasswordField extends PasswordField implements ValidatedControl {
	private boolean valid = false;
	private ValidatedButton registeredButton;
	
	
	public void registerButton(ValidatedButton button) {
		registeredButton = button;
		button.addTarget();
	}

	public ValidatedPasswordField(TextValidator validator) {
		this.setOnKeyTyped(e -> {
			if(validator.validate(this.getText())) {
				this.setBackground(new Background(new BackgroundFill(
						Color.LIGHTGREEN,null,null)));
				valid = true;
				if(registeredButton != null) registeredButton.addValid();
			} else {
				this.setBackground(new Background(new BackgroundFill(
						Color.LIGHTPINK,null,null)));
				if(valid == true && registeredButton != null) registeredButton.takeValid();
				valid = false;
			}
		});
	}
	
	public ValidatedPasswordField(TextValidator validator, ValidatedButton button) {
		this(validator);
		this.registerButton(button);
	}
	
	public ValidatedPasswordField(TextField fieldToMatch) {
		this.setOnKeyTyped(e -> {
			if(this.getText().equals(fieldToMatch.getText())) {
				this.setBackground(new Background(new BackgroundFill(
						Color.LIGHTGREEN,null,null)));
				valid = true;
				if(registeredButton != null) registeredButton.addValid();
			} else {
				this.setBackground(new Background(new BackgroundFill(
						Color.LIGHTPINK,null,null)));
				if(valid == true && registeredButton != null) registeredButton.takeValid();
				valid = false;
			}
		});
	}
	
	public ValidatedPasswordField(TextField fieldToMatch, ValidatedButton button) {
		this(fieldToMatch);
		this.registerButton(button);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean isValid() {
		return valid;
	}

}
