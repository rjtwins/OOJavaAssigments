package Main;


/**
 * @author J.Vedder S4379101
 * Extends Expression,
 * Handles Expressions with a single argument and a sign.
 * Contains a Expression and a String "operator".
 */
public abstract class SingleArgExpression extends Expression{
    
    private String op;
    private Expression x;
    
    /**
     * Constructor, will construct a SingleArgExpression if supplied with
     * a sign in String format, and an Expression.
     * @param op
     * @param x
     */
    public SingleArgExpression(String op, Expression x){
        this.op = op;
        this.x = x;
    }
    
    /**
     * Returns a String representation of the Expression.
     * @return String representation of Expression
     */
    @Override
    public String toString(){
        return this.op + "(" + this.x.toString() + ")";
    }
    
    /**
     * Evaluates all contained expressions.
     * Returns self.
     * @return Expression self
     */
    @Override
    public Expression optimize(){
        this.setX(this.getX().optimize());
        return this;
    }
    
    //Getters and setters:
    public Expression getX() {
        return x;
    }

    public void setX(Expression x) {
        this.x = x;
    }
    
    
}
