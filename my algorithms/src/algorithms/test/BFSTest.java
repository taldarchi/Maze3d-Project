package algorithms.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.State;

// TODO: Auto-generated Javadoc
/**
 * The Class BFSTest.
 */
public class BFSTest {
	Maze3d maze;
	Position startPosition;
	Position goalPosition;
	MazeAdapter m;
	CommonSearcher<Position> searcher;
	List<State<Position>> solution;
	
	public BFSTest(){
		maze=new GetRandomCell().generate(10,10,10);
		goalPosition=maze.getGoalPosition();
		m=new MazeAdapter(maze);	
        searcher=new BFS<Position>();
    	solution=searcher.search(m).getSolution();
	}
	
    @Test
    public void checkOnGrowingTreeRandomCell() {
        assertNotNull(solution);
    }

    @Test
	public void validNumberOfEvalutedNodes() {
		assertEquals(true, searcher.getNumberOfNodesEvaluated() > 0);
	}    
    
    @Test
	public void CheckGoalStateOfTheMaze() {
		assertEquals(goalPosition,solution.get(solution.size()-1).getState());
	}
    
	@Test (expected = Exception.class)
	public void checkIfSearcherIsNullIfMazeIsNull() {
		Maze3d mazeNull=null;
		m=new MazeAdapter(mazeNull);	
        searcher=new BFS<Position>();
		searcher.search(m);
	}

}
