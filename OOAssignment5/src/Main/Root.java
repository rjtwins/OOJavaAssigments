package Main;


/**
 * @author J.Vedder S4379101
 * extends SingleArgExpression
 * Handles expressions with one argument and the root operator.
 */
public class Root extends SingleArgExpression{
    
    /**
     * Constructor, constructs an Root Expression of supplied with an expression.
     * @param x
     */
    public Root(Expression x) {
        super("\u221A", x);
    }
    
    /**
     * Evaluation function,
     * First calls evaluation on contained Expression.
     * Evaluates itself, if the contained Expression is a Variable return self.
     * Else if the value of the contained Expression is 0 or 1 return a Constant
     * containing this respectively.
     * @return evaluated Expression
     */
    @Override
    public Expression optimize(){
        super.optimize();
        if(this.getX().getValue() == null){
            return this;
        }if(this.getX().getValue() == 0){
            return new Constant(0);
        }else if(this.getX().getValue() == 1){
            return new Constant(1);
        }
        return this;
    }

    /**
     * Gets the Double representation of the Expression.
     * Returns the square root of the containing expression of this is a Constant.
     * Else return null.
     * @return Double representation of Expression
     */
    @Override
    public Double getValue() {
        Double x = this.getX().getValue();
        if(x == null){
            return null;
        }
        return Math.sqrt(x);
    }

    @Override
    public Double eval() {
        Double x = this.getX().eval();
        return Math.sqrt(x);
    }
}
