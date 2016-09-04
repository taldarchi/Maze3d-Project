package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Command_dir;
import view.CLI;
import view.MyView;

public class Run {

	public static void main(String[] args) throws IOException {
//		MyView m=new MyView();
//		m.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = null;
		HashMap<String,Command> map=new HashMap<String,Command>();
		map.put("dir", new Command_dir());
		CLI cli=new CLI(in, out, map);
		cli.start();
		

	}

}
