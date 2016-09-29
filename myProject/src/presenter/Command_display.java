/**
 * @file Command_display.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to display maze.
 * 				
 * @date    08/09/2016
 */
package presenter;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/**
 * The Class Command_display.
 */
public class Command_display implements Command {
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command display.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_display(View view, Model model){
		this.view=view;
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		Maze3d maze=model.getMazeByName(string).getMaze();
		view.display(maze,string);


	}

}
