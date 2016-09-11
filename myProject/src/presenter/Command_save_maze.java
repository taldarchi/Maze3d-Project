/**
 * @file Command_load_maze.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to 
 *              save a desired maze to a file in a specific name.
 * 				
 * @date    08/09/2016
 */
package presenter;

import java.io.IOException;

import model.Model;

/**
 * The Class Command_save_maze.
 */
public class Command_save_maze implements Command {
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command save maze.
	 *
	 * @param model the model
	 */
	public Command_save_maze(Model model){
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
			String name=strings[0];
			String fileName=strings[1];
			model.saveMaze(name,fileName);
			}

		}
}
