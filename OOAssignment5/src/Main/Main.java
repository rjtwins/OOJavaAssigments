package Main;

import static Main.ExpressionFactory.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author J.Vedder S4379101
 * Main, the program starts here.
 * The main implements a testing method to test the expression system.
 */
public class Main {    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.test();
    }

    /**
     * Testing the expression system.
     */
    private void test() {
        Expression e1 = sum(con(3.0), var("a"));
        Expression e2 = sum(con(3.0), mult(con(1), con(999)));
        Expression e3 = neg(mult(var("b"), sum(var("c"),sum(con(0),var("d")))));
        Expression e4 = mult(sum(con(1),con(25)),sum(con(1),var("e")));
        Expression e5 = sum(con(3.0), neg(mult(var("f"), var("g"))));
        Expression e6 = root(sum(con(1.0), mult(var("h"), var("i"))));
        Expression e7 = sum(root(con(3)),root(var("j")));
        Expression e8 = sum(mult(con(3),var("k")),root(sum(con(10),var("m"))));
        Expression e9 = sum(mult(con(5),var("o")),root(con(1)));
        Expression e10 = sum(mult(con(5),var("n")),root(mult(con(1),var("p"))));
        
        Expression[] e = {e1,e2,e3,e4,e5,e6,e7,e8,e9,e10};
        
        System.out.println("RAW FUCNTIONS:");
        for(Expression ei:e){
            System.out.println(ei);
        }
        
        //Printing evaluated functions:
        System.out.println("\nOPTIMIZED FUCNTIONS:");
        for(Expression ei:e){
            System.out.println(ei.optimize());
        }
        
        System.out.println("\nEVALUATED FUCNTIONS:");
        for(Expression ei:e){
            System.out.println(ei.eval());
        }
    }
}