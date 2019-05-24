
import java.util.Scanner;
/**
 * @author J.Vedder S4379101
 * Handles interfacing with an output device, in this case the terminal.
 */
class View {
    Scanner scan;
    
    /**
     * Constructor, initiates the scanner for later use.
     */
    public View(){
        this.scan = new Scanner(System.in);
    }
    
    /**
     * Print out a given string.
     * @param s to print
     */
    public void display(String s){
        System.out.println(s);
    }
    
    /**
     * Get the next input string.
     * @return user input string
     */
    public String getString(){
        return scan.next();
    }
    
    /**
     * Get the next input integer.
     * @return user input integer
     */
    public int getInt(){
        return scan.nextInt();
    }
    
    /**
     * Get the next input double.
     * @return user input double.
     */
    public double getDouble(){
        return scan.nextDouble();
    }

    /**
     * "Clears" the screen by skipping a hudred lines.
     */
    void clearScreen() {
        for(int i = 0;i < 100; i++){
            System.out.println("");
        }
    }
    
    /**
     * Wait for a user to input something and press return.
     */
    void waitForInput(){
        String line = scan.next();
    }
}