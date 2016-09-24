/**
 * @file Command_display_solution.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description command responsible to tell view to print maze solution to the screen
 * 				
 * @date    08/09/2016
 */
package presenter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class Command_display_solution implements Command {
	private View view;
	private Model model;
	public Command_display_solution(View view, Model model){
		this.view=view;
		this.model=model;
	}

	@Override
	public void doCommand(String string) {
		//check for errors first
		String[] strings=string.split(" ");
		if(strings.length!=1)
			view.printMessage("Bad parameters, try again");
		else{
			String name=strings[0];
			Maze3d maze=model.getMazes().get(name).getMaze();
			if(!model.mazeNameCheck(name))
				view.printMessage("Maze does not exist, try again");
			else if(!model.solutionExists(maze))
				view.printMessage("Solution does not exist yet for "+name);
			else{
				Solution<Position> solution=model.getSolutions().get(model.getMazes().get(name));
				view.displaySolution(solution);
			}
	}
	}
}
