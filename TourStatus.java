import java.util.ArrayList;
import java.util.Optional;

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

    /**
     * adds an item to the backpack
     * @param item  an item to be added to the backpack: Item
     */
    public void addToBackpack(Item item) {
        this.backpack.add(item);
    }

    /**
     * removes an item from the backpack and adds it to current location
     * @param name  the name of the item to be removed: String
     * @return the item that was removed from the backpack, or null if no item was found: Item
     */
    public Item dropItemFromBackpack(String name) {
        //Item match = searchItems(name, this.backpack);

        Optional<Item> match = this.backpack.stream()
            .filter(item -> name.equalsIgnoreCase(item.getName()))
            .findFirst();
        if (match.isPresent()) {
            Item item = match.get();
            backpack.remove(item);
            this.currentLocation.addItem(item);
            return item;
        }
        return null;
    }

    /**
     * puts an item from the location into the backpack
     * @param name  the name of the item to be picked up: String
     * @return  the item that was picked up from the location, or null if no matching item was found: Item
     */
    public Item pickUpItemFromLocation(String name) { 
        Item match = this.currentLocation.getItemNamed(name); 
        
        if (match != null) {
            this.currentLocation.removeItem(match);
            this.backpack.add(match);
        }
        return match;
    }

    /**
     * formats the items into the backpack into a printable string
     * @return  a string representation of the items in the user's backpack: String
     */
    public String listBackpackItems() {
        String items = "";
        for (Item item : this.backpack) {
            items += item.toString() + "\n";
        }
        return items;
    }

    /**
     * returns the user's backpack
     * @return the user's backpack: ArrayList<Item>
     */
    public ArrayList<Item> getBackpack() {
        return this.backpack;
    }
}
