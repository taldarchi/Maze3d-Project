package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import controller.Controller;

public interface Model {
	
	public void setController(Controller controllerToCommunicate);
	public Maze3d loadMaze(String fileName, String nameToSave) throws IOException;
	public void generate3dMaze(String name, int z, int x, int y, String algorithm);
	public void solveMaze(String mazeName, String algorithm);
	boolean mazeNameCheck(String name);
	void saveMaze(String name, String fileName) throws IOException;
	public HashMap<String, List<State<Position>>> getSolutions();
	public Maze3d getMazeByName(String name);

}
