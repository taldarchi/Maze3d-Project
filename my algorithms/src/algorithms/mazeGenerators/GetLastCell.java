package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class GetLastCell.
 * get the last cell from the arraylist C of the growing tree algorithm
 * implemented in strategy pattern
 */
public class GetLastCell extends GrowingTreeGenerator {

	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.GrowingTreeGenerator#getCell(java.util.ArrayList)
	 */
	@Override
	public Position getCell(ArrayList<Position> C) {
		Position c=C.get(C.size()-1);
		return c;
	}
}
