package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Presenter implements Observer{
	
	private View view;
	private Model model;
	
	public Presenter(MyView view, MyModel model){
	      this.view=view;
	      this.model=model;
	      model.loadSolutionMap();
	      HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir(view));
		  map.put("generate_3d_maze", new Command_generate_3d_maze(view, model));
		  map.put("display", new Command_display(view, model));
		  map.put("display_cross_section", new Command_display_cross_section(view, model));
		  map.put("save_maze", new Command_save_maze(view, model));
		  map.put("load_maze", new Command_load_maze(view, model));
		  map.put("solve", new Command_solve(view, model));
		  map.put("display_solution", new Command_display_solution(view, model));
		  map.put("exit",new Command_exit(view));
		  view.setMap(map);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o==view){
			if(arg.equals("save_solution_map")){
				model.saveSolutionMap();
				return;
			}
			Command command;
			boolean bool=true;
			String line=new String((String)arg);
			Scanner s = new Scanner(line);
			String string = new String();
			command=((View) view).getMap().get(s.next());
			while(s.hasNext()){
				if(bool){
					string+=s.next();
					bool=false;
				}
				else
					string+=" "+s.next();
			}
			try {
				command.doCommand(string);
			} catch (IOException e) {
				e.printStackTrace();
			}
			s.close();
		}
		if(o==model){
			String str=(String)arg;
			((View) view).printMessage(str);
		}
	}
	
}
