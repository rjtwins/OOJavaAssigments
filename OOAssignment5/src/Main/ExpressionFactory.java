/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Constant;
import Main.Expression;
import Main.Multiplication;
import Main.Negation;
import Main.Root;
import Main.Sum;
import Main.Variable;

/**
 *
 * @author J.Vedder
 */
public class ExpressionFactory {
    public static Constant con(double x){
        return new Constant(x);
    }

    public static Variable var(String x){
        return new Variable(x);
    }

    public static Negation neg(Expression x){
        return new Negation(x);
    }

    public static Root root(Expression x){
        return new Root(x);
    }

    public static Sum sum(Expression x, Expression y){
        return new Sum(x,y);
    }

    public static Multiplication mult(Expression x, Expression y){
        return new Multiplication(x,y);
    }
}
