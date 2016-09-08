package view;

import java.io.IOException;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;

public interface View {
	
	public void dirPath(String path);
	public void printMessage(String string);
	public void exit() throws IOException;
	public void displaySolution(List<State<Position>> solution);
	public void display(Maze3d maze);
	public void displayCrossSection(int[][]m2dmaze);
	

}
