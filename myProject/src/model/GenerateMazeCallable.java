package model;

import java.util.concurrent.Callable;

import algorithms.mazeGenerators.GetLastCell;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class GenerateMazeCallable implements Callable<Maze3d>{
	
	String name;
	private int z;
	private int x;
	private int y;
	String algorithm;

	public GenerateMazeCallable(String name, int z, int x, int y, String algorithm) {
		this.name=name;
		this.z=z;
		this.x=x;
		this.y=y;
		this.algorithm=algorithm;
	}

	@Override
	public Maze3d call() {
		switch(algorithm){
		case "simple":
			Maze3d simpleMaze=new SimpleMaze3dGenerator().generate(z,x,y);
			return simpleMaze;
		case "growing_tree_last":
			Maze3d GTLCmaze=new GetLastCell().generate(z,x,y);
			return GTLCmaze;
		case"growing_tree_random":
			Maze3d GTRmaze=new GetRandomCell().generate(z,x,y);
			return GTRmaze;
}
		return null;
	}

}
