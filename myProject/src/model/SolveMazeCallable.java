package model;

import java.util.concurrent.Callable;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.Solution;

public class SolveMazeCallable implements Callable<Solution<Position>>{
	
	private Maze3d maze;
	private String algorithm;

	public SolveMazeCallable(Maze3d maze, String algorithm) {
		this.maze=maze;
		this.algorithm=algorithm;
	}
	@Override
	public Solution<Position> call() throws Exception {
		MazeAdapter m=new MazeAdapter(maze);
		CommonSearcher<Position> searcher;
		Solution<Position> solution;
		switch(algorithm){
		case "bfs":
		case "BFS":
			searcher=new BFS<Position>();
			solution=searcher.search(m);
			return solution;
		case "dfs":
		case "DFS":
			searcher=new DFS<Position>();
			solution=searcher.search(m);
			return solution;
		}
		return null;
	}
}
