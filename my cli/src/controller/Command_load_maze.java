/**
 * @file Command_load_maze.java
 * 
 * @author Tal Darchi
 * 
 * @description represents a command responsible to 
 *              load a desired maze from a file in a specific name.
 * 				
 * @date    08/09/2016
 */
package controller;

import java.io.IOException;

import model.Model;

/**
 * The Class Command_load_maze.
 */
public class Command_load_maze implements Command {
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command load maze.
	 *
	 * @param model the model
	 */
	public Command_load_maze(Model model){
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
			System.out.println("Bad parameters, try again");
		else{
			String fileName=strings[0];
			String nameToSave=strings[1];
			if(model.mazeNameCheck(nameToSave))
				System.out.println("Maze name already exists, try again");
			else
				model.loadMaze(fileName, nameToSave);

		}

	}

}
