package controller;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class Command_display implements Command {
	private View view;
	private Model model;
	public Command_display(View view, Model model){
		this.view=view;
		this.model=model;
	}

	@Override
	public void doCommand(String string) {
		Maze3d maze=model.getMazeByName(string);
		view.display(maze);
	}

}
