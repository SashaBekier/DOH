package view.controls;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class ValidatedTextField extends TextField implements ValidatedControl {
	private boolean valid = false;
	private ValidatedButton registeredButton;
	
	
	public void registerButton(ValidatedButton button) {
		registeredButton = button;
		button.addTarget();
	}
	
	public ValidatedTextField(TextValidator validator, ValidatedButton button) {
		this(validator);
		this.registerButton(button);
	}
	
	public ValidatedTextField(TextValidator validator) {
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
	
	public void setValid(boolean isValid) {
		this.valid = isValid;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValue(String text) {
		this.setValue(text);
	}
}
