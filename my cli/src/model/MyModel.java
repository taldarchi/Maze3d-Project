package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;


import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.GetLastCell;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.State;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {

	private Controller m_controller;
	private HashMap<String,Maze3d> mazes=new HashMap<String,Maze3d>();
	private HashMap<String,List<State<Position>>> solutions=new HashMap<String,List<State<Position>>>();
	

	@Override
	public void generate3dMaze(String name,int z,int x, int y, String algorithm) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				switch(algorithm){
				case "simple":
					Maze3d simpleMaze=new SimpleMaze3dGenerator().generate(z,x,y);
					mazes.put(name, simpleMaze);
					break;
				case "growing_tree_last":
					Maze3d GTLCmaze=new GetLastCell().generate(z,x,y);
					mazes.put(name, GTLCmaze);
					break;
				case"growing_tree_random":
					Maze3d GTRmaze=new GetRandomCell().generate(z,x,y);
					mazes.put(name, GTRmaze);
					break;
		}
			String mazeName=name;
			String message = String.format("Maze %s is ready", mazeName);
			m_controller.Print(message);
		
	}
  }).start();
		
	}

	@Override
	public void saveMaze(String name, String fileName) throws IOException {
		Maze3d maze=mazes.get(name);
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream(fileName));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		
	}

	@Override
	public Maze3d loadMaze(String fileName, String nameToSave) throws IOException
	{
		try{
		Maze3d maze = new Maze3d();
		InputStream in=new MyDecompressorInputStream(new FileInputStream(fileName));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		mazes.put(nameToSave, loaded);
		}catch(FileNotFoundException e){
			  m_controller.Print("File not found, try again");
		}
		return null;
	}

	@Override
	public void solveMaze(String mazeName, String algorithm) {
		new Thread(new Runnable(){
			@Override
				public void run() {
					Maze3d maze=mazes.get(mazeName);
					MazeAdapter m=new MazeAdapter(maze);
					CommonSearcher<Position> searcher;
					List<State<Position>> solution = null;
					if(!algorithm.equals("bfs")&&!algorithm.equals("BFS")&&!algorithm.equals("dfs")&&!algorithm.equals("DFS"))
						m_controller.Print("Cant find search algorithm, try again");
					else{
						switch(algorithm){
						case "bfs":
						case "BFS":
							searcher=new BFS<Position>();
							solution=searcher.search(m).getSolution();
							solutions.put(mazeName, solution);
							break;
						case "dfs":
						case "DFS":
							searcher=new DFS<Position>();
							solution=searcher.search(m).getSolution();
							solutions.put(mazeName, solution);
							break;
						}
						String message = String.format("Solution for %s (%s) is ready", mazeName,algorithm);
						m_controller.Print(message);
					}
			}
 		}).start();


		
	}
	@Override
	public boolean mazeNameCheck(String name){
		return mazes.containsKey(name);
	}

	@Override
	public void setController(Controller controller) {
		this.m_controller=controller;
		
	}
	@Override
	public Maze3d getMazeByName(String name){
		if(!mazes.containsKey(name))
			  m_controller.Print("Maze not found, try again");
		else{
			Maze3d maze=mazes.get(name);
			return maze;
		}
		return null;
	}
		

	public HashMap<String, List<State<Position>>> getSolutions() {
		return solutions;
	}




}
