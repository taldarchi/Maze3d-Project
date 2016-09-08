package controller;

import java.io.IOException;

import model.Model;

public class Command_save_maze implements Command {
	private Model model;
	public Command_save_maze(Model model){
		this.model=model;
	}
	@Override
	public void doCommand(String string) throws IOException {
		String[] strings=string.split(" ");
		if(strings.length!=2)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			String fileName=strings[1];
			model.saveMaze(name,fileName);
			}

		}
}
