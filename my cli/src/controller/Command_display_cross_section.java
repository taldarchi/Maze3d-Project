/**
 * @file Command_display_cross_section.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description tells view to return a 2d array of the maze in the desired index of the desired axis.
 * 				
 * @date    08/09/2016
 */
package controller;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/**
 * The Class Command_display_cross_section.
 */
public class Command_display_cross_section implements Command {
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command display cross section.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_display_cross_section(View view, Model model){
		this.view=view;
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		//get information from string before telling view
		String[] strings=string.split(" ");
		if(strings.length!=3)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!model.mazeNameCheck(name))
				System.out.println("Maze does not exist, try again");
			else{
				Maze3d maze=model.getMazeByName(name);
				String axis=strings[1];
				try{
				int index=Integer.parseInt(strings[2]);
				switch(axis){
				case "z":
				case "Z":
					int[][] maze2dz=maze.getCrossSectionByZ(index);
					view.displayCrossSection(maze2dz);
					break;
				case "x":
				case "X":
					int[][] maze2dx=maze.getCrossSectionByX(index);
					view.displayCrossSection(maze2dx);
					break;
				case "y":
				case "Y":
					int[][] maze2dy=maze.getCrossSectionByY(index);
					view.displayCrossSection(maze2dy);
					break;
				}
				}catch(NumberFormatException e){
					System.out.println("First type axis and then index!");
				}

			}

	}

}
}