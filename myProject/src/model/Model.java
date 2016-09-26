/**
 * @file Model.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description interface for the model in the MVC
 * 				
 * @date    08/09/2016
 */
package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Interface Model.
 */
public interface Model{
	
	/**
	 * Load maze.
	 *
	 * @param fileName the file name
	 * @param nameToSave the name to save
	 * @return the maze 3 d
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Maze3d loadMaze(String fileName, String nameToSave) throws IOException;
	
	/**
	 * Generate 3 d maze.
	 *
	 * @param name the name
	 * @param z the z
	 * @param x the x
	 * @param y the y
	 * @param algorithm the algorithm
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public void generate3dMaze(String name, int z, int x, int y,String algorithm) throws InterruptedException, ExecutionException;
	
	/**
	 * Solve maze.
	 *
	 * @param mazeName the maze name
	 * @param algorithm the algorithm
	 */
	public void solveMaze(String mazeName, String algorithm);
	
	/**
	 * Maze name check.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	boolean mazeNameCheck(String name);
	
	/**
	 * Save maze.
	 *
	 * @param name the name
	 * @param fileName the file name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void saveMaze(String name, String fileName) throws IOException;
	
	/**
	 * Gets the solutions.
	 *
	 * @return the solutions
	 */
	public HashMap<Maze3d, Solution<Position>> getSolutions();
	
	/**
	 * Gets the maze by name.
	 *
	 * @param name the name
	 * @return the maze by name
	 */
	public PlayedMaze getMazeByName(String name);

	/**
	 * Solution exists.
	 *
	 * @param maze the maze
	 * @return true, if successful
	 */
	boolean solutionExists(Maze3d maze);
	
	/**
	 * Save solution map.
	 */
	public void saveSolutionMap();
	
	/**
	 * Load solution map.
	 */
	public void loadSolutionMap();

	/**
	 * Gets the mazes.
	 *
	 * @return the mazes
	 */
	public HashMap<String, PlayedMaze> getMazes();

	/**
	 * Forward.
	 *
	 * @param string the string
	 */
	public void forward(String string);
	
	/**
	 * Backwards.
	 *
	 * @param string the string
	 */
	public void backwards(String string);
	
	/**
	 * Up.
	 *
	 * @param string the string
	 */
	public void up(String string);
	
	/**
	 * Down.
	 *
	 * @param string the string
	 */
	public void down(String string);
	
	/**
	 * Left.
	 *
	 * @param string the string
	 */
	public void left(String string);
	
	/**
	 * Right.
	 *
	 * @param string the string
	 */
	public void right(String string);
	
	/**
	 * Hint.
	 *
	 * @param name the name
	 */
	public void hint(String name);

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition();
	
	/**
	 * Read properties.
	 *
	 * @param filename the filename
	 */
	public void readProperties(String filename);






}
