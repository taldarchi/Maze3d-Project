package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Interface GrowingTreeGeneratorInterface.
 */
public interface GrowingTreeGeneratorInterface extends Maze3dGenerator {

	/**
	 * Gets the cell.
	 *
	 * @param C the c
	 * @return the cell
	 */
	Position getCell(ArrayList<Position> C);
}
