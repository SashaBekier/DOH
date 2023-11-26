package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.ExceptionPopUp;
import view.ImportCsvView;

public class ImportCsvController extends ViewController {
	private ImportCsvView importCsv;
	
	public ImportCsvController(DAHController cont) {
		super(cont);
		importCsv = new ImportCsvView(this);
		super.setView(importCsv);
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	public Stage getStage() {
		return control.getStage();
	}

	public void importCsv(File file) {
		try {
			int[] result = model.importPostsFrom(file);
			importCsv.showImportResult(result[0], result[1]);
			control.updateStage(DAHScreen.IMPORT);
		} catch (FileNotFoundException e) {
			new ExceptionPopUp(e);
		}
	}
}
