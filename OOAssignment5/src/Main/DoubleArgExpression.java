package Main;


/**
 * @author J.Vedder S4379101
 * extends Expression, represents an expression with two arguments and an operator.
 * Contains two expressions and an operator.
 */
public abstract class DoubleArgExpression extends Expression{
    private Expression x,y;
    private String op;
    
    /**
     * Constructor
     * @param Expression x
     * @param Expression y
     * @param String operator between x and y.
     */
    public DoubleArgExpression(Expression x, Expression y, String op){
        this.x = x;
        this.y = y;
        this.op = op;
    }

    /**
     * toString method gives a String representation of what this expression
     * contains.
     * @return String representation of the Expression
     */
    @Override
    public String toString(){
        return "(" + x.toString() + ")" + this.op + "(" + this.y.toString() + ")";
    }

    /**
     * Evaluation method for the Expression.
     * Calls optimize on all contained Expressions.
     * @return Expression self
     */
    @Override
    public Expression optimize(){
        this.setX(this.getX().optimize());
        this.setY(this.getY().optimize());
        return this;
    }
    
    //Getters and setters:    
    public Expression getX() {
        return x;
    }

    public void setX(Expression x) {
        this.x = x;
    }

    public Expression getY() {
        return y;
    }

    public void setY(Expression y) {
        this.y = y;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
    
    
}
