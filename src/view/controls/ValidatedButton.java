package view.controls;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class ValidatedButton extends Button {
	private int target = 0;
	private int validControls = 0;
	
	public ValidatedButton(String buttonText) {
		super(buttonText);
	}
	public ValidatedButton(String buttonText, Node buttonNode) {
		super(buttonText, buttonNode);
	}
	
	public void addTarget() {
		target++;
		this.setDisable(true);
	}
	
	public void addValid() {
		validControls++;
		if(buttonValid()) {
			this.setDisable(false);
		}
	}
	
	public void removeValid() {
		validControls--;
		this.setDisable(true);
	}
	
	public boolean buttonValid() {
		return target == validControls;
	}

}
