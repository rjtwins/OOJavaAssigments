package Main;


import java.util.*;
/**
 * Jorre Vedder S4379101
 * Class adjusted to contain route, examined configurations in a HashSet as well as
 * using a priority queue instead of a normal to use heuristic search instead
 * or simple breadth-first search.
 */

/**
 * A class that implements a breadth-first search algorithm
 * for finding the Configurations for which the isSolution predicate holds
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.5
 * @date 25-02-2017
 */
public class Solver
{
   // A queue for maintaining graphs that are not visited yet.
    Queue<Configuration> toExamine;
    HashSet<Configuration> examined;
    List<Configuration> route;

    /**
     * Constructor, initializes the toExamine and examined lists and adds
     * the initial configuration passed to the constructor to the to Examine
     * list.
     * @param Configuration g initial Configuration
     */
    public Solver( Configuration g ) {
        toExamine = new PriorityQueue<>();
        examined = new HashSet<>();
        this.toExamine.add(g);
    }

    /**
     * Breath-first best first solver.
     * Has a priority queue to pick the best successor configuration first.
     * First checks if the configuration is solvable before starting to look
     * for a solution. When a solution is found gets it route and stores it
     * in a list.
     * @return a string representation of the solution
     */
    public String solve() {
        if(!toExamine.peek().isSolvable()){
            return "Non-solvable";
        }
        while ( ! toExamine.isEmpty() ) {
            Configuration next = toExamine.remove();
            examined.add(next);
            if ( next.isSolution() ) {
                this.route = next.pathFromRoot();
                return "Success!";
            } else {
                for ( Configuration succ : next.successors() ) {
                    if(examined.contains(succ))
                        continue;
                    toExamine.add(succ);
                }
            }
        }
        return "Failure!";
    }
    
    /**
     * Getter for the route stored.
     * @return
     */
    public List<Configuration> getRoute(){
        return this.route;
    }
}