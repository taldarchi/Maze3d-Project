package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

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
	public CLI(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		HashMap<String,Command> map=new HashMap<String,Command>();
	}
	
	public void start(){
		String line;
	    System.out.println("Enter String");
		try {
			while((line=in.readLine())!="exit"){	
				Scanner s = new Scanner(line);
				String command=s.next();
				
				if(map.containsKey(command))
					switch (command){
					case "dir":
						Command_dir dir=(Command_dir) map.get("dir");
						command=s.next();
						out.write(command);						    
							
					default:
						System.out.println("No such command.");
					}
				s.close();
			}
			Command_exit exit=(Command_exit) map.get("exit");
			exit.doCommand();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public PrintWriter getCommand() {
		return this.out;
	}


}
