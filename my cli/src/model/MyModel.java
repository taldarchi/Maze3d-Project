package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;

public class MyModel extends Observable implements Model {
	
	private Maze3d maze;
	private String mazeName;
	
	public MyModel(){
		
	}
	
	public void start(){
		
	}

	@Override
	public void generate3dMaze(String name,int z,int x, int y) {
//		new Thread(new Runnable(){
//		@Override
//		public void run() {
			maze=new GetRandomCell().generate(z,x,y);
			setChanged();
			notifyObservers(name);
			mazeName=name;
//	}
//  }).start();
		
	}

	@Override
	public void saveMaze(Maze3d maze, String fileName) throws IOException {
		OutputStream out=new MyCompressorOutputStream(
		new FileOutputStream(fileName));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		
	}

	@Override
	public Maze3d loadMaze() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void solveMaze(String s) {
		// TODO Auto-generated method stub
		
	}


	public Maze3d getMaze() {
		return maze;
	}

	public String getName() {
		return mazeName;
	}

	public void setName(String name) {
		this.mazeName = name;
	}



}
