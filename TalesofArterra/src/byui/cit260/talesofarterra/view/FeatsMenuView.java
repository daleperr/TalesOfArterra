/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.talesofarterra.view;

//import java.util.Scanner;
import byui.cit260.talesofarterra.model.Character;
import byui.cit260.talesofarterra.control.CharacterControl;
import byui.cit260.talesofarterra.exceptions.CharacterControlException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lucas
 */
public class FeatsMenuView extends View {
    private Character char1;
             

    public FeatsMenuView() {
       super("\n****************************************************************"
          + "\n*                      FEATS MENU                              *"
          + "\n*       Select the new feat your character will learn:         *"         
          + "\n*==============================================================*"
          + "\n*     KEY TO PRESS                           FEAT              *"
          + "\n*--------------------------------------------------------------*"
          + "\n*       \"1\"............................COMBAT CASTING        *"
          + "\n*       \"2\"............................GREAT FORTITUDE       *"
          + "\n*       \"3\"............................IMPROVED INITIATIVE   *"
          + "\n*       \"4\"............................IRON WILL             *"
          + "\n*       \"5\"............................LIGHTNING REFLEXES    *"
          + "\n*       \"6\"............................SPELL FOCUS           *"
          + "\n*       \"7\"............................SPELL PENETRATION     *"
          + "\n*       \"8\"............................STEALTHY              *"
          + "\n*       \"9\"............................TOUGHNESS             *"
          + "\n*                                                              *"
          + "\n*       \"H\"............................HELP                  *"
          + "\n*       \"Q\"............................QUIT***(TEMP)         *"         
          + "\n****************************************************************"
          + "\n*               Please enter an option:                        *"
          + "\n****************************************************************");

    }
    
    @Override
    public boolean doAction(String value)
    {        
        CharacterControl charControl = new CharacterControl();
        try {
            char1 = charControl.loadCharacter("playerChar.ser");
        } catch (CharacterControlException ex) {
            this.console.println("Error reading input: " + ex.getMessage());
        }
        int feature = 0;
        boolean exit = false;
        
           switch(value)
           {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8": 
            case "9": 
                try {
                    feature = Integer.parseInt(value) - 1;
                    charControl.enableFeats(char1, feature);
                    try {
                        charControl.saveCharacter(char1, "playerChar.ser");
                    } catch (CharacterControlException ex) {
                        ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
                    }
                    exit = true;
                    break;
                } catch(NumberFormatException ne) {
                    this.console.println( "ERROR: That is not a valid value!" );
                }
            case "H":
            case "h": featsHelp();
                          break;         
            case "Q":
            case "q": exit = true;
                          break;                          
            default: this.console.println( "ERROR: That is not a valid value!" );
            }
        
        return exit;

    }
    public void featsHelp()
    {
        this.console.println("\n*** featsHelp stub function called ***");
    }


}

