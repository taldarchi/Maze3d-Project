package controller;

import model.Model;

public class Command_generate_3d_maze implements Command {
	
	private Model model;
	public Command_generate_3d_maze(Model model){
		this.model=model;
	}

	@Override
	public void doCommand(String string) {
		String[] strings=string.split(" |,");
		if(strings.length!=5)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(model.mazeNameCheck(name))
				System.out.println("Maze name already exists, try again");
			else{
				String algorithm=strings[4];
				if(!algorithm.equalsIgnoreCase("simple") && !algorithm.equalsIgnoreCase("growing_tree_last") && !algorithm.equalsIgnoreCase("growing_tree_random"))
					System.out.println("Wrong algorithm, try again");
				else{
					int z=Integer.parseInt(strings[1]);
					int x=Integer.parseInt(strings[2]);
					int y=Integer.parseInt(strings[3]);
					model.generate3dMaze(name,z,x,y,algorithm);
					
				}
			}
		}
	}

}
