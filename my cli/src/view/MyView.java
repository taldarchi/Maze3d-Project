/**
 * @file MyView.java
 * 
 * @author Tal Darchi
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
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import controller.Command;
import controller.Controller;
import controller.MyController;

/**
 * The Class MyView.
 */
public class MyView implements View {
	
	/** The cli. */
	private CLI cli;
	
	/** The m controller. */
	private Controller m_controller;
	
	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintWriter out;
	
	/** The map is set. */
	private boolean mapIsSet=false;
	
	/** The map. */
	private HashMap<String, Command> map;

	
	/**
	 * Instantiates a new my view.
	 */
	public MyView(){
		this.cli=null;
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
		if (mapIsSet != true){
			out.println("Map is not set yet");
			out.flush();
			return;
		}
		this.cli=new CLI(in,out,map);
		cli.start();
	}

	/* (non-Javadoc)
	 * @see view.View#display(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void display(Maze3d maze) {
		out.println(maze);
	}

	/* (non-Javadoc)
	 * @see view.View#displaySolution(java.util.List)
	 */
	@Override
	public void displaySolution(List<State<Position>> solution) {
				out.println("Solution Path: "+solution);
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
			out.println("Bad parameters, try again");
		}
	}
	
	/* (non-Javadoc)
	 * @see view.View#printMessage(java.lang.String)
	 */
	@Override
	public void printMessage(String string) {
		out.println(string);
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
	public void exit() throws IOException 
	{
		out.println("Quiting everything...");
		out.println("Done.");
		in.close();
		out.flush();
		out.close();
		System.exit(0);
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(MyController controller) {
		this.m_controller=controller;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap(HashMap<String, Command> map) {
		this.map = map;
		this.mapIsSet=true;
	}
	
	/**
	 * Gets the m controller.
	 *
	 * @return the m controller
	 */
	public Controller getM_controller() {
		return m_controller;
	}

}
