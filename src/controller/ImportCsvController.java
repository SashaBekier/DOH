package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DAHModel;
import view.ImportCsvView;

public class ImportCsvController implements ViewController {
	private ImportCsvView importCsv;
	private DAHController control;
	private DAHModel model;

	public ImportCsvController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		importCsv = new ImportCsvView(this);
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	@Override
	public HBox[] getPanes() {
		return importCsv.getPanes();
	}

	public Stage getStage() {
		return control.getStage();
	}

	public void importCsv(File file) {
		try {
			int[] result = model.importPostsFrom(file);
			importCsv = new ImportCsvView(this);
			importCsv.showImportResult(result[0], result[1]);
			control.updateStage(DAHScreen.IMPORT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
