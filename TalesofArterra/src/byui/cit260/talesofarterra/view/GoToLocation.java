/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.talesofarterra.view;

import byui.cit260.talesofarterra.control.LocationControl;
import byui.cit260.talesofarterra.control.MapControl;
import byui.cit260.talesofarterra.control.SceneControl;
import byui.cit260.talesofarterra.exceptions.MapControlException;
import byui.cit260.talesofarterra.model.Game;
import byui.cit260.talesofarterra.model.Location;
import byui.cit260.talesofarterra.model.Map;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import talesofarterra.TalesofArterra;

/**
 *
 * @author Dale
 */
class GoToLocation extends View {
    
    public GoToLocation() {
    super("\n*****************************************************************"
          + "\n*                   Change Locations                            *"
          + "\n*                  enter C to continue                          *"
          + "\n*===============================================================*");
    }
    
    @Override
    public boolean doAction(String value) {
        Game game = TalesofArterra.getGame();
        MapControl mc = new MapControl();
        Location[][] locs = null;
        ArrayList<Location> listLocations = new ArrayList<>();
        
        try {
            locs = mc.createMap(game.getCurrentMap());
        } catch(MapControlException mce) {
            ErrorView.display(this.getClass().getName(), "Error reading input: " + mce.getMessage());
        }
        
        for (int i = 0; i < locs.length; i++) {
            for (int j = 0; j < locs[0].length; j++) {
                if (locs[i][j] != game.getCurrentLocation() && locs[i][j] != null) {
                    listLocations.add(locs[i][j]);
                }
            }
        }
        
        for (Location loc : listLocations) {
            this.console.println(listLocations.indexOf(loc)
                + " - "
                + loc.getDescription());
        }
        
        this.console.println("\nChoose your next location.");
        
        int choice = 0;
        try {
            choice = Integer.parseInt(this.getInput());
        } catch(NumberFormatException ex) {
            ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
        }
        
        String locationName = listLocations.get(choice).name();
        String mapName = null;
        if (locationName.contains("Gate")) {
            mapName = locationName.replace(game.getCurrentMap().name(),"");
            mapName = mapName.replace("Gate", "");
            locationName = game.getCurrentMap().name() + mapName + "Gate";
            this.console.println("Changing map ... you should definitely explore to get your bearings.");
        } else if (locationName.contains("Door")) {
            mapName = locationName.replace(game.getCurrentMap().name(),"");
            mapName = mapName.replace("Door", "");
            locationName = game.getCurrentMap().name() + mapName + "Door";
            this.console.println("Changing map ... you should definitely explore to get your bearings.");
        };
        
        if (!(mapName == null)) {
            game.setCurrentMap(Map.valueOf(mapName));
        }
        LocationControl lc = new LocationControl();
        lc.setDialog();
        lc.changeLocation(game, Location.valueOf(locationName));
            
        DisplayDialog.display();
        
        boolean more = false;
        try {
            Class.forName("byui.cit260.talesofarterra.view." + locationName).newInstance();
            more = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            // do nothing, just move along
        }
        
        if (more != false) {
            lc.setDialog();
            DisplayDialog.display();
        }
        
        return true;
    }
    
}