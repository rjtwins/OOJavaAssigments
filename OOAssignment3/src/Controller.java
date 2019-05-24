import java.util.Arrays;
/**
 * @author J.Vedder S4379101
 * Controller for flow and menu logic.
 * Has: 
 * A view to output to
 * A list of models of shapes.
 * A current index of where the last empty spot is in the shapes array.
 */
public class Controller {
    View v;
    Shape[] shapes;
    int index;
    
    /**
     * Constructor,
     * initiates the View v and the to be used list of models.
     */
    public Controller(){
        v = new View();
        shapes = new Shape[10];
        index = 0;
    }

    /**
     * Public method to start the controller when it has been constructed.
     */
    public void start() {
        menuFlow();
    }
    
    /**
     * Handles the main menu logic.
     * Will continue to loop for menu string input until the user inputs "quit"
     * When the user quits will display a goodby message.
     */
    private void menuFlow(){
        boolean quit = false;
        while(!quit){
            welcome();
            String command = v.getString();
            switch (command){
                case "show": show();
                break;
                case "circle": circle();
                break;
                case "rectangle": rectangle();
                break;
                case "move": moveShape();
                break;
                case "remove": removeShape();
                break;
                case "sort": sortShapes();
                break;
                case "help": welcome();
                break;
                case "quit": quit = true;
                break;
                default: v.display("The command: " + command + " was not recognized try again please.");
            }
        }
        v.display("Exiting program.");
    }
    
    /**
     * Tells the view to display a welcome message.
     */
    private void welcome(){
        v.display("Use the following commands to controll the program, pleas follow a command with return to execute.");
        v.display("quit - stops the program.\n"
                + "show - lists the geometric objects.\n"
                + "circle - to add a circle if the array is not full.\n"
                + "rectangle - adds a rectangle if the array is not full.\n"
                + "move - moves an object.\n"
                + "remove - remove an object.\n"
                + "sort - sort shapes according to area.\n"
                + "help - show this message again.");
    }
    
    /**
     * Gets the strings for shapes contained in the shapes array and tells the view
     * to display them (element wise).
     */
    private void show() {
        v.display("Priting all current shapes: ");
        for(Shape s : this.shapes){
            if(s == null){
                continue;
            }
            v.display(s.toString());
        }
        v.display("Pres any key followed by return to return to the main menu.");
        v.waitForInput();
    }

    /**
     * Logic for creating a circle, tells to view to display queries and gets input from it.
     */
    private void circle() {
        v.display("Constructing circle, enter x coordinate: ");
        double x = v.getDouble();
        v.display("Enter y coordinate: ");
        double y = v.getDouble();
        v.display("Enter radius: ");
        double r = v.getDouble();
        shapes[this.index] = new Circle(r,x,y);
        this.index++;
    }

    /**
     * Logic for creating a rectangle, tells the view to display queries and gets input from it.
     */
    private void rectangle() {
        v.display("Constructing rectangle, enter x coordinate: ");
        double x = v.getDouble();
        v.display("Enter y coordinate: ");
        double y = v.getDouble();
        v.display("Enter hight: ");
        double h = v.getDouble();
        v.display("Enter with: ");
        double w = v.getDouble();
        this.shapes[this.index] = new Rectangle(x,y,w,h);
        this.index++;
    }

    /**
     * Logic for moving a shape around in space, tells the view to display a query for which
     * shape to move and how far to move it.
     */
    private void moveShape(){
        v.display("Initiating move, please enter an the index of what object you wish to move: ");
        int i1 = v.getInt();
        Shape sel = this.shapes[i1];
        if(sel == null){
            v.display("There is no shape at that index, returning to menu.");
            return;
        }
        v.display(sel.toString() + " has been selected, please enter the modification for the x coordinate: ");
        double dx = v.getDouble();
        v.display("Please enter the modification for the y coordinate: ");
        double dy = v.getDouble();
        sel.move(dx, dy);
        v.display("Shape moved, result: " + sel.toString());
    }

    /**
     * Logic for removing a shape from the shapes array.
     * tells the view to display a query for what shape to remove and removes it.
     * When a shape is removed it might leave a gap, therefore we move the 
     * shape before the last open index to the nullified index.
     * If the last open index is 0 we have an empty array so don't remove anything.
     */
    private void removeShape() {
        if(this.index == 0){
            v.display("There is nothing in the array of shapes, returning to main menu.");
        }
        v.display("Initiating remove, please enter an the index of the shape you wish to remove: ");
        int i = v.getInt();
        this.shapes[i] = null;
        this.shapes[i] = this.shapes[this.index-1];
        this.shapes[this.index-1] = null;
        this.index--;
    }

    /**
     * Sorts the array via the Comparable<Shape> interface used by Arrays.sort(Array array).
     * If we haven an empty array, there is nothing to sort so return.
     */
    private void sortShapes() {
        System.out.println(this.index);
        if(this.index == 0)
            return;
        System.out.println("STARTING SORTING:");
        Arrays.sort(this.shapes, 0, this.index);
        show();
    }
}