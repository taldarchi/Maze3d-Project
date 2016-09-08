package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import model.MyModel;
import view.MyView;

public class MyController implements Observer{
	private MyView view;
	private MyModel model;

	public MyController(){
		MyView view=new MyView();
		MyModel model=new MyModel();
	}
	public MyController(MyView view, MyModel model){
	      this.model = model;
	      this.view = view;
	   }
	public void start(BufferedReader in, PrintWriter out) throws IOException {
		  HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir());
		  map.put("generate_3d_maze", new Command_generate_3d_maze());
		  map.put("display", new Command_display());
		  map.put("display_cross_section", new Command_display_cross_section());
          map.put("save_maze", new Command_save_maze());
		  map.put("load_maze", new Command_load_maze());
		  map.put("solve", new Command_solve());
		  map.put("display_solution", new Command_display_solution());
		  view.start(in, out, map);
	}
	public void sendReady(String name) {
		view.printMessage(name);	
	}
	public void saveToHashMap(String name,Maze3d maze){
		view.saveToHashMap(name,maze);
	}
	@Override
	public void update(Observable o, Object arg) {
		view.printMessage((String)arg);
		
	}
}