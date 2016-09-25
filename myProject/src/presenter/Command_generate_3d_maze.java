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
import utils.PropertiesFile;
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
		if(strings.length!=4 &&strings.length!=5)
			view.printMessage("Bad parameters, try again");
		else{
			String name=strings[0];
			if(model.mazeNameCheck(name))
				view.printMessage("Maze name already exists, try again");
			else{
				int z=Integer.parseInt(strings[1]);
				int x=Integer.parseInt(strings[2]);
				int y=Integer.parseInt(strings[3]);
				String algorithm=null;
				try {
				if(strings.length==5){
					algorithm=strings[4];
					if(!algorithm.equals("simple")&&!algorithm.equals("growing_tree_random")&&!algorithm.equals("growing_tree_last"))
						view.printMessage("Algorithm does not exist, try again");
					else{
						model.generate3dMaze(name,z,x,y,algorithm);
						view.updateMaze(model.getMazeByName(name).getMaze(), name);
					}
				}
				else{
					model.generate3dMaze(name,z,x,y,algorithm);
					if(PropertiesFile.getProperties().getUserInterface().equals("gui"))
						view.updateMaze(model.getMazeByName(name).getMaze(), name);
				}
				} catch (InterruptedException|ExecutionException e) {
					e.printStackTrace();	
				}
			}
		}
	}
}
