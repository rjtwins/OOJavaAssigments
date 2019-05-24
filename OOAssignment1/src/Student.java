/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Contains the data
 * @author J.Vedder S4379101
 */
public class Student {
    private int number;
    private String first_name;
    private String last_name;
    
    /**
     * Constructor for making a student with relevant information.
     * @param number
     * @param first_name
     * @param last_name
     */
    public Student(int number,String first_name,String last_name){
        this.number = number;
        this.first_name = first_name;
        this.last_name = last_name;   
    }
    
    /**
     * Setter for changing first name
     * @param first_name
     */
    public void change_first_name(String first_name){
        this.first_name = first_name;
    }
    
    /**
     * Setter for changing last name
     * @param last_name
     */
    public void change_last_name(String last_name){
        this.last_name = last_name;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    /**
     * Making a comprehensible string from all the data in this class
     * @return String
     */
    @Override
    public String toString(){
        return this.first_name + " " + this.last_name + " ,s" + Integer.toString(this.number);
    }
}
