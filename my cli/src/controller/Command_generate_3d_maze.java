/**
 * @file Command_generate_3d_maze.java
 * 
 * @author Tal Darchi
 * 
 * @description represents a command responsible to 
 *              generate a maze in the model.
 * 				
 * @date    08/09/2016
 */
package controller;

import model.Model;

/**
 * The Class Command_generate_3d_maze.
 */
public class Command_generate_3d_maze implements Command {
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command generate 3 d maze.
	 *
	 * @param model the model
	 */
	public Command_generate_3d_maze(Model model){
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		//check for errors first
		String[] strings=string.split(" |,");
		if(strings.length!=5)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(model.mazeNameCheck(name))
				System.out.println("Maze name already exists, try again");
			else{
				String algorithm=strings[4];
				if(!algorithm.equalsIgnoreCase("simple") && !algorithm.equalsIgnoreCase("growing_tree_last") && !algorithm.equalsIgnoreCase("growing_tree_random"))
					System.out.println("Wrong algorithm, try again");
				else{
					int z=Integer.parseInt(strings[1]);
					int x=Integer.parseInt(strings[2]);
					int y=Integer.parseInt(strings[3]);
					model.generate3dMaze(name,z,x,y,algorithm);
					
				}
			}
		}
	}

}
