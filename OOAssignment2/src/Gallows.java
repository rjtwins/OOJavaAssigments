/**
 * Model for the gallows, has the variables, word, guess, letters and lives to keep track of the state of the game.
 * After construction contains methods for making guesses during the game as well as for comparing those with the word to be guessed.
 * An instance of Gallows requires a View object (or a implementation of it) to be assigned to it via the setView(View view) method.
 * quarries for input and output will be given to the view during the game.
 * @author J.Vedder S4379101
 */
public class Gallows {
    private StringBuilder word;
    private StringBuilder guess;
    private StringBuilder letters;
    private int lives;
    
    //The state indicated the current state of the model, 0 dead, 1 playing 2 won.
    private int state;
    private View view;

    /**
     * Constructor for the model when no user input was given.
     * It will pick a "random" word from a txt file "words.txt"
     * It then creates a StringBuilder and calls the setup method to set internal variables.
     */
    public Gallows() {
        WordReader reader = new WordReader("words.txt");
        System.out.println(reader.getNumberOfWords());
        this.word = new StringBuilder(reader.getWord());
        setup();
    }
    
    /**
     * Constructor for the model when user input was given.
     * Creates a StringBuilder from the input and calls the setup method to set internal variables.
     * @param word to play with String s
     */
    public Gallows(String s){
        this.word = new StringBuilder(s);
        setup();
    }
    
    /**
     * Calls the constructGuess method and then sets up the models initial state variables.
     */
    private void setup() {
        this.letters = new StringBuilder();
        constructGuess();
        this.lives = 10;
        this.state = 1;
        
    }

    /**
     * Main game loop method, plays the game until the user either wins or loses.
     * Asks the view for input every round and checks the current game state.
     * Displays a message upon win or lose and ends the game
     */
    void play() {
        view.clearScreen();
        view.display(toString());
        while(state == 1){
            makeGuess();
            view.clearScreen();
            view.display(toString());        
        }
        if(state == 0){
            //we got hanged
            view.displayLose();
        }else{
            //we won!
            view.displayWin();
        }
    }
    
    /**
     * Takes the word to guess and to construct a dotted string (StringBuilder instance) to be filled in based on it.
     */
    public void constructGuess(){
        //This is a bit ugly
        this.guess = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            this.guess.append(".");
        }
    }
    
    /**
     * Asks view for input and calls a method to compare it to the to guess word.
     * After comparing it updates the internal state variables (lives, updateState()) based on the correctness of the guess.
     * @return true if correct guess, false if incorrect
     */
    public boolean makeGuess(){
        char guess = view.getGues();
        if(!compareAndFill(guess)){
            lives -= 1;
            updateState();
            return false;
        }
        updateState();
        return true;
    }
    
    /**
     * Updates the internal game state based on the current lives and the amount of unguessed letters.
     */
    private void updateState(){
        if(lives <= 0){
            state = 0;
        }else if(this.guess.indexOf(".") == -1){
            state = 2;
        }
    }
    
    /**
     * Compares the input to the to guess word, determines if the input is present and if so fills in the letter.
     * @param character to look if present in the to guess word
     */
    private boolean compareAndFill(char c){
        boolean match = false;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == c){
                //correct input found
                this.guess.setCharAt(i, c);
                match = true;
            }
        }
        if(!match){
            //Wrong guess
            this.letters.append(c);
            return false;
        }
        return true;
    }
    
    /**
     * Override for toString method to be able to print a nice representation of the game sate;
     * @return string form of the current game state
     */
    @Override
    public String toString(){
        return "Current lives: " + this.lives + "\n" +"Letters used: " + this.letters + "\n" + "Current guess: " + guess.toString();
    }

    /**
     * Setter for the view;
     * @param an implementation of the View class
     */
    void setView(View view) {
        this.view = view;
    }
}
