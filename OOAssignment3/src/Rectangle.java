/**
 * @author J.Vedder S4379101
 * Model for the Rectangle implements Shape.
 * Its defined by a x, y coordinates, with and hight.
 */
public class Rectangle implements Shape{
    
    private double x,y,with,hight;
    
    /**
     * Constructor
     * @param x of left lower bottom coordinate
     * @param y of left bottom coordinate
     * @param with of the rectangle
     * @param hight of the rectangle
     */
    public Rectangle(double x, double y, double with, double hight){
        this.x = x;
        this.y = y;
        this.with = with;
        this.hight = hight;
    }

    /**
     * To get the left bound of the bounding box.
     * @return double left bound
     */
    @Override
    public double left() {
        return this.x;
    }

    /**
     * To get the right bound of the bounding box.
     * @return double right bound
     */
    @Override
    public double right() {
        return this.x + this.with;
    }

    /**
     * To get the bottum bound of the bounding box.
     * @return double bottum bound
     */
    @Override
    public double bottum() {
        return this.y;
    }

    /**
     * To get the top bound of the bounding box.
     * @return double top bound
     */
    @Override
    public double top() {
        return this.y + this.hight;
    }

    /**
     * To get the area of the shape.
     * @return double area
     */
    @Override
    public double area() {
        return this.with * this.hight;
    }

    /**
     * To move the circle by x or by y.
     * @param dx to move on x axe
     * @param dy to move on y axe
     */
    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
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
        return "Ractangle with h = " + this.hight + " and w = " + this.with + " at x = " + this.x + " y = " + this.y + " and area: " + this.area();
    }
}