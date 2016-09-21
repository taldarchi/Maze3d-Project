package algorithms.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.GetLastCell;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.State;

public class BFSTest {

    @Test
    public void checkOnGrowingTreeRandomCell() {
		Maze3d maze=new GetRandomCell().generate(10,10,10);
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		List<State<Position>> solution;
		searcher=new BFS<Position>();
		solution=searcher.search(m).getSolution();
        assertNotNull(solution);
    	
    }
    @Test
    public void checkOnGrowingTreeLastCell() {
		Maze3d maze=new GetLastCell().generate(15,15,15);
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		List<State<Position>> solution;
		searcher=new BFS<Position>();
		solution=searcher.search(m).getSolution();
        assertNotNull(solution);
    	
    }
    @Test
    public void checkOnSimpleMaze() {
		Maze3d maze=new SimpleMaze3dGenerator().generate(3,15,20);
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		List<State<Position>> solution;
		searcher=new BFS<Position>();
		solution=searcher.search(m).getSolution();
        assertNotNull(solution);
    	
    }
    @Test
    public void checkIfSameResultEveryTime() {
		Maze3d maze=new GetLastCell().generate(15,15,15);
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		List<State<Position>> solution1;
		searcher=new BFS<Position>();
		solution1=searcher.search(m).getSolution();
		
		List<State<Position>> solution2;
		searcher=new BFS<Position>();
		solution2=searcher.search(m).getSolution();
		
		assertEquals(solution1, solution2);
        
    	
    }

}
