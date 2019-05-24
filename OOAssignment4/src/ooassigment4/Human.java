/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooassigment4;

/**
 *
 * @author J.Vedder
 */
public class Human extends Player{
    
    public Human(Field color) {
        super(color);
    }
    
    /**
     * Ask to player to input a move.
     * Repeat until the player makes a valid move.
     * @param v view to output/input
     * @param b Board to make the move on
     */
    @Override
    public void play(View v, Board b){
        v.display("Please insert a set of valid coordnates followed by RETURN to make a move");
        boolean notValid = true;
        while(notValid){
            String coordXY[] = v.getString().split(",");
            if(coordXY.length > 2){
                v.display("You've entered an invalid set of coordinates, try again.");
                continue;
            }
            int x = Integer.parseInt(coordXY[0]);
            int y = Integer.parseInt(coordXY[1]);
            if (b.play(x,y, getColor()))
                notValid = false;
            else
                v.display("Those coordinates are either outside the board or allready occupied."
                        + "\nPlease try again.");
        }
    }
}
