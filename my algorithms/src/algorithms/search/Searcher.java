package algorithms.search;
/**
 * The Interface Searcher
 * We may want to implement other searching algorithms in the future as well
 * E.g., beam search, A*, hill climbing, etc.
 * We want our system to work with any type of “searcher”
 * I.e., we can replace the searching algorithm without changing the system’s code
 *
 * @param <T> the generic type
 */

public interface Searcher<T> {
    
    /**
     * Search.
     *
     * @param s the s
     * @return the solution
     */
    // the search method
    public Solution<T> search(Searchable<T> s);
    
    /**
     * Gets the number of nodes evaluated.
     *
     * @return the number of nodes evaluated
     */
    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();
}
