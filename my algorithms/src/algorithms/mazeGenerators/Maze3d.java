package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze3d.
 */
public class Maze3d implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 122377620547766818L;

	/** The maze 3 d. */
	private  int [][][] maze3d;
	
	/** The start position. */
	private Position startPosition;
	
	/** The goal position. */
	private Position goalPosition;
	
	/**
	 * Instantiates a new maze 3 d.
	 */
	public Maze3d(){
		this.maze3d=null;
		this.startPosition=null;
		this.goalPosition=null;
		
	}
	
	/**
	 * Instantiates a new maze 3 d.
	 *
	 * @param maze the maze
	 * @param start the start
	 * @param goal the goal
	 */
	public Maze3d(int[][][] maze,Position start,Position goal){
		this.maze3d=maze;
		this.startPosition=start;
		this.goalPosition=goal;
	}
	
	public Maze3d(byte[] byteMaze){
		this.startPosition=new Position();
		this.goalPosition=new Position();
		
		this.startPosition.setZ(byteMaze[0]);
		this.startPosition.setX(byteMaze[1]);
		this.startPosition.setY(byteMaze[2]);
		
		this.goalPosition.setZ(byteMaze[3]);
		this.goalPosition.setX(byteMaze[4]);
		this.goalPosition.setY(byteMaze[5]);
		
		this.maze3d=new int[byteMaze[6]][byteMaze[7]][byteMaze[8]];
		int x=9;
		for(int i=0;i<maze3d.length;i++)
			for(int j=0;j<maze3d[0].length;j++)
				for(int k=0;k<maze3d[0][0].length;k++){
					maze3d[i][j][k]=byteMaze[x];
					x++;
				}
	}
	
	
	/**
	 * Gets the maze 3 d.
	 *
	 * @return the maze 3 d
	 */
	public int[][][] getMaze3d() {
		return maze3d;
	}

	/**
	 * Sets the maze 3 d.
	 *
	 * @param maze3d the new maze 3 d
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}

	/**
	 * Gets the start position.
	 *
	 * @return the start position
	 */
	public Position getStartPosition() {
		return startPosition;
	}

	/**
	 * Sets the start position.
	 *
	 * @param startPosition the new start position
	 */
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	/**
	 * Gets the goal position.
	 *
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}

	/**
	 * Sets the goal position.
	 *
	 * @param goalPosition the new goal position
	 */
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	/**
	 * Gets the possible moves.
	 *
	 * @param p the p
	 * @return the possible moves
	 */
	public String[] getPossibleMoves(Position p){
		ArrayList<String> possibleMoves=new ArrayList<String>();
			if((p.getZ()<=maze3d.length)&&(p.getX()<=maze3d[0].length)&&(p.getY()<=maze3d[0][0].length))
			{
				if ((p.getZ()+2<maze3d.length)&&(maze3d[p.getZ()+2][p.getX()][p.getY()]==0))
					possibleMoves.add("Up");
				if ((p.getX()+2<maze3d[0].length)&&(maze3d[p.getZ()][p.getX()+2][p.getY()]==0))
					possibleMoves.add("Forward");
				if ((p.getY()+2<maze3d[0][0].length)&&(maze3d[p.getZ()][p.getX()][p.getY()+2]==0))
					possibleMoves.add("Right");
				if ((p.getZ()-2>=0)&&(maze3d[p.getZ()-2][p.getX()][p.getY()]==0))
					possibleMoves.add("Down");
				if ((p.getX()-2>=0)&&(maze3d[p.getZ()][p.getX()-2][p.getY()]==0))
					possibleMoves.add("Backwards");
				if ((p.getY()-2>=0)&&(maze3d[p.getZ()][p.getX()][p.getY()-2]==0))
					possibleMoves.add("Left");
			}
		String [] moves=new String[possibleMoves.size()];
		return possibleMoves.toArray(moves);
	}
	
	/**
	 * Gets the cross section by Z.
	 *
	 * @param n the n
	 * @return the cross section by Z
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByZ(int n) throws IndexOutOfBoundsException{
		if((n<0)||(n>=maze3d.length))
			throw new IndexOutOfBoundsException("Out of bounds");
		int[][]cross=new int[maze3d[0].length][maze3d[0][0].length];
		for(int i=0;i<maze3d[0].length;i++)
			for(int j=0;j<maze3d[0][0].length;j++)
				cross[i][j]=maze3d[n][i][j];
		return cross;	
	}
	
	/**
	 * Gets the cross section by X.
	 *
	 * @param n the n
	 * @return the cross section by X
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByX(int n) throws IndexOutOfBoundsException{
		if((n<0)||(n>=maze3d[0].length))
			throw new IndexOutOfBoundsException("Out of bounds");
		int[][]cross=new int[maze3d.length][maze3d[0][0].length];
		for(int i=0;i<maze3d.length;i++)
			for(int j=0;j<maze3d[0][0].length;j++)
				cross[i][j]=maze3d[i][n][j];
		return cross;
	}	
	
	
	/**
	 * Gets the cross section by Y.
	 *
	 * @param n the n
	 * @return the cross section by Y
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByY(int n) throws IndexOutOfBoundsException{
		if((n<0)||(n>=maze3d[0][0].length))
			throw new IndexOutOfBoundsException("Out of bounds");
		int[][]cross=new int[maze3d.length][maze3d[0].length];
		for(int i=0;i<maze3d.length;i++)
			for(int j=0;j<maze3d[0].length;j++)
				cross[i][j]=maze3d[i][j][n];
		return cross;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s1 = new String();

		for (int i = 0; i <maze3d.length; i++) {
			for (int j = 0; j < maze3d[0].length ; j++) {
				for (int k = 0; k< maze3d[0][0].length ; k++) {
					s1 += maze3d[i][j][k];
				}
				s1 += "\n";
			}
			s1 += "\n";
		}
		return s1;
	}
	
	
	/**
	 * Prints the maze 3 d.
	 */
	public void printMaze3d(){
		for(int i=0;i<maze3d.length;i++){
			for (int j=0;j<maze3d[0].length;j++){
				for(int k=0;k<maze3d[0][0].length;k++)
				{
					System.out.print(maze3d[i][j][k]+ " ");
				}
				System.out.println();
			}
			System.out.println();

		}
	}
	
	public byte[] toByteArray(){
		byte[] b=new byte[maze3d.length*maze3d[0].length*maze3d[0][0].length+3+3+3];
		int x=9;
		
		b[0]=(byte) startPosition.getZ(); //start position
		b[1]=(byte) startPosition.getX();
		b[2]=(byte) startPosition.getY();
		
		b[3]=(byte) goalPosition.getZ(); //goal position
		b[4]=(byte) goalPosition.getX();
		b[5]=(byte) goalPosition.getY();
				
				
		b[6]=(byte) maze3d.length; //maze size
		b[7]=(byte) maze3d[0].length;
		b[8]=(byte) maze3d[0][0].length;
		
		for(int i=0;i<maze3d.length;i++)
			for(int j=0;j<maze3d[0].length;j++)
				for(int k=0;k<maze3d[0][0].length;k++){
					b[x]=(byte) maze3d[i][j][k];
					x++;
				}
		return b;
		
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(maze3d);
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
		Maze3d other = (Maze3d) obj;
		if (goalPosition == null) {
			if (other.goalPosition != null)
				return false;
		} else if (!goalPosition.equals(other.goalPosition))
			return false;
		if (!Arrays.deepEquals(maze3d, other.maze3d))
			return false;
		if (startPosition == null) {
			if (other.startPosition != null)
				return false;
		} else if (!startPosition.equals(other.startPosition))
			return false;
		return true;
	}
}
