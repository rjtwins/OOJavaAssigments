package Main;


/**
 * @author J.Vedder S4379101
 * extends the DoubleArgExpression class.
 * Handles expressions with two arguments with the operator multiplication.
 */
public class Multiplication extends DoubleArgExpression{

    /**
     * Constructor, gives a Expression when supplied with x and y to 
     * multiply.
     * @param Expression x
     * @param Expression y
     */
    public Multiplication(Expression x, Expression y){
        super(x,y,"*");
    }
    
    /**
     * Calls super to have its containing expressions evaluated.
     * Evaluates the expression, first checks for Variable type expressions.
     * Then checks if the result of the multiplication is going to be one or
     * zero. If so return a constant with that number.
     * If all checks out or there are two Constants return self.
     * @return Expression evaluated Expression
     */
    @Override
    public Expression optimize(){
        super.optimize();
        Double x = this.getX().getValue();
        Double y = this.getY().getValue();
        
        //If x is not a var check x
        if(x != null){
            if(x == 0){
                return new Constant(0);
            }
            if(x == 1){
                return this.getY();
            }
        }
        //if y is not a var check y
        if(y != null){
            if(y == 0){
                return new Constant(0);
            }
            if(y == 1){
                return this.getX();
            }
        }
        if(y != null && x != null){
            return new Constant(x*y);
        }
        //If neither are 1 or 0 return the entire expression.
        return this;
    }

    /**
     * Get value method,
     * sees if there are any variables in the expressions contained.
     * if so return null
     * else return the product of the two contained constants.
     * @return Double (x*y)
     */
    @Override
    public Double getValue() {
        Double x = this.getX().getValue();
        Double y = this.getY().getValue();
        if(x == null || y == null){
            return null;
        }
        return x*y;
    }

    @Override
    public Double eval() {
        Double x = this.getX().eval();
        Double y = this.getY().eval();
        return x*y;
    }
}
