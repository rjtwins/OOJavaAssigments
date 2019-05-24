package Main;





/**
 * @author J.Vedder S4379101
 * extends NoArgExpression
 * Handles expressions with no arguments containing a single variable.
 */
public class Variable extends NoArgExpression{
    private String name;

    /**
     * Constructor, will construct a Variable Expression if supplied with 
     * a string to be contained.
     * @param String Variable disgination.
     */
    public Variable(String name){
        this.name = name;
    }
    
    /**
     * Returns the String representation of the contained String.
     * @return String disgination of variable.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Returns a Double representation of the contained String.
     * Attempts to find a reference to a Double in the map.
     * Returns null if none is found.
     * @return Double representation of Expression
     */
    @Override
    public Double getValue() {
        return null;
    }

    @Override
    public Double eval() {
        //System.out.println("Eval var name: " + name);
        return this.env().get(name);
    }
}
