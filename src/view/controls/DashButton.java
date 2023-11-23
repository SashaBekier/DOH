package view.controls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;


public class DashButton extends Button {
	public DashButton(String label,String picPath) {
		this.setText(label);
		Image pic = new Image(picPath);
		this.setGraphic(new ImageView(pic));
		this.setContentDisplay(ContentDisplay.TOP);
		this.setPrefSize(100, 100);
		this.setBackground(DAHStyles.BLUE_BG);
		this.setBorder(DAHStyles.BLUE_BORDER);	
		
		this.setPadding(new Insets(2));
	}
}
