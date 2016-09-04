package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Command_dir;
import controller.Command_exit;

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

	public CLI(BufferedReader in, PrintWriter out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	public void start(){
		String line;
	    System.out.print("Enter String");
		try {
			while((line=in.readLine())!="exit"){		
				if(map.containsKey(line))
					switch (line){
					case "dir":
						Command_dir dir=(Command_dir) map.get("dir");
						dir.doCommand();
						    
							
					default:
						System.out.println("No such command.");
					}
			}
			Command_exit exit=(Command_exit) map.get("exit");
			exit.doCommand();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
