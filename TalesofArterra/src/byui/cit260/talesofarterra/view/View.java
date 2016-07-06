/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.talesofarterra.view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Dale
 */
public abstract class View implements ViewInterface {
    
    protected String displayMessage;
    
    protected final BufferedReader keyboard = 
            
                                talesofarterra.TalesofArterra.getInFile();
    protected final PrintWriter console = 
                                talesofarterra.TalesofArterra.getOutFile();
    public View(String displayMessage) {
        this.displayMessage = displayMessage;
    }
    
    @Override
    public void display() {
        
        boolean done = false;
        do {
            // prompt for input
            String value = this.getInput();
            if (value.toUpperCase().equals("Q"))
                return;
            
            // do the requested action
            done = this.doAction(value);
        } while (!done);
    }
    
    @Override
    public String getInput() {
        //Scanner keyboard = new Scanner(System.in);
        String value = "";
        boolean valid = false;
        try {
        // while a valid name has not been entered
        while(!valid) {
            // prompt for name
            System.out.println(this.displayMessage);
            
            //get value from keyboard entry
            value = this.keyboard.readLine();
            value = value.trim();
            
            if (value.length() < 1) { //blank value entered
                System.out.println("Sorry, you must enter a value.");
                continue;
            }
            break;
            }
        }catch (Exception e) {
                System.out.println("Error reading input: " + e.getMessage());
        }
        
        return value;
    }
}
