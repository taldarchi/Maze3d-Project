package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class PlayedMaze {
	private Maze3d maze;
	private Position current;
	private Solution<Position> solution;


	public PlayedMaze() {
		this.maze = null;
		this.current = null;
		this.solution = null;
	}
	

	public PlayedMaze(Maze3d maze) {
		this.maze = maze;
		this.current = maze.getStartPosition();
		this.solution = null;
	}
	

	public void setCurrentPosition(Position currentPosition) {
		this.current = currentPosition;
	}

	public Position getCurrentPosition() {
		return this.current;
	}


	public Maze3d getMaze() {
		return maze;
	}


	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}


	public Solution<Position> getSolution() {
		return solution;
	}


	public void setSolution(Solution<Position> solution) {
		this.solution = solution;
	}
}
