package controller;

import java.io.IOException;

import algorithms.mazeGenerators.Maze3d;

public class Command_save_maze implements Command {

	@Override
	public void doCommand(String string) throws IOException {
		String[] strings=string.split(" ");
		if(strings.length!=2)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(view.mazeNameCheck(name))
				System.out.println("Maze name already exists, try again");
			else{
				String fileName=strings[1];
				Maze3d maze=view.getMazeByName(name);
				model.saveMaze(maze,fileName);
			}

		}
}
}
