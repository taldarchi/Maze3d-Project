package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import model.MyModel;
import view.CLI;
import view.MyView;

public class MyController implements Facade{
	private MyView view;
	private MyModel model;
	
	public MyController(MyModel model, MyView view){
	      this.model = model;
	      this.view = view;
		  HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir());
		  map.put("generate_3d_maze", new Command_generate_3d_maze());
		  map.put("display", new Command_display());
		  map.put("display_cross_section", new Command_display_cross_section());
          map.put("save_maze", new Command_save_maze());
		  map.put("load_maze", new Command_load_maze());
		  map.put("solve", new Command_solve());
		  map.put("display_solution", new Command_display_solution());
		  map.put("exit", new Command_exit());
		  
	    		  
	   }
	
	public void work() throws IOException{
		view.start();
		CLI cli=view.getCLI();
		PrintWriter command=cli.getCommand();
		String s=command.toString();
		
		
	}


}
