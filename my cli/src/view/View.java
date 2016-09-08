package view;

import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;

public interface View {
	
	public void dirPath(String path);
	public void display(String name);
	public void displaySolution(List<State<Position>> s);
	public void printMessage(String s);
	void displayCrossSection(Maze3d maze2, String axis, int index);
	

}
