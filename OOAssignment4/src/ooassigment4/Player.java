/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooassigment4;

/**
 * @author J.Vedder S4379101
 * Player abstract class.
 * Has some variables all players will use.
 * Has some methods all players will use
 * has setters and getters.
 */
public abstract class Player {

    private Field color;
    private String name;

    public Player(Field color){
        this.color = color;
    }

    void play(View v, Board b) {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public Field getColor(){
        return this.color;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
}
