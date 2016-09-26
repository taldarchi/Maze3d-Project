package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Class PlayedMaze.
 */
public class PlayedMaze {
	
	/** The maze. */
	private Maze3d maze;
	
	/** The current. */
	private Position current;
	
	/** The solution. */
	private Solution<Position> solution;


	/**
	 * Instantiates a new played maze.
	 */
	public PlayedMaze() {
		this.maze = null;
		this.current = null;
		this.solution = null;
	}
	

	/**
	 * Instantiates a new played maze.
	 *
	 * @param maze the maze
	 */
	public PlayedMaze(Maze3d maze) {
		this.maze = maze;
		this.current = maze.getStartPosition();
		this.solution = null;
	}
	

	/**
	 * Sets the current position.
	 *
	 * @param currentPosition the new current position
	 */
	public void setCurrentPosition(Position currentPosition) {
		this.current = currentPosition;
	}

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return this.current;
	}


	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return maze;
	}


	/**
	 * Sets the maze.
	 *
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}


	/**
	 * Gets the solution.
	 *
	 * @return the solution
	 */
	public Solution<Position> getSolution() {
		return solution;
	}


	/**
	 * Sets the solution.
	 *
	 * @param solution the new solution
	 */
	public void setSolution(Solution<Position> solution) {
		this.solution = solution;
	}
}
