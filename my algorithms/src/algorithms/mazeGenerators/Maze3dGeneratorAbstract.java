package algorithms.mazeGenerators;

/**
 * The Class Maze3dGeneratorAbstract.
 */
public abstract class Maze3dGeneratorAbstract implements Maze3dGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#generate(int, int, int)
	 */
	@Override
	public abstract Maze3d generate(int z, int x, int y);

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Maze3dGenerator#measureAlgorithmTime(int, int, int)
	 * 
	 * implemented here because this action is the same for each algorithm
	 */
	@Override
	public String measureAlgorithmTime(int z, int x, int y) {
		long startTime=System.currentTimeMillis();
		generate(z,x,y);
		long endTime = System.currentTimeMillis();
		return Long.toString(endTime-startTime);
	}

}
