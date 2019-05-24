/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooassigment4;

/**
 *
 * @author J.Vedder S4379101
 * Field enum for filling in the cells of the board.
 * Either YELLOW or RED with values of X and O.
 */
public enum Field {
    YELLOW("X"), 
    RED("O");
    
    private final String asci;
    
    /**
     * Constructor for the enums.
     * @param asci symbol of the color
     */
    Field(String asci){
        this.asci = asci;
    }
    
    @Override
    public String toString(){
        return this.asci;
    }
}
