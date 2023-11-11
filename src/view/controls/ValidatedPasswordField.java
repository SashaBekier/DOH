package view.controls;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class ValidatedPasswordField extends PasswordField implements ValidatedControl {
	private boolean valid = false;
	private ValidatedButton registeredButton;
	private TextValidator validator;
	
	
	public void registerButton(ValidatedButton button) {
		registeredButton = button;
		button.addTarget();
	}

	public ValidatedPasswordField(TextValidator validator) {
		this.validator = validator;
		this.setOnKeyTyped(e -> {
			validate();
		});
	}
	
	public void setValidatedText(String text) {
		super.setText(text);
		if(validator!=null) {
			validate();
		} else {
			setInvalid();
		}
	}
	
	public ValidatedPasswordField(TextValidator validator, ValidatedButton button) {
		this(validator);
		this.registerButton(button);
	}
	
	public ValidatedPasswordField(TextField fieldToMatch) {
		this.setOnKeyTyped(e -> {
			if(this.getText().equals(fieldToMatch.getText())) {
				setValid();
			} else {
				setInvalid();
			}
		});
	}
	
	private void setInvalid() {
		setBackground(new Background(new BackgroundFill(
				Color.LIGHTPINK,null,null)));
		if(valid == true && registeredButton != null) registeredButton.takeValid();
		valid = false;
	}
	
	private void setValid() {
		setBackground(new Background(new BackgroundFill(
				Color.LIGHTGREEN,null,null)));
		valid = true;
		if(registeredButton != null) registeredButton.addValid();
	}
	
	public ValidatedPasswordField(TextField fieldToMatch, ValidatedButton button) {
		this(fieldToMatch);
		this.registerButton(button);
	}
	
	
	private void validate() {
		if(validator.validate(this.getText())) {
			setValid();
		} else {
			setInvalid();
		}
	}
	
	
	
	
	
	
	
	
	
	@Override
	public boolean isValid() {
		return valid;
	}

}
