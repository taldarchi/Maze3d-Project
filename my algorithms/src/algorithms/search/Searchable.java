package algorithms.search;

import java.util.ArrayList;

/**
 * The Interface Searchable.
 * The algorithm just knows Searchable (and State)
 * It does not know any specific searchable domains (and states)
 * we can switch searchable domains independently from the algorithm
 * @param <T> the generic type
 */
public interface Searchable<T>{
	   
   	/**
   	 * Gets the start state.
   	 *
   	 * @return the start state
   	 */
   	State<T> getStartState();
	   
   	/**
   	 * Gets the goal state.
   	 *
   	 * @return the goal state
   	 */
   	State<T> getGoalState();
	   
   	/**
   	 * Gets the all possible states.
   	 *
   	 * @param s the s
   	 * @return the all possible states
   	 */
   	ArrayList<State<T>> getAllPossibleStates(State<T> s);
	}