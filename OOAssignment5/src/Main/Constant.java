package Main;




/**
 * @author J.Vedder S4379101
 * Extends the NoArgExpresison class.
 * Handles constant values with no operators or signs.
 */
public class Constant extends NoArgExpression{
    private double constant;
    
    /**
     * Constructor, gives a Constant object when supplied with a double.
     * @param double the Constant will contain.
     */
    public Constant(double constant){
        this.constant = constant;
    }

    /**
     * toString for this object.
     * @return String representation of the content of the Constant.
     */
    @Override
    public String toString(){
        return Double.toString(constant);
    }
    
    /**
     * Evaluation of the expression, nothing to evaluate returns self.
     * @return Expression self
     */
    @Override
    public Expression optimize(){
        return this;
    }

    /**
     * Returns a Double representation of the double this constant holds.
     * @return Double value this constant holds.
     */
    @Override
    public Double getValue() {
        return this.constant;
    }

    @Override
    public Double eval() {
        return this.constant;
    }
}