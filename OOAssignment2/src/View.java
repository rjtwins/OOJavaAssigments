
import java.util.Scanner;

/**
 * The view, handles in and output between the user and the model.
 * Contains some logic for starting up the game.
 * @author J.Vedder S4379101
 */
public class View {
    Gallows model;
    Scanner scan = new Scanner(System.in);

    /**
     *  Constructor, starts the setup and initiates the play sequence.
     */
    public View() {
        setup();
    }
    
    /**
     * Sets up an instance of Gallows and gets the word that will be used for the game.
     */
    private void setup(){
        displayWelcome();
        String word = getInput();
        if(word != null)
            this.model = new Gallows(word);
        else
            this.model = new Gallows();
        this.model.setView(this);
        this.model.play();
    }
    
    /**
     * Method for getting user input when setting up the game.
     * @return word to play with
     */
    public String getInput() {
        if(scan.next().equalsIgnoreCase("R")){
            return null;
        }else{
            display("Please enter the word you would like to play with and press return to continue.");
            String word = scan.next();
            if (word == null || word.equalsIgnoreCase("")){
                display("An invalide string was entered we will play with a random word");
                return null;
            }
            return word;
        }
    }
    
    /**
     * Method for getting a single char from the user.
     * @return character input by user
     */
    public char getGues() {
        display("Please make a guess by entering a letter and pressing return.");
        boolean correct = false;
        String guess = null;
        while(!correct){
            guess = scan.next();
            if(guess.length() > 1){
                display("Please only enter a single character as a guess, try again and press return");
            }else{
                correct = true;
            }  
        }
        return guess.toCharArray()[0];
    }
    /**
     * Displays a welcome message when setting up the game.
     */
    public void displayWelcome() {
        System.out.println("Welcome to Gallows, press r to play with a random word or i to input a word and press return to continue.");
    }
    
    /**
     * Method for outputting to the user, this can be adjusted if a different output medium is used.
     * @param display string s
     */
    public void display(String s){
        System.out.println(s);
    }

    /**
     * Creates the illusion of a cleared screen to prevent participants from reading the user inputed word.
     */
    public void clearScreen() {  
        for(int i = 0;i < 100; i++){
            System.out.println("");
        }
    }  

    /**
     * Display a win message.
     */
    void displayWin() {
        display("Congratulations, YOU WON!");
    }

    /**
     * Display a lose message.
     */
    void displayLose() {
        display("You got hanged, YOU LOSE!");
    }        
}
