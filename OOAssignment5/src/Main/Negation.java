package Main;


/**
 * @author J.Vedder
 * extends SingleArgExpression
 * Handles expressions with one argument and the sign negation.
 */
public class Negation extends SingleArgExpression{
    
    /**
     * Constructor, when supplied with an Expression will construct an
     * Expression with a negative sign.
     * @param Expression x to negate.
     */
    public Negation(Expression x) {
        super("-", x);
    }

    /**
     * Evaluation method for the negation Expression
     * First calls the evaluation on containing expression.
     * Then evaluates itself, returns 0 of the value of the expression
     * to negate is 0. Returns one if the expression to negate is -1
     * @return Evaluated Expression
     */
    @Override
    public Expression optimize(){
        this.setX(this.getX().optimize());
        if(this.getX().getValue() == null){
            return this;
        }else if(this.getX().getValue() == 0){
            return new Constant(0);
        }else if(this.getX().getValue() == -1){
            return new Constant(1);
        }
        return this;
    }
    
    /**
     * Gets the Double value representation of the expression.
     * @return Double representation of Expression
     */
    @Override
    public Double getValue() {
        Double x = this.getX().getValue();
        if(x == null){
            return null;
        }
        return x*-1;
    }

    @Override
    public Double eval() {
        Double x = this.getX().eval();
        return x*-1;
    }
}
