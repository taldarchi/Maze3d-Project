package controller;

import java.util.HashMap;

import model.MyModel;
import view.MyView;
import view.View;

public class MyController implements Controller{
	private View view;
	public MyController(){
		new MyView();
		new MyModel();
	}
	public MyController(MyView view, MyModel model){
	      this.view = view;
	      HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir(view));
		  map.put("generate_3d_maze", new Command_generate_3d_maze(model));
		  map.put("display", new Command_display(view, model));
		  map.put("display_cross_section", new Command_display_cross_section(view, model));
          map.put("save_maze", new Command_save_maze(model));
		  map.put("load_maze", new Command_load_maze(model));
		  map.put("solve", new Command_solve(model));
		  map.put("display_solution", new Command_display_solution(view, model));
		  map.put("exit",new Command_exit(view));
		  view.setMap(map);
	}
	
	@Override
	public void Print(String string) 
	{
		view.printMessage(string);
	}
}