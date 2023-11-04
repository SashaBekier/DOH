package controller;

import java.io.FileNotFoundException;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.PostsView;
import view.ImportCsvView;
import view.LogInView;

public class ImportCsvController implements Controller {
	private ImportCsvView importCsv;
	private DAHController control;
	private DAHManager model;
	
	public ImportCsvController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		importCsv = new ImportCsvView(this);
		
	}
	@Override
	public Pane[] getPanes() {
		return importCsv.getPanes();
	}
	
	public Pane getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		User user = model.getActiveUser();
		String[] attribs = user.getAttributes();
		return attribs[2] + " " + attribs[3];
	}
	public void importCsv(String file) {
		try {
			model.importPostsFrom(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
