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

public class MyView implements View {
	
	private CLI cli;
	private Controller m_controller;
	private BufferedReader in;
	private PrintWriter out;
	private boolean mapIsSet=false;
	private HashMap<String, Command> map;

	
	public MyView(){
		this.cli=null;
	}
	public MyView(BufferedReader in, PrintWriter out){
		this.in=in;
		this.out=out;
	}

	public void start() throws IOException{
		if (mapIsSet != true)
		{
			out.println("Map is not set yet");
			out.flush();
			
			return;
		}
		this.cli=new CLI(in,out,map);
		cli.start();
	}

	@Override
	public void display(Maze3d maze) {
		out.println(maze);
	}

	@Override
	public void displaySolution(List<State<Position>> solution) {
				out.println("Solution Path: "+solution);
	}

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
	
	@Override
	public void printMessage(String string) {
		out.println(string);
	}
	@Override
	public void displayCrossSection(int[][]m2dmaze) {
		out.println(Arrays.deepToString(m2dmaze));

	}
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

	public void setController(MyController controller) {
		this.m_controller=controller;
	}
	
	public void setMap(HashMap<String, Command> map) {
		this.map = map;
		this.mapIsSet=true;
	}
	public Controller getM_controller() {
		return m_controller;
	}

}
