package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * The Class CommonSearcher.
 * 
 * an abstract class with what all searchers have in common.
 * It will be wasteful to implement it over and over again with each algorithm.
 * 
 * @param <T> the generic type
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	/** The open list. */
	protected PriorityQueue<State<T>> openList;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;

	 /**
 	 * Instantiates a new common searcher.
 	 */
 	public CommonSearcher() {
		  openList=new PriorityQueue<State<T>>(new Comparator<State<T>>()
				  {
					@Override
					public int compare(State<T> arg0, State<T> arg1) {
						return ((int)arg0.getCost()-(int)arg1.getCost());
					}
			  
				  });
		  evaluatedNodes=0;
		 }

	 /**
 	 * Pop open list.
 	 *
 	 * @return the state
 	 */
 	protected State<T> popOpenList() {
	  evaluatedNodes++;
	  return openList.poll();
	 }
	 
 	/**
 	 * Pop stack.
 	 *
 	 * @param stack the stack
 	 * @return the state
 	 */
 	protected State<T> popStack(Stack<State<T>> stack) {
		  evaluatedNodes++;
		  return stack.pop();
		 }

	 
	/**
	 * Back trace.
	 *
	 * @param start the start
	 * @param goal the goal
	 * @return the solution
	 */
	protected Solution<T> backTrace(State<T> start, State<T> goal) {
		Solution<T> solution = new Solution<T>();
		State<T> temp= goal;

		while (!temp.equals(start)){
				solution.Add(temp);
				temp=temp.getCameFrom();
		}
		solution.arrange();
		return solution;
	}
	
	/**
	 * Calculate the cost.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @return the double
	 */
	public double calcCost(State<T> s1, State<T> s2){
		return 1;
	}

/* (non-Javadoc)
 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
 */
@Override
public int getNumberOfNodesEvaluated() {
 return evaluatedNodes;
}


/* (non-Javadoc)
 * @see algorithms.search.Searcher#search(algorithms.search.Searchable)
 */
@Override
public abstract Solution<T> search(Searchable<T> s);

}
