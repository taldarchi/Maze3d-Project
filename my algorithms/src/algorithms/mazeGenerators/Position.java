package algorithms.mazeGenerators;

import algorithms.demo.Modifiable;

// TODO: Auto-generated Javadoc
/**
 * The Class Position.
 */
public class Position implements Modifiable<Position>{
	
	/** The z. */
	private int z;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new position.
	 */
	public Position(){
		this.z=0;
		this.x=0;
		this.y=0;
	}
	
	/**
	 * Instantiates a new position.
	 *
	 * @param z the z
	 * @param x the x
	 * @param y the y
	 */
	public Position(int z,int x,int y){
		this.z=z;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Instantiates a new position.
	 *
	 * @param p the p
	 */
	public Position(Position p){
		this.z=p.z;
		this.x=p.x;
		this.y=p.y;
	}
	
	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     * the ability to clone the states to pass arguments
     * by value and not by reference in the arraylist
     */
    public Position clone() throws CloneNotSupportedException {
        return (Position) super.clone();
    }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * overriding the equals method to compare positions properly
	 */
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Position))return false;
	    Position otherMyClass = (Position)other;
	    if(this.getZ()==otherMyClass.getZ() && this.getX() == otherMyClass.getX() && this.getY() == otherMyClass.getY())
	    	return true;
	    else
	    	return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	
	public String toString() {
		return "{" + z + ","+ x + "," + y + "}";
	}

	
}
