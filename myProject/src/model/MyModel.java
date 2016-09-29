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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import utils.PropertiesFile;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model{
	
	/** The executor. */
	private ExecutorService executor = Executors.newFixedThreadPool(PropertiesFile.getProperties().getThreadsNum());
	
	/** The mazes. */
	private HashMap<String,PlayedMaze> mazes=new HashMap<String,PlayedMaze>();
	
	/** The solutions. */
	private HashMap<Maze3d, Solution<Position>> solutions=new HashMap<Maze3d,Solution<Position>>();
	
	/** The wanted position. */
	private Position wantedPosition;
	
	/** The current maze. */
	private Maze3d currentMaze;
	
	/** The current position. */
	private Position currentPosition;
	
	/** The simple algorithm. */
	private boolean simpleAlgorithm;
	

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
				if(algorithm==null){
					currentMaze=generateFromProperties().generate(z, x, y);
				}
				else{
					switch(algorithm){
						case "simple":
							currentMaze= new SimpleMaze3dGenerator().generate(z, x, y);
							simpleAlgorithm=true;
							return currentMaze;
							
						case "growing_tree_last":
							currentMaze= new GetLastCell().generate(z, x, y);
							return currentMaze;
							
						case"growing_tree_random":
							currentMaze= new GetRandomCell().generate(z, x, y);
							return currentMaze;
					}
				}
				return currentMaze;
			}
		});
		try {
			Maze3d maze = m.get();
			PlayedMaze p_maze=new PlayedMaze(maze);
			mazes.put(name,p_maze);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		if(PropertiesFile.getProperties().getUserInterface().equals("cli")){
			String mazeName=name;
			String message = String.format("Maze %s is ready", mazeName);
			setChanged();
			notifyObservers(message);
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 * 
	 * save maze to a file with filename
	 */
	@Override
	public void saveMaze(String name, String fileName) throws IOException {
		Maze3d maze=mazes.get(name).getMaze();
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("resources/mazes/"+fileName));
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
		InputStream in=new MyDecompressorInputStream(new FileInputStream("resources/mazes/"+fileName));
		byte b[]=new byte[1000000];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		PlayedMaze p_maze=new PlayedMaze(loaded);
		mazes.put(nameToSave, p_maze);
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
		Maze3d maze=mazes.get(mazeName).getMaze();
		maze.setStartPosition(mazes.get(mazeName).getCurrentPosition());
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
		if(PropertiesFile.getProperties().getUserInterface().equals("cli")){
			String message = String.format("Solution for %s is ready", mazeName);
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
	public PlayedMaze getMazeByName(String name){
		if(!mazes.containsKey(name)){
			String message="Maze not found, try again";
			setChanged();
			notifyObservers(message);
		}
		else{
			PlayedMaze maze=mazes.get(name);
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
	
	/* (non-Javadoc)
	 * @see model.Model#solutionExists(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public boolean solutionExists(Maze3d maze){
		return solutions.containsKey(maze);
	}

	/* (non-Javadoc)
	 * @see model.Model#saveSolutionMap()
	 */
	@Override
	public void saveSolutionMap(){
		try {
	        File file = new File("resources/solutionmaps/map.zip");
	        ObjectOutputStream output;
			output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
	        output.writeObject(solutions);
	        output.flush();
	        output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	/* (non-Javadoc)
	 * @see model.Model#loadSolutionMap()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void loadSolutionMap() {
		try {
	        File file = new File("map.zip");
	        ObjectInputStream input;
			input = new ObjectInputStream(new GZIPInputStream(new FileInputStream("resources/solutionmaps/"+file)));
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
	
	/* (non-Javadoc)
	 * @see model.Model#getMazes()
	 */
	@Override
	public HashMap<String, PlayedMaze> getMazes() {
		return mazes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((solutions == null) ? 0 : solutions.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	

	/**
	 * Generate from properties.
	 *
	 * @return the maze 3 d generator
	 */
	public Maze3dGenerator generateFromProperties(){
		switch(PropertiesFile.getProperties().getGeneratorAlgorithm()){
		case "simple":
			simpleAlgorithm=true;
			return new SimpleMaze3dGenerator();
		case "growing_tree_last":
			return new GetLastCell();
		case"growing_tree_random":
			return new GetRandomCell();
	}
		return null;
}
	
	/**
	 * Solve from properties.
	 *
	 * @return the common searcher
	 */
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
	
	/**
	 * Sets the maze to play.
	 *
	 * @param name the new maze to play
	 */
	public void setMazeToPlay(String name){
		this.currentMaze=this.getMazeByName(name).getMaze();
		this.currentPosition=this.getMazeByName(name).getCurrentPosition();
	}
	
	/* (non-Javadoc)
	 * @see model.Model#hint(java.lang.String)
	 */
	public void hint(String name){
		Maze3d maze=this.mazes.get(name).getMaze();
		List<State<Position>> sol=this.solutions.get(maze).getSolution();
		State<Position> s=sol.get(0);
		Position pos=s.getState();
		setChanged();
		notifyObservers(pos);
	}
	
	/* (non-Javadoc)
	 * @see model.Model#up(java.lang.String)
	 */
	public void up(String string){
		setMazeToPlay(string);
		if(simpleAlgorithm)
			this.wantedPosition = new Position(this.currentPosition.getZ()+1, this.currentPosition.getX(), this.currentPosition.getY());
		else
			this.wantedPosition = new Position(this.currentPosition.getZ()+2, this.currentPosition.getX(), this.currentPosition.getY());
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Up"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}
	
	/* (non-Javadoc)
	 * @see model.Model#down(java.lang.String)
	 */
	public void down(String string){
		setMazeToPlay(string);
		if(simpleAlgorithm)
			this.wantedPosition = new Position(this.currentPosition.getZ()-1, this.currentPosition.getX(), this.currentPosition.getY());
		else
			this.wantedPosition = new Position(this.currentPosition.getZ()-2, this.currentPosition.getX(), this.currentPosition.getY());
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Down"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}	
	
	/* (non-Javadoc)
	 * @see model.Model#forward(java.lang.String)
	 */
	public void forward(String string){
		setMazeToPlay(string);
		this.wantedPosition = new Position(this.currentPosition.getZ(), this.currentPosition.getX()+1, this.currentPosition.getY());
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Forward"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}	
	
	/* (non-Javadoc)
	 * @see model.Model#backwards(java.lang.String)
	 */
	public void backwards(String string){
		setMazeToPlay(string);
		this.wantedPosition = new Position(this.currentPosition.getZ(), this.currentPosition.getX()-1, this.currentPosition.getY());
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Backwards"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}	
	
	/* (non-Javadoc)
	 * @see model.Model#right(java.lang.String)
	 */
	public void right(String string){
		setMazeToPlay(string);
		this.wantedPosition = new Position(this.currentPosition.getZ(), this.currentPosition.getX(), this.currentPosition.getY()+1);
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Right"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}
	


	/* (non-Javadoc)
	 * @see model.Model#left(java.lang.String)
	 */
	public void left(String string){
		setMazeToPlay(string);
		this.wantedPosition = new Position(this.currentPosition.getZ(), this.currentPosition.getX(), this.currentPosition.getY()-1);
		if((Arrays.asList(this.currentMaze.getPossibleMoves(currentPosition)).contains("Left"))){
			this.currentPosition.setZ(this.wantedPosition.getZ());
			this.currentPosition.setX(this.wantedPosition.getX());
			this.currentPosition.setY(this.wantedPosition.getY());
			setChanged();
			notifyObservers("character_move");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#getCurrentPosition()
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#readProperties(java.lang.String)
	 */
	public void readProperties(String filename){
		try {
			PropertiesFile.readProperties(filename);
		} catch (FileNotFoundException|ArrayIndexOutOfBoundsException e) {
			setChanged();
			notifyObservers("Properties file not found");
		}
	}
		
}
