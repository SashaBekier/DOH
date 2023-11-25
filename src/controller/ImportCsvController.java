package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.DAHModel;
import model.User;
import view.PostsView;
import view.ImportCsvView;
import view.LogInView;

public class ImportCsvController implements ViewController {
	private ImportCsvView importCsv;
	private DAHController control;
	private DAHModel model;
	
	public ImportCsvController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		importCsv = new ImportCsvView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		return importCsv.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}
	public void importCsv(String file) {
		try {
			model.importPostsFrom(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
	public Stage getStage() {
		return control.getStage();
	}

}
