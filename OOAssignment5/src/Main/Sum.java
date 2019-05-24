package Main;


/**
 * @author J.Vedder S4379101
 * Extends DoubleArgExpression
 * Handles Expressions with two arguments and the addition operator.
 */
public class Sum extends DoubleArgExpression{

    /**
     * Constructor, will construct a DoubleArgExpression if supplied with
     * two expression to apply the addition operator to.
     * @param Expression x
     * @param Expression y
     */
    public Sum(Expression x, Expression y){
        super(x,y,"+");
    }
    
    @Override
    public Expression optimize(){
        super.optimize();
        Double x = this.getX().getValue();
        Double y = this.getY().getValue();
        
        //If x is not a var check x
        if(x != null){
            if(x == 0){
                return this.getY();
            }
        }
        //if y is not a var check y
        if(y != null){
            if(y == 0){
                return this.getY();
            }
        }
        
        if(y != null && x != null){
            return new Constant(x+y);
        }
        
        //If both parts contained are expressions return this.
        return this;
    }

    /**
     * Get the Double representation of the Expression.
     * Will return null if either of the Expressions contained are a Variable.
     * Will return the sum of the Double values of two Constants if two 
     * Constants are contained in the expression.
     * @return Double representation of Expression
     */
    @Override
    public Double getValue() {
        Double x = this.getX().getValue();
        Double y = this.getY().getValue();
        if(x == null || y == null){
            return null;
        }
        return x + y;
    }

    @Override
    public Double eval() {
        Double x = this.getX().eval();
        Double y = this.getY().eval();
        return x + y;
    };
}
