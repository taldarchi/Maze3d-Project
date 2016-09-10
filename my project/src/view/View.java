/**
 * @file View.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description interface for the view in the MVC
 * 				
 * @date    08/09/2016
 */
package view;

import java.io.IOException;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;

/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Dir path.
	 * displays files and/or directories in a desired path on the system
	 *
	 * @param path the path
	 */
	public void dirPath(String path);
	
	/**
	 * Prints the message.
	 *
	 * @param string the string
	 */
	public void printMessage(String string);
	
	/**
	 * Exit.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exit() throws IOException;
	
	/**
	 * Display solution.
	 *
	 * @param solution the solution
	 */
	public void displaySolution(List<State<Position>> solution);
	
	/**
	 * Display.
	 *
	 * @param maze the maze
	 */
	public void display(Maze3d maze);
	
	/**
	 * Display cross section.
	 *
	 * @param m2dmaze the m 2 dmaze
	 */
	public void displayCrossSection(int[][]m2dmaze);
	

}
