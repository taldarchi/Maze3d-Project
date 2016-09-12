package algorithms.search;

import java.io.Serializable;

import algorithms.demo.Modifiable;

// TODO: Auto-generated Javadoc
/**
 * The Class State.
 * Since each node represents a “state” of the problem, we created this state class
 * Inside, we can use whatever types that can describe states generally
 * @param <T> the generic type
 */
public class State<T> implements Modifiable<State<T>>,Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -488340128770820272L;

	/** The state. */
    private T state;    // the state represented by a string
    
    /** The cost. */
    private double cost;     // cost to reach this state
    
    /** The came from. */
    private State<T> cameFrom;  // the state we came from to this state
    
    /** The came from direction. */
    private String cameFromDirection;

    /**
     * Instantiates a new state.
     */
    public State(){
    	this.state=null;
    	this.cost=0;
    	this.cameFrom=null;
    	this.cameFromDirection=null;
    }
    
    /**
     * Instantiates a new state.
     *
     * @param state the state
     */
    public State(T state){    // CTOR    
        this.state = state;
    }
    
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(T state) {
		this.state = state;
	} 

    /**
     * Gets the state.
     *
     * @return the state
     */
    public T getState() {
		return state;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Gets the came from.
	 *
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the came from.
	 *
	 * @param cameFrom the new came from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     * 
     * the ability to clone the states to pass arguments
     * by value and not by reference in the arraylist
     */
    @SuppressWarnings("unchecked")
	public State<T> clone() throws CloneNotSupportedException {
        return (State<T>) super.clone();
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * overriding the equals method to compare states
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State<?>))
			return false;
		if (obj == this)
			return true;
		return this.state.equals(((State<T>) obj).state);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
	    return this.cameFromDirection+ " to " +"Node: '" + this.state + "'";
	}
	
	/**
	 * Gets the came from direction.
	 *
	 * @return the came from direction
	 */
	public String getCameFromDirection() {
		return cameFromDirection;
	}
	
	/**
	 * Sets the came from direction.
	 *
	 * @param cameFromDirection the new came from direction
	 */
	public void setCameFromDirection(String cameFromDirection) {
		this.cameFromDirection = cameFromDirection;
	} 
}


