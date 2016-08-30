package algorithms.mazeGenerators;

import java.util.Arrays;

import algorithms.mazeGenerators.GetLastCell;
import algorithms.mazeGenerators.GetRandomCell;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {
private static void testMazeGenerator(Maze3dGenerator mg){
// prints the time it takes the algorithm to run

System.out.println(mg.measureAlgorithmTime(10,10,10));
// generate another 3d maze
Maze3d maze=mg.generate(4,4,4);
// get the maze entrance
Position p=maze.getStartPosition();
// print the position
System.out.println(p); // format "{x,y,z}"
// get all the possible moves from a position
String[] moves=maze.getPossibleMoves(p);
// print the moves
for(String move : moves)
	System.out.println(move);
// prints the maze exit position
System.out.println(maze.getGoalPosition());
try{
// get 2d cross sections of the 3d maze
int[][] maze2dx=maze.getCrossSectionByX(2);
System.out.println(Arrays.deepToString(maze2dx));
int[][] maze2dy=maze.getCrossSectionByY(5);
System.out.println(Arrays.deepToString(maze2dy));
int[][] maze2dz=maze.getCrossSectionByZ(0);
System.out.println(Arrays.deepToString(maze2dz));
// this should throw an exception!
maze.getCrossSectionByX(-1);
} catch (IndexOutOfBoundsException e){
System.out.println("good!");
System.out.println(maze);
}
}
public static void main(String[] args){
testMazeGenerator(new SimpleMaze3dGenerator());
testMazeGenerator(new GetRandomCell());
testMazeGenerator(new GetLastCell());
/*i chose strategy pattern to allow a choice between choosing a random cell and the last cell*/
}
}