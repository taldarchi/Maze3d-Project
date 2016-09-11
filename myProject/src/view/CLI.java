/**
 * @file CLI.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents the command line interface with the user
 * 				
 * @date    08/09/2016
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

import presenter.Command;
import presenter.Presenter;



/**
 * The Class CLI.
 */
public class CLI extends Observable{

	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintWriter out;
	
	/** The map. */
	private HashMap<String,Command> map;
	
	/** The exit. */
	boolean exit=false;
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param map the map
	 * @param m_presenter 
	 */
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> map, Presenter m_presenter) {
		super();
		this.in = in;
		this.out = out;
		this.map = map;
		this.addObserver(m_presenter);
	}
	
	/**
	 * Start.
	 * gets commands from user and analyses them.
	 * runs on a partial thread.
	 * @throws IOException 
	 */
	public void start() throws IOException{
	    out.println("\nPlease Choose a Command:\n\n"
	    		+ "dir <path> - Display files/folders in the desired path\n"
	    		+ "generate_3d_maze <name> <X,Y,Z> <algorithm> - Create a maze with desired parameters\n"
	    		+ "display <name> - Print maze\n"
	    		+ "display_cross_section <name> <X/Y/Z> <index> - Display 2d array of the desired index in a maze\n"
	    		+ "save_maze <name> <file name> - Save maze\n"
	    		+ "load_maze <file name> <name> - Load maze\n"
	    		+ "solve <name> <algorithm> - Solve desired maze with desired algorithm\n"
	    		+ "display_solution <name> - Display solution\n"
	    		+ "exit - Close and exit everything\n");
	new Thread(new Runnable(){
		@Override
		public void run() { 
			try{
			while(true){
				String line=new String (in.readLine());
				Scanner s = new Scanner(line);
				String command=s.next();
				if(map.containsKey(command)){
	    		setChanged();
	    		notifyObservers(line);
			}
	    	else
	    		out.println("No such command");
	    	s.close();
			}
			} catch (IOException e) {
				e.printStackTrace();}
		}
	}).start();
	
}
}
