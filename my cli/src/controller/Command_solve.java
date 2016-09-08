package controller;

import model.Model;

public class Command_solve implements Command {
	private Model model;
	public Command_solve(Model model){
		this.model=model;
	}
	
	@Override
	public void doCommand(String string) {
		String[] strings=string.split(" ");
		if(strings.length!=2)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!model.mazeNameCheck(name))
				System.out.println("Maze does not exist, try again");
			else{
			String algorithm=strings[1];
			model.solveMaze(name,algorithm);
			}

	}
	}
}
