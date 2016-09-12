package presenter;

import java.io.IOException;

import model.Model;
import view.View;

public class Command_load_solution_map implements Command {
	
	private View view;
	private Model model;

	public Command_load_solution_map(View view, Model model) {
		this.view=view;
		this.model=model;
	}

	@Override
	public void doCommand(String string) throws IOException {
		//check for errors first
		String[] strings=string.split(" ");
		if(strings.length!=1)
			view.printMessage("Bad parameters, try again");
		else{
			String fileName=strings[0];
			model.loadSolutionMap(fileName);
			}
				
	}

}
