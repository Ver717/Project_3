import java.util.ArrayList;

/**
 * 
 * Location class reperesents a location on a campus. it has four fields, a name of the location, a description, a flag marking whether or not
 * the location has been visited, and an ArrayList of Doors denoting where the user can travel.
 */
public class Location {
    private String name;
    private String description;
    private boolean haveVisited;
    private ArrayList<Door> doors;
    private ArrayList<Item> items;

    /**
     * default constructor. 
     */
    public Location() {
        this.name = "";
        this.description = "";
        this.haveVisited = false;
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    /**
     * initializes location object w/o doors from name and description parameters.
     * @param name  name of the new location: String
     * @param desc  description of the new location: String
     */
    public Location(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.haveVisited = false;
        this.doors = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    /**
     * returns the name of the location
     * @return  the name of the location: String
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the description of the location
     * @return  the description of the location: String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * returns the flag denoting whether or not the location has been visited before on the tour
     * @return  the hasVisited flag: boolean
     */
    public boolean getHaveVisited() {
        return this.haveVisited;
    }

    /**
     * updates the name of the location
     * @param n the new name of the location: String
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * updates the description of the location
     * @param d the new description of the location: String
     */
    public void setDescription(String d) {
        this.description = d;
    }

    /**
     * updates if the lcation has been visited
     * @param v whether or not the location has been visited: boolean
     */
    public void setHaveVisited(boolean v) {
        this.haveVisited = v;
    }

    /**
     * creates and returns a string representing a description of all the doors of a location
     * @return  a string representing a description of all of the doors of a location
     */
    public String describeDoors() {
        String des = "";
        for (Door door : this.doors) {
            des += "  *  " + door.describe() + "\n";
        }
        return des;
    }
    /**
     * gives you a description of the current location, set of doors 
     * and the list of all items currently within it.
     * @return
     */
    public String describeLocation() {
        return this.description + "\n" + this.items + describeDoors();
    }

    /**
     * finds and returns a location the user can leave to from this location
     * @param dir   the direction a user wants to go: String
     * @return  a location you can leave too from this location: Location
     */
    public Location leaveLocation(String dir) {
        Location leaveLocation = null;
        for (Door d : doors) {
            if (d.getDirection().equals(dir)) {
                leaveLocation = d.getEntering();
                break;
            }
        }
        return leaveLocation;
    }

    /**
     * adds a door to the location
     * @param door  a new door to be added to the location: Door
     */
    public void addDoor(Door door) {
        this.doors.add(door);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public Item getItemNamed(String name) {
        return null;
    }

    public String getItemsInLocation() {
        String items = "";
        for (Item item : this.items) {
            items += item.toString() + "\n";
        }
        return items;
    }

    /**
     * returns a list of doors from the location
     * @return  a list of doors from the location: ArrayList
     */
    public ArrayList<Door> getDoors() {
        return this.doors;
    }
}
