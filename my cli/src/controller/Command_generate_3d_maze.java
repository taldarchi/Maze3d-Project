package controller;

import algorithms.mazeGenerators.Maze3d;

public class Command_generate_3d_maze implements Command {

	@Override
	public void doCommand(String string) {
		String[] strings=string.split(" |,");
		if(strings.length!=4)
			System.out.println("Bad parameters, try again");
		else{
			
			String name=strings[0];
			if(view.mazeNameCheck(name))
				System.out.println("Maze name already exists, try again");
			else{
				int z=Integer.parseInt(strings[1]);
				int x=Integer.parseInt(strings[2]);
				int y=Integer.parseInt(strings[3]);
				model.generate3dMaze(name,z,x,y);
				Maze3d maze=model.getMaze();
				view.saveToHashMap(name, maze);
			}
		}
	}

}
