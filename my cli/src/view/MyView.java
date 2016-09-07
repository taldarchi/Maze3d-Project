package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

public class MyView implements Facade {
	
	private CLI cli;
	
	public MyView(){
		this.cli=null;
	}

	public void start(BufferedReader in, PrintWriter out, HashMap<String, Command> map) throws IOException{
		this.cli=new CLI(in,out,map);
		cli.start();
	}

	@Override
	public void displayData(Maze3d maze) {
		System.out.println(maze);
		
	}

}
