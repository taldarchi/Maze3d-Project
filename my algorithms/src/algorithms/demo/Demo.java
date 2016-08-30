package algorithms.demo;

import java.util.List;

import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.State;

/**
 * The Class Demo.
 */
public class Demo {
	
	/**
	 * Run.
	 */
	public static void Run(){
		//Maze3d maze=new SimpleMaze3dGenerator().generate(6, 15, 15);
		//Maze3d maze=new GetLastCell().generate(6, 5, 5);
		Maze3d maze=new GetRandomCell().generate(6, 15, 15);
		System.out.println(maze);
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		List<State<Position>> solution;
		System.out.println("--BFS Test:--");
		searcher=new BFS<Position>();
		solution=searcher.search(m).getSolution();
		System.out.println("Solution Path:"+solution);
		System.out.println("number of nodes evaluated: "+searcher.getNumberOfNodesEvaluated());
		System.out.println("--DFS Test:--");
		searcher=new DFS<Position>();
		solution=searcher.search(m).getSolution();
		System.out.println("Solution Path:"+solution);
		System.out.println("number of nodes evaluated: "+searcher.getNumberOfNodesEvaluated());
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		Run();
	}

}
