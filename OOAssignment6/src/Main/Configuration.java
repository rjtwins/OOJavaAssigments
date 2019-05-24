package Main;

/**
 * Jorre Vedder S4379101
 * Class adjusted to contain route pathFromRoute and setParent in order to get a
 * route and be able to set the parent.
 */



/**
 * An interface for representing nodes in a state space.
 * 
 * @author Sjaak Smetsers
 * @version 1.3
 * @date 25-02-2017
 */

import java.util.Collection;
import java.util.List;

public interface Configuration extends Comparable<Configuration> {
   /**
     * To obtain the parent of the current configuration, i.e.
     * the configuration which had this as one of its successors
     *
     * @return a reference to the parent
     */
    public abstract Configuration parent();
    
    /**
     * To obtain the successors for a specific configuration
     *
     * @return a collection of configurations containing the successors
     */
    public abstract Collection<Configuration> successors();

    /**
     * For marking final / solution configurations.
     * 
     * @return true if a this is a solution, false otherwise
     */
    public abstract boolean isSolution();
        
    /**
     * To build a path from the root configuration to the current one.
     *
     * @return a list of successive configurations from the root to 'this'
     */
    public abstract List<Configuration> pathFromRoot();

    /**
     * Set the parent of this configuration (where was it generated from).
     * @param parent it was generated from
     */
    public void setParent(Configuration parent);
    
    /**
     * Method for getting the heuristic in an integer value;
     * @return heuristic of configuration
     */
    public int getH();
    
    /**
     * Method for checking if the current configuration can be solved or does
     * not have a solution.
     * @return true if solvable false if not
     */
    public boolean isSolvable();
}
