/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooassigment4;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CPU class with cpu stratagy.
 * extends the Player class.
 * @author J.Vedder S4379101
 */
public class CPU extends Player{
    private boolean CPUTurn = true;
    public CPU(Field color){
        super(color);
        this.setName("Hall9000");
    }
    
    public void play(View v, Board b){
        int[] coords = winMove(b);
        b.play(coords[0], coords[1], this.getColor());
    }
    
    /**
     * Generates all moves to see if there is any in the next move
     * where either the cpu or the other player can win.
     * Will play the winning move if it can win.
     * Else if the other player can win it will play
     * so that it blocks the other player.
     * @param Board b
     * @return Int[] set of coordinates
     */
    private int[] winMove(Board b){
        int[] cplay = new int[2];
        boolean cwin = false;
        int[] pplay = new int[2];
        boolean pwin = false;
        //See if there is a win in the next move.
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Board cb = b.copy();
                Board pb = b.copy();
                if(cb.play(i, j, this.getColor())){
                    if(cb.winning()){
                        cwin = true;
                        cplay[0] = i;
                        cplay[1] = j;
                        return cplay;
                    }
                }
                if(pb.play(i, j, Field.YELLOW)){   
                    if(pb.winning()){
                        pwin = true;
                        pplay[0] = i;
                        pplay[1] = j;
                        return pplay;
                    }
                }
            }
        }
        return randomMove(b.copy(), this.getColor());
    }
    
    
    //Not very strategic but it works.

    /**
     * Pick a random valid move.
     * @param Board b to pick move on
     * @param color The color of the player
     * @return int[] a set of coordinates
     */
    private int[] randomMove(Board b, Field color){
        int x = 0;
        int y = 0;
        do{
            x = ThreadLocalRandom.current().nextInt(0, 3);
            y = ThreadLocalRandom.current().nextInt(0, 3);
        }while(b.play(x, y, color) == false);
        int[] coords = {x,y};
        return coords;
    }
}
