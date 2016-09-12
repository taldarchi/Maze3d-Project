/**
 * @file Command_generate_3d_maze.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to 
 *              generate a maze in the model.
 * 				
 * @date    08/09/2016
 */
package presenter;

import java.util.concurrent.ExecutionException;

import model.Model;
import view.View;

/**
 * The Class Command_generate_3d_maze.
 */
public class Command_generate_3d_maze implements Command {
	
	private View view;
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command generate 3 d maze.
	 *
	 * @param model the model
	 */
	public Command_generate_3d_maze(View view,Model model){
		this.view=view;
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string){
		//check for errors first
		String[] strings=string.split(" |,");
		if(strings.length!=5)
			view.printMessage("Bad parameters, try again");
		else{
			String name=strings[0];
			if(model.mazeNameCheck(name))
				view.printMessage("Maze name already exists, try again");
			else{
				String algorithm=strings[4];
				if(!algorithm.equalsIgnoreCase("simple") && !algorithm.equalsIgnoreCase("growing_tree_last") && !algorithm.equalsIgnoreCase("growing_tree_random"))
					view.printMessage("Wrong algorithm, try again");
				else{
					int z=Integer.parseInt(strings[1]);
					int x=Integer.parseInt(strings[2]);
					int y=Integer.parseInt(strings[3]);
					try {
						model.generate3dMaze(name,z,x,y,algorithm);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}

}
