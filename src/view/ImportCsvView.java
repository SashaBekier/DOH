package view;

import controller.AddController;
import controller.Controller;
import controller.HomeController;
import controller.ImportCsvController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ImportCsvView extends View{
	private ImportCsvController control;

	public ImportCsvView(ImportCsvController importCsvController) {
		control = importCsvController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		Button importB = new Button("Import Default CSV");
		middlePane.getChildren().add(importB);
		
		importB.setOnAction(e->{
			control.importCsv("posts.csv");
		});
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
