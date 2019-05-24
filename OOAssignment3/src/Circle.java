/**
 * @author J.Vedder S4379101
 * Model for the circle implements Shape.
 * Its defined by a radius, x and y coordinates.
 */
public class Circle implements Shape{
    
    private double r,x,y;
    
    /**
     * Constructor
     * @param r radius of circle
     * @param x of circle center
     * @param y of circle center
     */
    public Circle(double r, double x, double y){
        this.r = r;
        this.x = x;
        this.y = y;
    }

    /**
     * To get the left bound of the bounding box.
     * @return double left bound
     */
    @Override
    public double left() {
        return this.x - this.r;
    }

    /**
     * To get the right bound of the bounding box.
     * @return double right bound
     */
    @Override
    public double right() {
        return this.x + this.r;
    }

    /**
     * To get the bottum bound of the bounding box.
     * @return double bottum bound
     */
    @Override
    public double bottum() {
        return this.y - this.r;
    }

    /**
     * To get the top bound of the bounding box.
     * @return double top bound
     */
    @Override
    public double top() {
        return this.y + this.r;
    }

    /**
     * To get the area of the shape.
     * @return double area
     */
    @Override
    public double area() {
        return 2*Math.PI*this.r;
    }

    /**
     * To move the shape by x and or by y
     * @param dx to move on x axe
     * @param dy to move on y axe
     */
    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dx;
    }

    /**
     * To compare two shapes, compares the area of the other shape to this one.
     * @param t a shape to compare to.
     * @return int negative if smaller, 0 if same, positive if larger.
     */
    @Override
    public int compareTo(Shape t) {
       if(area() > t.area()){
           return 1;
       }else if(area() == t.area())
           return 0;
       else
           return -1;
    }
    
    /**
     * To output the content of the class via a string.
     * @return string
     */
    @Override
    public String toString(){
        return "Circle with r = " + this.r + " at x = " + this.x + " y = " + this.y + " with area: " + this.area();
    }
}