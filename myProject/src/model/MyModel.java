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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model{
	
	/**
	 * 
	 */
	ExecutorService executor = Executors.newCachedThreadPool();
	/** The mazes. */
	private HashMap<String,Maze3d> mazes=new HashMap<String,Maze3d>();
	
	/** The solutions. */
	private HashMap<Maze3d, Solution<Position>> solutions=new HashMap<Maze3d,Solution<Position>>();

	/* (non-Javadoc)
	 * @see model.Model#generate3dMaze(java.lang.String, int, int, int, java.lang.String)
	 * 
	 * generation of the 3d maze from the parameters 
	 */
	@Override
	public void generate3dMaze(String name,int z,int x, int y, String algorithm){
		
		Future<Maze3d> m=executor.submit(new GenerateMazeCallable(name,z,x,y,algorithm));
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
		out.write(maze.toByteArray());
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
	public void solveMaze(String mazeName, String algorithm) {
		Maze3d maze=mazes.get(mazeName);
		if(!algorithm.equals("bfs")&&!algorithm.equals("BFS")&&!algorithm.equals("dfs")&&!algorithm.equals("DFS")){
			String message="Search algorithm does not exist, try again";
			setChanged();
			notifyObservers(message);
		}
		else{
			Future<Solution<Position>> s=executor.submit(new SolveMazeCallable(maze, algorithm));
			Solution<Position> solution;
			try {
				solution = s.get();
				solutions.put(maze, solution);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			String message = String.format("Solution for %s (%s) is ready", mazeName,algorithm);
			setChanged();
			notifyObservers(message);
		}
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
	public void saveSolutionMap(String fileName){
		try {
	        File file = new File(fileName);
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
	public void loadSolutionMap(String fileName) {
		try {
	        File file = new File(fileName);
	        ObjectInputStream input;
			input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
	        Object readObject = input.readObject();
	        input.close();
	        if(!(readObject instanceof HashMap)) throw new IOException("Data is not a hashmap");
	        solutions = (HashMap<Maze3d, Solution<Position>>) readObject;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		
	}
	@Override
	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}

}
