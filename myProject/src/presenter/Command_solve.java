/**
 * @file Command_solve.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to 
 *              solve a maze with a desired algorithm.
 * 				
 * @date    08/09/2016
 */
package presenter;

import model.Model;
import utils.PropertiesFile;
import view.View;

/**
 * The Class Command_solve.
 */
public class Command_solve implements Command {
	
	private View view;
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command solve.
	 *
	 * @param model the model
	 */
	public Command_solve(View view,Model model){
		this.view=view;
		this.model=model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		//check for errors first
		String[] strings=string.split(" ");
		if(strings.length!=1 &&strings.length!=2)
			view.printMessage("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!model.mazeNameCheck(name))
				view.printMessage("Maze does not exist, try again");
			else if(model.solutionExists(model.getMazes().get(name).getMaze())){
				//view.printMessage("Solution for "+name+" already exists:");
				view.displaySolution(model.getSolutions().get(model.getMazes().get(name).getMaze()));
			}
			else{
				String algorithm=null;
				if(strings.length==2)
					algorithm=strings[1];
				model.solveMaze(name,algorithm);
				if(PropertiesFile.getProperties().getUserInterface().equals("gui"))
					view.displaySolution(model.getSolutions().get(model.getMazeByName(name).getMaze()));
			}
	}
	}
}
