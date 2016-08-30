package algorithms.mazeGenerators;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMaze3dGenerator.
 */
public class SimpleMaze3dGenerator extends Maze3dGeneratorAbstract {
	
	/** The Rnd. */
	Random Rnd=new Random();
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGeneratorAbstract#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int z, int x, int y) {
		
		z=z*2+1;
		x=x*2+1;
		y=y*2+1;
		int [][][] temp=new int[z][x][y];
		
		for(int i=0;i<z;i++)
			for(int j=0;j<x;j++)
				for(int k=0;k<y;k++)
				{
					if(i==0 || i==z-1 || j==0 || j==x-1 || k==0|| k==y-1) //creating the borders by the requirements
						temp[i][j][k]=1;
					else
						temp[i][j][k]=Rnd.nextInt(2);
				}
		

		//start position random
		int s1,s2,s3;
		s1=0;
		s2=2+(Rnd.nextInt(((x-2)/2)-1)*2);
		s3=2+(Rnd.nextInt(((y-2)/2)-1)*2);
		temp[s1][s2][s3]=0;
		Position start=new Position(s1,s2,s3);
		
		//goal position random
		int g1,g2,g3;
		g1=z-1;
		g2=2+(Rnd.nextInt(((x-2)/2)-1)*2);
		g3=2+(Rnd.nextInt(((y-2)/2)-1)*2);
		temp[g1][g2][g3]=0;
		Position goal= new Position(g1,g2,g3);
		
		//random path to assure maze solution
		Position tempPos=start;
		int path;
		while(!(tempPos.equals(goal))){
			if(tempPos.getZ()==0)
				path=0;
			else
				path=Rnd.nextInt(3); //0=z ,1=x 2=y  //random place to go
			switch (path){
			//checking where should go next depending on the distance from each point
			case 0:
			if(tempPos.getZ()+2<z){ 
				if(tempPos.getZ()==goal.getZ()-2 && (temp[s1+2][s2][s3]!=0)) // to avoid moving on the last floor or first floor(not an option)
					continue;
				if(temp[s1+2][s2][s3]==0){
					temp[s1+1][s2][s3]=0;
					s1=s1+2;
					tempPos=new Position(s1,s2,s3);
				}
				else{
					temp[s1+2][s2][s3]=0;
					temp[s1+1][s2][s3]=0;
					s1=s1+2;
					tempPos=new Position(s1,s2,s3);
				}
			}
			break;
			
			case 1:
			if(tempPos.getX()>goal.getX()&& tempPos.getX()-2>=0)
				if(temp[s1][s2-2][s3]==0){
					temp[s1][s2-1][s3]=0;
					s2=s2-2;
					tempPos=new Position(s1,s2,s3);
				}
				else{
					temp[s1][s2-2][s3]=0;
					temp[s1][s2-1][s3]=0;
					s2=s2-2;
					tempPos=new Position(s1,s2,s3);
				}

			else if(tempPos.getX()<goal.getX() && tempPos.getX()+2<x)
				if(temp[s1][s2+2][s3]==0){
					temp[s1][s2+1][s3]=0;
					s2=s2+2;
					tempPos=new Position(s1,s2,s3);
				}
				else{
					temp[s1][s2+2][s3]=0;
					temp[s1][s2+1][s3]=0;
					s2=s2+2;
					tempPos=new Position(s1,s2,s3);
				}
					
			break;
			
			case 2:
			if(tempPos.getY()>goal.getY()&& tempPos.getY()-2>=1)
				if(temp[s1][s2][s3-2]==0){
					temp[s1][s2][s3-1]=0;
					s3=s3-2;
					tempPos=new Position(s1,s2,s3);
				}
				else{
					temp[s1][s2][s3-2]=0;
					temp[s1][s2][s3-1]=0;
					s3=s3-2;
					tempPos=new Position(s1,s2,s3);
				}
			else if(tempPos.getY()<goal.getY() && tempPos.getY()+2<y-1)
				if(temp[s1][s2][s3+2]==0){
					temp[s1][s2][s3+1]=0;
					s3=s3+2;
					tempPos=new Position(s1,s2,s3);
				}
				else{
					temp[s1][s2][s3+2]=0;
					temp[s1][s2][s3+1]=0;
					s3=s3+2;
					tempPos=new Position(s1,s2,s3);
				}
			break;
			
			}
		}
		Maze3d m = new Maze3d(temp,start,goal);
		return m;
	}
}
