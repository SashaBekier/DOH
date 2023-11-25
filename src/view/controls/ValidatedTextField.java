package view.controls;

import javafx.scene.control.TextField;

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
			this.setBackground(DAHStyles.VALID_BG);
			this.setBorder(DAHStyles.VALID_BORDER);
			if(registeredButton != null && valid == false) registeredButton.addValid();
			valid = true;
		} else {
			this.setBackground(DAHStyles.INVALID_BG);
			this.setBorder(DAHStyles.INVALID_BORDER);
			if(valid == true && registeredButton != null) registeredButton.removeValid();
			valid = false;
		}
	}
}
