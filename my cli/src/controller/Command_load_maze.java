package controller;

import java.io.IOException;

import model.Model;

public class Command_load_maze implements Command {
	private Model model;
	public Command_load_maze(Model model){
		this.model=model;
	}
	@Override
	public void doCommand(String string) throws IOException {
		String[] strings=string.split(" ");
		if(strings.length!=2)
			System.out.println("Bad parameters, try again");
		else{
			String fileName=strings[0];
			String nameToSave=strings[1];
			if(model.mazeNameCheck(nameToSave))
				System.out.println("Maze name already exists, try again");
			else
				model.loadMaze(fileName, nameToSave);

		}

	}

}
