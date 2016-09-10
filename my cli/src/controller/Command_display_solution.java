/**
 * @file Command_display_solution.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description command responsible to tell view to print maze solution to the screen
 * 				
 * @date    08/09/2016
 */
package controller;

import java.util.List;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;
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
			System.out.println("Bad parameters, try again");
		else{
			String name=strings[0];
			if(!model.mazeNameCheck(name))
				System.out.println("Maze does not exist, try again");
			else{
				List<State<Position>> solution=model.getSolutions().get(string);
				view.displaySolution(solution);
			}
	}
	}
}
