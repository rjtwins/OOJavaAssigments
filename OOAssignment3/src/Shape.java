/**
 * @author J.Vedder S4379101
 */
public interface Shape extends Comparable<Shape>{
    public double left();
    public double right();
    public double bottum();
    public double top();
    public double area();
    public void move(double dx, double dy);
}