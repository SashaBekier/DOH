package view;

import java.io.File;

import controller.ImportCsvController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import view.controls.DAHStyles;

public class ImportCsvView extends DAHView{
	private ImportCsvController control;
	private Label importResult = new Label("I am the import result field");

	public ImportCsvView(ImportCsvController importCsvController) {
		control = importCsvController;
		drawTop();
		drawMiddle();
	}
	
	public void showImportResult(int imported, int failed) {
		importResult.setText(imported + " posts imported. " + failed + " lines with errors");
		drawMiddle();
	}
	
	public void clearInputResult() {
		importResult = new Label("");
	}
	
	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		
		Button importB = new Button("Browse for Import CSV");
		importB.setMinSize(300, 100);
		Button defaultB = new Button("Import Default CSV");
		defaultB.setMinSize(300, 50);
		Label title = new Label("Import Posts from CSV");
		title.setFont(DAHStyles.HEADING);
		title.setTranslateY(50);
		container.getChildren().addAll(title,
				DAHStyles.verticalSpacer(DAHStyles.MIN_STAGE_HEIGHT/6),
				importResult, importB, defaultB);
		container.setSpacing(10);
		container.setAlignment(Pos.CENTER);
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
			if(file!=null) control.importCsv(file);
		});
	}

	private void drawTop() {
		topPane = control.getDashboard();
	}

	
}
