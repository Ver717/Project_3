import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
* The TourStatus class represents the state of a tour of a campus object. it has fields storing a campus object and the current location of the tour.
*/
public class TourStatus {
    private Campus campus;
    private Location currentLocation;
    private ArrayList<Item> backpack;
    
    private static TourStatus tourInstance;

    /**
     * default constructor. campus and location are not initialized.
     */
    private TourStatus() {
       this.campus = new Campus();
       this.currentLocation = new Location();
       this.backpack = new ArrayList<>();
    }

    // not on diagram
    /**
     * TourStatus paramaterized constructor. fully initializes TourStatus objects.
     * @param campus    the campus to be toured and tracked by the TourStatus object
     */
    private TourStatus(Campus campus) {
       this.campus = campus;
       this.currentLocation = campus.getStartingLocation();
    }

    public static TourStatus getInstance() {
        if (tourInstance == null) {
            tourInstance = new TourStatus();
        }
        return tourInstance;
    }

    // to be deleted
    /**
     * updates the campus being toured
     * @param campus    the new campus to be toured: Campus
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * updates the current location of the tour
     * @param loc   the new current location of the tour: Location
     */
    public void setCurrentLocation(Location loc) {
        this.currentLocation = loc;
    }

    /**
     * returns the campus object being toured
     * @return  the campus object being toured: Campus
     */
    public Campus getCampus() {
        return this.campus;
    }

    /**
     * returns the current location of the tour
     * @return  the current location of the tour: Location
     */
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * accepts current direction and if a door corresponds to that direction, 
     * updates the user’s current location. Otherwise, current location is unchanged.
     * @param dir   the direction the user wants to go: String
     */
    public void updateTourLocation(String dir) {
        Location nLocation = null;
        
        for (Door door : this.currentLocation.getDoors()) {
            if (door.getDirection().equalsIgnoreCase(dir)) {
                nLocation = door.getEntering(); 
                break;
            }
        }
        if (nLocation != null) {
            this.currentLocation = nLocation;
        }
    }

    public void addToBackpack(Item item) {
        this.backpack.add(item);
    }

    public Item dropItemFromBackpack(String name) {
        //Item item = this.backpack.sort();
        return null;
    }

    public Item pickUpItemFromLocation(String name) {
        return null;
    }

    public String listBackpackItems() {
        String items = "";
        for (Item item : this.backpack) {
            items += item + "\n";
        }
        return items;
    }
}
