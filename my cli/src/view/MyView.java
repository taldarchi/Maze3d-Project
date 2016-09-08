package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import controller.Command;

public class MyView implements View {
	
	private CLI cli;
	private HashMap<String,Maze3d> mazes=new HashMap<String,Maze3d>();
	
	public MyView(){
		this.cli=null;
	}

	public void start(BufferedReader in, PrintWriter out, HashMap<String, Command> map) throws IOException{
		this.cli=new CLI(in,out,map);
		cli.start();
	}

	@Override
	public void display(String name) {
		if(mazes.containsKey(name))
			System.out.println(mazes.get(name));
		else
			System.out.println("No such maze");
		
	}

	@Override
	public void displaySolution(List<State<Position>> s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dirPath(String path) {
		try{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++){
		      if (listOfFiles[i].isFile())
		        System.out.println("File " + listOfFiles[i].getName());
		      else if (listOfFiles[i].isDirectory()) 
		        System.out.println("Directory " + listOfFiles[i].getName());
		    }
		}catch(NullPointerException e){
			System.out.println("Bad parameters, try again");
		}
		
	}
	
	@Override
	public void printMessage(String name) {
		System.out.println("Maze "+name+" is ready");
		
	}
	@Override
	public void displayCrossSection(Maze3d maze2, String axis, int index) {
		switch(axis){
		case "z":
		case "Z":
			int[][] maze2dz=maze2.getCrossSectionByZ(index);
			System.out.println(Arrays.deepToString(maze2dz));
			break;
		case "x":
		case "X":
			int[][] maze2dx=maze2.getCrossSectionByX(index);
			System.out.println(Arrays.deepToString(maze2dx));
			break;
		case "y":
		case "Y":
			int[][] maze2dy=maze2.getCrossSectionByY(index);
			System.out.println(Arrays.deepToString(maze2dy));
			break;
			
		}
		
	}

	public void saveToHashMap(String name, Maze3d maze) {
		mazes.put(name, maze);
		
	}
	public boolean mazeNameCheck(String name){
		return mazes.containsKey(name);
	}

	public Maze3d getMazeByName(String name) {
		return mazes.get(name);
	}


}
