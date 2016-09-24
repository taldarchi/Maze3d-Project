package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * The Class Demo.
 */
public class Demo {
	
	/**
	 * Run.
	 */
	public static void Run(){
		Maze3d maze=new SimpleMaze3dGenerator().generate(6, 15, 15);
		//Maze3d maze=new GetLastCell().generate(6, 5, 5);
		//Maze3d maze=new GetRandomCell().generate(10,10,10);
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
	 * @throws IOException 
	 */
	public static void ByteArrayTest() throws IOException{
		Maze3d maze=new GetRandomCell().generate(10, 10, 10); //... generate it
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(
		new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		InputStream in=new MyDecompressorInputStream(
		new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		System.out.println(loaded.equals(maze));
	}
	
	public static void main(String[] args) throws IOException{
		Run();
		ByteArrayTest();
		
	}

}
