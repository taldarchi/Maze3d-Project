package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Command_dir;
import controller.Command_display;
import controller.Command_display_cross_section;
import controller.Command_display_solution;
import controller.Command_exit;
import controller.Command_generate_3d_maze;
import controller.Command_load_maze;
import controller.Command_save_maze;
import controller.Command_solve;
import controller.MyController;
import model.MyModel;
import view.CLI;
import view.MyView;

public class Run {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //output stream
		PrintWriter out = new PrintWriter(System.out);
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
		CLI cli=new CLI(in, out, map);
		MyModel model=new MyModel();
		MyView view=new MyView(cli);
		MyController controller=new MyController(model, view)
		m.start();

		

	}

}
