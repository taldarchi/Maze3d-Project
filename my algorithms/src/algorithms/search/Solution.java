package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The Class Solution.
 * returns the final solution i.e the path and the cost
 * 
 * @param <T> the generic type
 */
public class Solution<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146867362919689897L;
	/** The path. */
	private List<State<T>> path;
	
	/**
	 * Instantiates a new solution.
	 */
	public Solution() {
		this.path = new ArrayList<>();
	}

	/**
	 * Adds the.
	 *
	 * @param s the s
	 */
	public void Add(State<T> s) {
		path.add(s);
	}
	
	/**
	 * Arrange.
	 */
	public void arrange() {
		Collections.reverse(path);
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public List<State<T>> getPath() {
		return path;
	}
	
	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() {
		double totalCost = 0;
		for(State<T> s : path) {
			totalCost += s.getCost();
		}
		return totalCost;
	}
	
	/**
	 * Gets the solution.
	 *
	 * @return the solution
	 */
	public List<State<T>> getSolution(){
		return path;
	
}
}