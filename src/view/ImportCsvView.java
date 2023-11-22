package view;

import java.io.File;

import controller.AddController;
import controller.ViewController;
import controller.HomeController;
import controller.ImportCsvController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ImportCsvView extends DAHView{
	private ImportCsvController control;
	private Label importResult = new Label("");

	public ImportCsvView(ImportCsvController importCsvController) {
		control = importCsvController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		
		Button importB = new Button("Browse for Import CSV");
		Button defaultB = new Button("Import Default CSV");
		
		container.getChildren().addAll(importResult, importB, defaultB);
		
		middlePane.getChildren().add(container);
		
		defaultB.setOnAction(e->{
			clearInputResult();
			File file = new File("posts.csv");
			control.importCsv(file);
		});
		
		FileChooser fileChooser = new FileChooser();
		
		
		importB.setOnAction(e -> {
			clearInputResult();
			File file = fileChooser.showOpenDialog(control.getStage());
			control.importCsv(file);
		});
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	public void showImportResult(int imported, int failed) {
		importResult.setText(imported + " posts imported. " + failed + " lines with errors");
		
	}
	public void clearInputResult() {
		importResult = new Label("");
	}

	
}
