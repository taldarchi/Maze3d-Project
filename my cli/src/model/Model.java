package model;

import java.io.IOException;

import algorithms.mazeGenerators.Maze3d;

public interface Model {
	
	public void generate3dMaze(String name,int z,int x, int y);
	public Maze3d loadMaze();
	public void solveMaze(String s);
	public void saveMaze(Maze3d maze, String fileName) throws IOException;

	
}
