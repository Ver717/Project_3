import java.util.ArrayList;

/**
 * 
 * Location
 */
public class Location {
    private String name;
    private String description;
    private boolean haveVisited;
    private ArrayList<Door> doors;

    /**
     * 
     */
    public Location() {
        this.name = "";
        this.description = "";
        this.haveVisited = false;
        this.doors = new ArrayList<>();
    }

    /**
     * 
     * @param name
     * @param desc
     */
    public Location(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.haveVisited = false;
        this.doors = new ArrayList<>();
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 
     * @return
     */
    public boolean getHaveVisited() {
        return this.haveVisited;
    }

    /**
     * 
     * @param n
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * 
     * @param d
     */
    public void setDescription(String d) {
        this.description = d;
    }

    /**
     * 
     * @param v
     */
    public void setHaveVisited(boolean v) {
        this.haveVisited = v;
    }

    /**
     * 
     * @return
     */
    public String describeDoors() {
        String des = "";
        for (Door door : this.doors) {
            des += "  *  " + door.describe() + "\n";
        }
        return des;
    }

    /**
     * given a direction (n,s,e,w), 
     * find the location you can exit to and return a Location object. 
     * Use the direction to find whether there is a door in the ArrayList 
     * which matches the valid direction and door.
     * @param dir
     * @return
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
     * 
     * @param door
     */
    public void addDoor(Door door) {
        this.doors.add(door);
    }

    /**
     * 
     * @return
     */
    public ArrayList<Door> getDoors() {
        return this.doors;
    }
}
