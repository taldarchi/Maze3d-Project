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
package controller;

import model.Model;

/**
 * The Class Command_solve.
 */
public class Command_solve implements Command {
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command solve.
	 *
	 * @param model the model
	 */
	public Command_solve(Model model){
		this.model=model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		//check for errors first
		String[] strings=string.split(" ");
		if(strings.length!=2)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!model.mazeNameCheck(name))
				System.out.println("Maze does not exist, try again");
			else{
			String algorithm=strings[1];
			model.solveMaze(name,algorithm);
			}

	}
	}
}