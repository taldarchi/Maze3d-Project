package algorithms.mazeGenerators;

// TODO: Auto-generated Javadoc
/**
 * The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator {

	/**
	 * Generate.
	 *
	 * @param z the z
	 * @param x the x
	 * @param y the y
	 * @return the maze 3 d
	 */
	public Maze3d generate(int z,int x,int y); 
	
	/**
	 * Measure algorithm time.
	 *
	 * @param z the z
	 * @param x the x
	 * @param y the y
	 * @return the string
	 */
	public String measureAlgorithmTime(int z,int x,int y);
}
