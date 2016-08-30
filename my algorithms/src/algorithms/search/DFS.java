package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The Class DFS.
 *
 * @param <T> the generic type
 * 
 * DFS algorithm  is an algorithm for traversing or searching tree or graph data structures.
 * One starts at the root (selecting some arbitrary node as the root in the case of a graph) 
 * and explores as far as possible along each branch before backtracking.
 */
public class DFS<T> extends CommonSearcher<T> {
	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	public Solution<T> search(Searchable<T> s) {
		Stack<State<T>> stack=new Stack<State<T>>();
		ArrayList<State<T>> visited=new ArrayList<State<T>>();
		stack.push(s.getStartState());
		while(!stack.isEmpty()){
			State<T> current=popStack(stack);
			if(visited.contains(current))
				continue;
			visited.add(current);
			if(current.equals(s.getGoalState()))
				return backTrace(s.getStartState(),current);
			ArrayList<State<T>> successors=s.getAllPossibleStates(current);
			for(State<T> state : successors){
				if(!visited.contains(state))
					stack.push(state);
			}
		}
		return null;
	}
}