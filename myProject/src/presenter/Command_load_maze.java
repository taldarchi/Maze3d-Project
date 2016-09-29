/**
 * @file Command_load_maze.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to 
 *              load a desired maze from a file in a specific name.
 * 				
 * @date    08/09/2016
 */
package presenter;

import java.io.IOException;

import model.Model;
import utils.PropertiesFile;
import view.View;

/**
 * The Class Command_load_maze.
 */
public class Command_load_maze implements Command {
	
	private View view;
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command load maze.
	 *
	 * @param model the model
	 */
	public Command_load_maze(View view,Model model){
		this.view=view;
		this.model=model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		//check for errors first
		String[] strings=string.split(" ");
		if(strings.length!=2)
			view.printMessage("Bad parameters, try again");
		else{
			String fileName=strings[0];
			String nameToSave=strings[1];
			if(model.mazeNameCheck(nameToSave))
				view.printMessage("Maze name already exists, try again");
			else{
				model.loadMaze(fileName, nameToSave);
				try{
				if(PropertiesFile.getProperties().getUserInterface().equals("gui"))
					view.updateMaze(model.getMazeByName(nameToSave).getMaze(), nameToSave);
				}catch(NullPointerException e){
				}
			}
			

		}

	}

}
