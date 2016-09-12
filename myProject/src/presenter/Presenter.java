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
	
	private Observable view;
	private Model model;
	
	public Presenter(MyView view, MyModel model){
	      this.view=view;
	      this.model=model;
	      HashMap<String,Command> map=new HashMap<String,Command>();
		  map.put("dir", new Command_dir(view));
		  map.put("generate_3d_maze", new Command_generate_3d_maze(view, model));
		  map.put("display", new Command_display(view, model));
		  map.put("display_cross_section", new Command_display_cross_section(view, model));
		  map.put("save_maze", new Command_save_maze(view, model));
		  map.put("load_maze", new Command_load_maze(view, model));
		  map.put("solve", new Command_solve(view, model));
		  map.put("display_solution", new Command_display_solution(view, model));
		  map.put("save_solution_map", new Command_save_solution_map(view, model));
		  map.put("load_solution_map", new Command_load_solution_map(view, model));
		  map.put("exit",new Command_exit(view));
		  view.setMap(map);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o==view){
			try {
			String line=new String((String)arg);
			Scanner s = new Scanner(line);
			String string = new String();
			String command=s.next();
			switch (command){
			case "dir":
				Command dir=((View) view).getMap().get("dir");
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
						Command generate=((View) view).getMap().get("generate_3d_maze");
						while(s.hasNext())
							string+=s.next()+ " ";
						generate.doCommand(string);
						break;
					case "display":
						Command display=((View) view).getMap().get("display");
						while(s.hasNext())
							string+=s.next();
						display.doCommand(string);
						s.close();
						break;
					case "display_cross_section":
						Command display_cross_section=((View) view).getMap().get("display_cross_section");
						while(s.hasNext())
							string+=s.next()+ " ";
						display_cross_section.doCommand(string);
						s.close();
						break;
					case "save_maze":
						Command save_maze=((View) view).getMap().get("save_maze");
						while(s.hasNext())
							string+=s.next()+ " ";
						save_maze.doCommand(string);
						s.close();
						break;
					case "load_maze":
						Command load_maze=((View) view).getMap().get("load_maze");
						while(s.hasNext())
							string+=s.next()+ " ";
						load_maze.doCommand(string);
						s.close();
						break;
					case "solve":
						Command solve=((View) view).getMap().get("solve");
						while(s.hasNext())
							string+=s.next()+ " ";
						solve.doCommand(string);
						s.close();
						break;
					case "display_solution":
						Command display_solution=((View) view).getMap().get("display_solution");
						while(s.hasNext())
							string+=s.next();
						display_solution.doCommand(string);
						s.close();
						break;
					case "save_solution_map":
						Command save_solution_map=((View) view).getMap().get("save_solution_map");
						while(s.hasNext())
							string+=s.next();
						save_solution_map.doCommand(string);
						s.close();
						break;
					case "load_solution_map":
						Command load_solution_map=((View) view).getMap().get("load_solution_map");
						while(s.hasNext())
							string+=s.next();
						load_solution_map.doCommand(string);
						s.close();
						break;
					case "exit":
						Command exit=((View) view).getMap().get("exit");
						exit.doCommand(null);
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(o==model){
			String s=(String)arg;
			((View) view).printMessage(s);
		}
			
		
	}
}
