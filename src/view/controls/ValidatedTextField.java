package view.controls;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ValidatedTextField extends TextField implements ValidatedControl {
	private boolean valid = false;
	private ValidatedButton registeredButton;
	private TextValidator validator;
	
	
	public void registerButton(ValidatedButton button) {
		registeredButton = button;
		button.addTarget();
		if(valid) registeredButton.addValid();
	}
	
	public ValidatedTextField(TextValidator validator, ValidatedButton button) {
		this(validator);
		this.registerButton(button);
	}
	
	public ValidatedTextField(TextValidator validator) {
		this.validator = validator;
		this.setOnKeyTyped(e -> validate());
		validate();
	}
	
	public void setValidatedText(String text) {
		super.setText(text);
		validate();
	}
	
	public void setValid(boolean isValid) {
		this.valid = isValid;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValue(String text) {
		super.setText(text);
	}
	
	private void validate() {
		if(validator.validate(this.getText())) {
			this.setBackground(new Background(new BackgroundFill(
					Color.LIGHTGREEN,new CornerRadii(5),null)));
			if(registeredButton != null && valid == false) registeredButton.addValid();
			valid = true;
		} else {
			this.setBackground(new Background(new BackgroundFill(
					Color.LIGHTPINK,new CornerRadii(5),null)));
			if(valid == true && registeredButton != null) registeredButton.takeValid();
			valid = false;
		}
	}
}
