package Main;


import java.util.HashMap;
import java.util.Map;
/**
 * @author J.Vedder S4379101
 * Expression abstract class.
 * implements the Map to check constants against.
 * Has several abstract functions used by child classes.
 */
public abstract class Expression {
    
    private Map<String, Double> env;
    
    /**
     * Constructor generates and fills the map.
     */
    public Expression(){
        env = new HashMap<String,Double>();
        env.put("pi", 3.1415);
        env.put("a", 42.);
        env.put("b", 1.);
        env.put("c", 3.);
        env.put("d", 5.);
        env.put("e", 7.);
        env.put("f", 9.);
        env.put("g", 11.);
        env.put("h", 12.);
        env.put("i", 13.);
        env.put("j", 14.);
        env.put("k", 15.);
        env.put("m", 16.);
        env.put("n", 17.);
        env.put("o", 18.);
        env.put("p", 19.);
    }
    
    //Several funtions implimented by all child classes.
    @Override
    public abstract String toString();
    
    public Expression optimize(){
        return this;
    };
    
    public abstract Double eval();
    
    public abstract Double getValue();
    
    public Map<String,Double> env(){
        return this.env;
    }
}
