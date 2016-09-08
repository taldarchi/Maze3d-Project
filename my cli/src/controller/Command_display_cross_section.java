package controller;

import algorithms.mazeGenerators.Maze3d;

public class Command_display_cross_section implements Command {

	@Override
	public void doCommand(String string) {
		String[] strings=string.split(" ");
		if(strings.length!=3)
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!view.mazeNameCheck(name))
				System.out.println("Maze does not exist, try again");
			else{
				Maze3d maze=view.getMazeByName(name);
				String axis=strings[1];
				try{
				int index=Integer.parseInt(strings[2]);
				view.displayCrossSection(maze,axis,index);
				}catch(NumberFormatException e){
					System.out.println("First type axis and then index!");
				}

			}

	}

}
}