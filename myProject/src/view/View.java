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
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;

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
	public void displaySolution(Solution<Position> solution);
	
	/**
	 * Display.
	 *
	 * @param maze the maze
	 * @param name the name
	 */
	public void display(Maze3d maze,String name);
	
	/**
	 * Display cross section.
	 *
	 * @param m2dmaze the m 2 dmaze
	 */
	public void displayCrossSection(int[][]m2dmaze);
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String, Command> getMap();
	
	/**
	 * Execute command.
	 *
	 * @param string the string
	 */
	public void executeCommand(String string);
	
	/**
	 * Gets the line.
	 *
	 * @return the line
	 */
	public String getLine();
	
	/**
	 * Update maze.
	 *
	 * @param maze the maze
	 * @param name the name
	 */
	public void updateMaze(Maze3d maze, String name);
	
	/**
	 * Sets the hint position in gui.
	 *
	 * @param p the new hint position in gui
	 */
	public void setHintPositionInGui(Position p);

	/**
	 * Move.
	 *
	 * @param currentPosition the current position
	 */
	public void move(Position currentPosition);


}
