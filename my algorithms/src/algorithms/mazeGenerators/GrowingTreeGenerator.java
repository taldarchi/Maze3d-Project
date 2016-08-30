package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GrowingTreeGenerator.
 */
public abstract class GrowingTreeGenerator extends Maze3dGeneratorAbstract{
	
	/** The rnd. */
	Random rnd=new Random();
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGeneratorAbstract#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int z, int x, int y){
		ArrayList<Position> C =new ArrayList<Position>();
		int [][][] temp=new int[(z*2)+1][(x*2)+1][(y*2)+1]; //temp array to generate the maze on times 2 plus 1 because of the walls
		for(int i=0; i<temp.length;i++) //fill it with ones
			for(int j=0; j<temp[0].length;j++)
				for(int k=0;k<temp[0][0].length;k++)
					temp[i][j][k]=1;
		
		int p1,p2,p3;
		Position c,n;
		String ns;
		boolean bool=false;
		
		//random goal position on the last floor
		Position goal;
		int goalIntX = 0,goalIntY = 0;
		goalIntX=2+(rnd.nextInt(x-1)*2); //random even number
		goalIntY=2+(rnd.nextInt(y-1)*2);
		goal= new Position(z*2,goalIntX,goalIntY);
		temp[z*2][goalIntX][goalIntY]=0;
		
		//random start position on the first floor
		p1=0;
		p2=2+(rnd.nextInt(x-1)*2);
		p3=2+(rnd.nextInt(y-1)*2);
		Position p=new Position(p1,p2,p3);
		C.add(p);
		while(!C.isEmpty()){
			c=getCell(C); // depending on users choice- random or lastcell
			temp[c.getZ()][c.getX()][c.getY()]=0;
			ArrayList<String> neighbours=UnvisitedNeighbours(temp,c); // getting all unvisited neighbours from function
			if (!neighbours.isEmpty()){
				Collections.shuffle(neighbours);
				if(c.getZ()==0 && bool==false){
					bool=true;
					ns="Up";
				}
				else
					ns=neighbours.get(0);
				switch (ns){
				case "Up":
					temp[c.getZ()+2][c.getX()][c.getY()]=0;
					temp[c.getZ()+1][c.getX()][c.getY()]=0;
					n=new Position(c.getZ()+2,c.getX(),c.getY());
					if(c.getZ()+2==temp.length-1){ // to avoid moving on the last floor
						C.remove(c);
						break;
					}
					C.add(n);
					break;
				case "Forward":
					temp[c.getZ()][c.getX()+2][c.getY()]=0;
					temp[c.getZ()][c.getX()+1][c.getY()]=0;
					n=new Position(c.getZ(),c.getX()+2,c.getY());
					C.add(n);
					break;
				case "Right":
					temp[c.getZ()][c.getX()][c.getY()+2]=0;
					temp[c.getZ()][c.getX()][c.getY()+1]=0;
					n=new Position(c.getZ(),c.getX(),c.getY()+2);
					C.add(n);
					break;
				case "Down":
					temp[c.getZ()-2][c.getX()][c.getY()]=0;
					temp[c.getZ()-1][c.getX()][c.getY()]=0;
					n=new Position(c.getZ()-2,c.getX(),c.getY());
					C.add(n);
					break;
				case "Backwards":
					temp[c.getZ()][c.getX()-2][c.getY()]=0;
					temp[c.getZ()][c.getX()-1][c.getY()]=0;
					n=new Position(c.getZ(),c.getX()-2,c.getY());
					C.add(n);
					break;
				case "Left":
					temp[c.getZ()][c.getX()][c.getY()-2]=0;
					temp[c.getZ()][c.getX()][c.getY()-1]=0;
					n=new Position(c.getZ(),c.getX(),c.getY()-2);
					C.add(n);
					break;
				}
			}
			else
				C.remove(c);
			
		}
			
		Maze3d m = new Maze3d(); //finally setting the maze
		m.setMaze3d(temp);
		
		m.setStartPosition(p);
		m.setGoalPosition(goal);
		return m;
	}



/**
 * Unvisited neighbours.
 *
 * the function that checks for unvisited nodes
 * @param maze the maze
 * @param p the p
 * @return the array list
 */
public ArrayList<String> UnvisitedNeighbours(int [][][] maze,Position p){
	ArrayList<String> Neighbours=new ArrayList<String>();
		if((p.getZ()<=maze.length)&&(p.getX()<=maze[0].length)&&(p.getY()<=maze[0][0].length)) //range check
		{
			if (p.getZ()+2<maze.length-1){ //check for out of bounds
				if(maze[p.getZ()+2][p.getX()][p.getY()]==1) //check if visited
					Neighbours.add("Up");
			}
			else if (p.getZ()==maze.length-3)
				if(maze[p.getZ()+2][p.getX()][p.getY()]==0)
					Neighbours.add("Up");
				
			if (p.getX()+2<maze[0].length-1 && p.getZ()!=0 && p.getZ()!=maze.length-1)
				if(maze[p.getZ()][p.getX()+2][p.getY()]==1)
					Neighbours.add("Forward");
				
			if (p.getY()+2<maze[0][0].length-1 && p.getZ()!=0 && p.getZ()!=maze.length-1)
				if(maze[p.getZ()][p.getX()][p.getY()+2]==1)
					Neighbours.add("Right");
			
			if (p.getZ()-2>=1)
				if(maze[p.getZ()-2][p.getX()][p.getY()]==1)
					Neighbours.add("Down");
				
			if (p.getX()-2>=1 && p.getZ()!=0 && p.getZ()!=maze.length-1)
				if(maze[p.getZ()][p.getX()-2][p.getY()]==1)
					Neighbours.add("Backwards");
				
			if (p.getY()-2>=1 && p.getZ()!=0 && p.getZ()!=maze.length-1)
				if(maze[p.getZ()][p.getX()][p.getY()-2]==1)
					Neighbours.add("Left");
		}
	return Neighbours;
}

/**
 * Gets the cell.
 *
 * @param C the c
 * @return the cell
 */
public abstract Position getCell(ArrayList<Position> C);
}