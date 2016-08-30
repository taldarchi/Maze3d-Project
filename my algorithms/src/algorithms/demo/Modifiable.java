package algorithms.demo;

/**
 * The Interface Modifiable.
 *
 * class to make a generic type T cloneable
 *
 * @param <T> the generic type
 */
public interface Modifiable<T extends Modifiable<T>> extends Cloneable {
    
    /**
     * Clone.
     *
     * @return the t
     * @throws CloneNotSupportedException the clone not supported exception
     */
    T clone() throws CloneNotSupportedException;
}