package presenter;

import java.io.IOException;

import model.Model;
import utils.PropertiesFile;
import view.View;

public class Command_hint implements Command{
	private Model model;
	
	public Command_hint(View view,Model model){
		this.model=model;
	}

	@Override
	public void doCommand(String string) throws IOException {
		model.solveMaze(string, PropertiesFile.getProperties().getSearchAlgorithm());
		model.hint(string);
	}
	
	

}
