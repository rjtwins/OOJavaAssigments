/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooassigment4;

/**
 *
 * @author J.Vedder
 * This controls the game flow, requests/sends input/output to the view
 * Comunicates with the model (Board).
 */
public class Controller {
    View v;
    Board b;
    Player[] players;
    char mode = 's';
    
    /**
     * Constructor,
     * initiates the View v
     * Generates the init board and players list.
     */
    public Controller(){
        this.v = new View();
        this.b = new Board();
        this.players = new Player[2];
    }

    /**
     * Public method to start the controller when it has been constructed.
     * Sets up the game by asking the players for what mode we will play in.
     */
    public void start() {
        welcome();
        //Determin if we are doing sp or mp
        players[0] = new Human(Field.RED);
        if("m".equalsIgnoreCase(v.getString())){
            players[1] = new CPU(Field.YELLOW);
        }else{
            players[1] = new Human(Field.YELLOW);
        }
        getNames();
        playGame();
    }

    /**
     * Tells the view to display a welcome message.
     */
    private void welcome(){
        v.display("Welcome to CLASSIC TIC, TAC, TOE!");
        v.display("For single player press S, for multiplayer press M");
        v.display("Confirm your command with RETURN.");
    }

    /**
     * Main game control flow.
     * Play the game until either the board is full or there is a win.
     * Display what it was and end the program.
     */
    private void playGame() {
        boolean noWin = true;
        boolean space = true;
        while(noWin && space){
            for(Player p:players){
                v.display(this.b.toString());
                v.display("It is " + p.getName() + "'s turn.");
                p.play(v, this.b);
                if(this.b.winning()){
                    noWin = false;
                    v.display("Congratulations " + p.toString() + "!");
                    break;
                }
                if(this.b.boardFull()){
                    space = false;
                    v.display("A tie has been struck, please play again.");
                    break;
                }
            }
        }
        if(!noWin){
            
        }
    }

    /**
     * Get names for the players.
     */
    private void getNames() {
        for(Player p:this.players){
            if(p.getName() != null)
                continue;
            v.display("state your name followed by a RETURN.");
            p.setName(v.getString());
        }
    }
}
