/**
 * @file MyView.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description implementation of the view
 * 				
 * @date    08/09/2016
 */
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import utils.PropertiesFile;

/**
 * The Class MyView.
 */
public class MyView extends Observable implements View {
	
	/** The cli. */
	private CLI cli;
	
	private GUI gui;
	
	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintWriter out;
	
	/** The map. */
	private HashMap<String, Command> map;
	
	
	/**
	 * Instantiates a new my view.
	 */
	public MyView(){
		this.cli=null;
		this.gui=null;
	}
	
	/**
	 * Instantiates a new my view.
	 *
	 * @param in the in
	 * @param out the out
	 */
	public MyView(BufferedReader in, PrintWriter out){
		this.in=in;
		this.out=out;
	}

	/**
	 * Start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException{
		String ui = PropertiesFile.getProperties().getUserInterface();
		switch(ui){
		case "cli":
			this.cli=new CLI(this);
			cli.start();
			break;
		case "gui":
			this.gui=new GUI(this);
			gui.start();
			break;
		}
	}

	/* (non-Javadoc)
	 * @see view.View#display(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void display(Maze3d maze,String name) {
		if(PropertiesFile.getProperties().getUserInterface()=="cli")
			out.println(maze);
		else
			updateMaze(maze, name);
	}

	/* (non-Javadoc)
	 * @see view.View#displaySolution(java.util.List)
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		out.println("Solution Path: "+solution.getSolution());
	}

	/* (non-Javadoc)
	 * @see view.View#dirPath(java.lang.String)
	 */
	@Override
	public void dirPath(String path) {
		try{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++){
		      if (listOfFiles[i].isFile())
		        out.println("File " + listOfFiles[i].getName());
		      else if (listOfFiles[i].isDirectory()) 
		        out.println("Directory " + listOfFiles[i].getName());
		    }
		}catch(NullPointerException e){
			printMessage("Bad parameters, try again");
		}
	}
	
	/* (non-Javadoc)
	 * @see view.View#printMessage(java.lang.String)
	 */
	@Override
	public void printMessage(String string){
		if(cli!=null)
			out.println(string);
		else
			gui.message(string);
		
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(int[][])
	 */
	@Override
	public void displayCrossSection(int[][]m2dmaze) {
		out.println(Arrays.deepToString(m2dmaze));

	}
	
	/* (non-Javadoc)
	 * @see view.View#exit()
	 */
	@Override
	public void exit() throws IOException {
		
		String s="save_solution_map";
		setChanged();
		notifyObservers(s);
		
		out.println("Quiting everything...");
		in.close();
		out.flush();
		out.close();
		System.exit(0);
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap(HashMap<String, Command> map) {
		this.map = map;
	}
	
	/* (non-Javadoc)
	 * @see view.View#getMap()
	 */
	public HashMap<String, Command> getMap() {
		return map;
	}

	/* (non-Javadoc)
	 * @see view.View#executeCommand(java.lang.String)
	 */
	public void executeCommand(String string){
		Scanner s = new Scanner(string);
		try{
		String command=s.next();
		if(map.containsKey(command)){
			setChanged();
			notifyObservers(string);
			s.close();
		}
		else
			printMessage("No such command");
		s.close();
		}catch(NoSuchElementException e){
			out.println("No such command");
		}
	}
		
	/* (non-Javadoc)
	 * @see view.View#getLine()
	 */
	public String getLine(){
		try {
			String line = new String (in.readLine());
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public void updateMaze(Maze3d maze, String name){
		this.gui.displayCurrentMaze(maze, name);
	}
	
	public void setCurrentPositionInGui(Position p){
		this.gui.setCurrentPosition(p);
		this.gui.setCharacterPositionInMazeDisplay(p);
	}

	@Override
	public void move(Position currentPosition) {
		this.gui.move(currentPosition);
	}


}
