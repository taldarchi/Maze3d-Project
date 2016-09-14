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
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void generate3dMaze(String name, int z, int x, int y) throws InterruptedException, ExecutionException;
	
	/**
	 * Solve maze.
	 *
	 * @param mazeName the maze name
	 * @param algorithm the algorithm
	 */
	public void solveMaze(String mazeName);
	
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
	public Maze3d getMazeByName(String name);

	boolean solutionExists(Maze3d maze);
	
	public void saveSolutionMap(String fileName);
	public void loadSolutionMap(String fileName);

	public HashMap<String, Maze3d> getMazes();

	public void forward(String string);
	public void backwards(String string);
	public void up(String string);
	public void down(String string);
	public void left(String string);
	public void right(String string);



}
