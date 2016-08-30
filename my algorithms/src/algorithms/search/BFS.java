package algorithms.search;

import java.util.ArrayList;

/**
 * The Class BFS.
 * Advantages of  BFS:- 
 * 1. Solution will definitely be found by if there is one.
 * 2. BFS will never get trapped in blind alley , means unwanted nodes.
 * 3. If there are more than one solution then it will find the solution with minimal steps.
 * 
 * Advantages Of DFS :-
 * 1. Memory requirement is Linear WRT Nodes.
 * 2. Less time and space complexity rather than BFS.
 * 3. Solution can be found out without much more search.
 * 
 * 
 * i chose this option to implement BFS because that way i could advance in the search and change each node without
 * changing all other nodes . so i used the cloneable implementation and clone() function.
 * also i used arrayList for the closedSet and overwritten it's equals function.
 */
public class BFS<T> extends CommonSearcher<T> {

public Solution<T> search(Searchable<T> s){
	  openList.add(s.getStartState());
	  ArrayList<State<T>> closedSet=new ArrayList<State<T>>();

	  while(openList.size()>0){
	    State<T> n=popOpenList();// dequeue
	    closedSet.add(n);
	    if(n.equals(s.getGoalState()))
	      return backTrace(s.getStartState(), n); 
	      // private method, back traces through the parents

	    ArrayList<State<T>> successors=s.getAllPossibleStates(n); //however it is implemented 
	    for(State<T> state : successors){
	    	if(!closedSet.contains(state) && ! openList.contains(state)){
	    		state.setCameFrom(n);
	    		openList.add(state);
	      }
	      else if (state.getCost() > n.getCost()+calcCost(n,state)) {
			if (!openList.contains(state)) {
					openList.add(state);
				}
				else{
					openList.remove(state);
					openList.add(state);
			}
	      }
	    }
	  }
	return null;
}
}