package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GetRandomCell.
 * get random cell from the arraylist C of the growing tree algorithm
 * implemented in strategy pattern
 */
public class GetRandomCell extends GrowingTreeGenerator{
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.GrowingTreeGenerator#getCell(java.util.ArrayList)
	 */
	@Override
	public Position getCell(ArrayList<Position> C){
		Random rnd =new Random();
		int cell=rnd.nextInt(C.size());
		Position c= C.get(cell);
		return c;
	}
}
