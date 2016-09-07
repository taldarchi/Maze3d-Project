package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.Command;

public class CLI extends Thread{

	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String,Command> map;
	
	
	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> map) {
		super();
		this.in = in;
		this.out = out;
		this.map = map;
	}
	
	public void start(){
		String line;
	    System.out.println("Please Choose a Command:\n\n"
	    		+ "dir <path> - Display files/folders in the desired path\n"
	    		+ "generate_3d_maze <name> <(X,Y,Z)> - Create a maze with desired parameters\n"
	    		+ "display <name> - Print maze\n"
	    		+ "display_cross_section <index {X,Y,Z}> <name> - Display 2d array of the desired index in a maze\n"
	    		+ "save_maze <name> <file name> - Save maze\n"
	    		+ "load_maze <file name> <name> - Load maze\n"
	    		+ "solve <name> <algorithm> - Solve desired maze with desired algorithm\n"
	    		+ "display_solution <name> - Display solution\n"
	    		+ "exit - Close and exit everything\n");
		try {
			while(!(line=in.readLine()).equalsIgnoreCase("exit")){
				try{
				Scanner s = new Scanner(line);
				String string = new String();
				String command=s.next();
				if(map.containsKey(command))
					switch (command){
					case "dir":
						Command dir=map.get("dir");
						boolean bool=true;
						while(s.hasNext()){
							if(bool){
								string+=s.next();
								bool=false;
							}
							else
								string+=" "+s.next();
						}
						dir.doCommand(string);
						s.close();
						break;
					case "generate_3d_maze":
						Command generate=map.get("generate_3d_maze");
						while(s.hasNext())
							string+=s.next()+ " ";
						generate.doCommand(string);
						s.close();
						break;
					case "display":
						s.close();
						break;
					case "display_cross_section":
						s.close();
						break;
					case "save_maze":
						s.close();
						break;
					case "load_maze":
						s.close();
						break;
					case "solve":
						s.close();
						break;
					case "display_solution":
						s.close();
						break;
					default:
						System.out.println("No such command");
						break;
					}
				else
					System.out.println("No such command");
			}catch(NoSuchElementException e){}
			}
		// exit stuff
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PrintWriter getCommand() {
		return this.out;
	}


}
