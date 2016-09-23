/**
 * @file MyModel.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description implementation of the model
 * 				
 * @date    08/09/2016
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.GetLastCell;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import utils.PropertiesFile;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model{
	
	/**
	 *
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(PropertiesFile.getProperties().getThreadsNum());
	
	/** The mazes. */
	private HashMap<String,Maze3d> mazes=new HashMap<String,Maze3d>();
	
	/** The solutions. */
	private HashMap<Maze3d, Solution<Position>> solutions=new HashMap<Maze3d,Solution<Position>>();
	
	private PlayedMaze maze;

	/* (non-Javadoc)
	 * @see model.Model#generate3dMaze(java.lang.String, int, int, int, java.lang.String)
	 * 
	 * generation of the 3d maze from the parameters 
	 */
	@Override
	public void generate3dMaze(String name,int z,int x, int y,String algorithm){
		
		Future<Maze3d> m=executor.submit(new Callable<Maze3d>(){
			
			@Override
			public Maze3d call() throws Exception {
				Maze3d maze = new Maze3d();
				if(algorithm==null)
					maze=generateFromProperties().generate(z, x, y);
				else{
					switch(algorithm){
						case "simple":
							maze= new SimpleMaze3dGenerator().generate(z, x, y);
							return maze;
						case "growing_tree_last":
							maze= new GetLastCell().generate(z, x, y);
							return maze;
						case"growing_tree_random":
							maze= new GetRandomCell().generate(z, x, y);
							return maze;
					}
				}
				return maze;
			}
		});
		try {
			Maze3d maze = m.get();
			mazes.put(name,maze);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		String mazeName=name;
		String message = String.format("Maze %s is ready", mazeName);
		setChanged();
		notifyObservers(message);
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 * 
	 * save maze to a file with filename
	 */
	@Override
	public void saveMaze(String name, String fileName) throws IOException {
		Maze3d maze=mazes.get(name);
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream(fileName));
		try{
		out.write(maze.toByteArray());
		}catch(NullPointerException e){
			String message = ("Maze not found, try again");
			setChanged();
			notifyObservers(message);
		}
		out.flush();
		out.close();
		
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 * 
	 * load maze from a file with filename and save to nameToSave
	 */
	@Override
	public Maze3d loadMaze(String fileName, String nameToSave) throws IOException
	{
		try{
		InputStream in=new MyDecompressorInputStream(new FileInputStream(fileName));
		byte b[]=new byte[1000000];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		mazes.put(nameToSave, loaded);
		}catch(FileNotFoundException e){
			String message="File not found, try again";
			setChanged();
			notifyObservers(message);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String, java.lang.String)
	 * 
	 * solve maze with desired algorithm
	 */
	@Override
	public void solveMaze(String mazeName,String algorithm) {
		Maze3d maze=mazes.get(mazeName);
		Future<Solution<Position>> s=executor.submit(new Callable<Solution<Position>>(){

			@Override
			public Solution<Position> call() throws Exception {
				if(algorithm==null){
					MazeAdapter m=new MazeAdapter(maze);
					CommonSearcher<Position> searcher;
					Solution<Position> solution;
					searcher=solveFromProperties();
					solution=searcher.search(m);
					return solution;
				}
				else{
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
				}
				return null;
			}
		});
		try {
			Solution<Position> solution = s.get();
			solutions.put(maze, solution);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
 	    String message = String.format("Solution for %s is ready", mazeName);
		setChanged();
		notifyObservers(message);
	}
	
	/* (non-Javadoc)
	 * @see model.Model#mazeNameCheck(java.lang.String)
	 * 
	 * check if the maze name already exists
	 */
	@Override
	public boolean mazeNameCheck(String name){
		return mazes.containsKey(name);
	}

	
	/* (non-Javadoc)
	 * @see model.Model#getMazeByName(java.lang.String)
	 * 
	 * returns a maze by desired name
	 */
	@Override
	public Maze3d getMazeByName(String name){
		if(!mazes.containsKey(name)){
			String message="Maze not found, try again";
			setChanged();
			notifyObservers(message);
		}
		else{
			Maze3d maze=mazes.get(name);
			return maze;
		}
		return null;
	}
		

	/* (non-Javadoc)
	 * @see model.Model#getSolutions()
	 * 
	 * returns the solutions
	 */
	@Override
	public HashMap<Maze3d, Solution<Position>> getSolutions() {
		return solutions;
	}
	@Override
	public boolean solutionExists(Maze3d maze){
		return solutions.containsKey(maze);
	}

	@Override
	public void saveSolutionMap(){
		try {
	        File file = new File("map.zip");
	        ObjectOutputStream output;
			output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
	        output.writeObject(solutions);
	        output.flush();
	        output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadSolutionMap() {
		try {
	        File file = new File("map.zip");
	        ObjectInputStream input;
			input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
	        Object readObject = input.readObject();
	        input.close();
	        if(!(readObject instanceof HashMap)) throw new IOException("Data is not a hashmap");
	        solutions = (HashMap<Maze3d, Solution<Position>>) readObject;
		} catch (IOException | ClassNotFoundException e) {
			String message="File not found, try again";
			setChanged();
			notifyObservers(message);
		}

		
	}
	@Override
	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((solutions == null) ? 0 : solutions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyModel other = (MyModel) obj;
		if (solutions == null) {
			if (other.solutions != null)
				return false;
		} else if (!solutions.equals(other.solutions))
			return false;
		return true;
	}
	

	public Maze3dGenerator generateFromProperties(){
		switch(PropertiesFile.getProperties().getGeneratorAlgorithm()){
		case "simple":
			return new SimpleMaze3dGenerator();
		case "growing_tree_last":
			return new GetLastCell();
		case"growing_tree_random":
			return new GetRandomCell();
	}
		return null;
}
	public CommonSearcher<Position> solveFromProperties(){
		switch(PropertiesFile.getProperties().getSearchAlgorithm()){
		case "bfs":
		case "BFS":
			return new BFS<Position>();
		case "dfs":
		case "DFS":
			return new DFS<Position>();
		}
		return null;
	}
	
	public void setMazeToPlay(String name){
		this.maze.setMaze(getMazeByName(name));
		this.maze.setSolution(getSolutions().get(getMazeByName(name)));
		this.maze.setCurrentPosition(getMazeByName(name).getStartPosition());
		
	}
	
	public void up(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ()+1,this.maze.getCurrentPosition().getX(),this.maze.getCurrentPosition().getY()));
	}
	
	public void down(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ()-1,this.maze.getCurrentPosition().getX(),this.maze.getCurrentPosition().getY()));
	
	}	
	
	public void forward(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ(),this.maze.getCurrentPosition().getX()+1,this.maze.getCurrentPosition().getY()));
		
	}	
	
	public void backwards(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ(),this.maze.getCurrentPosition().getX()-1,this.maze.getCurrentPosition().getY()));
		
	}	
	
	public void right(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ(),this.maze.getCurrentPosition().getX(),this.maze.getCurrentPosition().getY()+1));
	}
	
	public void left(String string){
		this.maze.setCurrentPosition(new Position(this.maze.getCurrentPosition().getZ(),this.maze.getCurrentPosition().getX(),this.maze.getCurrentPosition().getY()-1));
	}
		
}
