import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The Door class represents a connection or route between two places. they are one way and belong to location objects. 
 * it has fields storing the direction the doors leads or points, the location you leave from, the loation you enter to., and a class field representing all possible valid directions.
 */
public class Door {

    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;

    private static ArrayList<String> directions = new ArrayList<String>(List.of("n", "s", "e", "w"));

    /**
     * default constructor, not fully initialized
     */
    public Door() {
        this.direction = "n";
        this.leavingLocation = new Location();
        this.enteringLocation = new Location();
    }

    /**
     * 
     * @param dir
     * @param leave
     * @param enter
     */
    public Door(String dir, Location leave, Location enter) {
        this.direction = dir;
        this.leavingLocation = leave;
        this.enteringLocation = enter;
    }

    /**
     * 
     * @return
     */
    public String describe() {
        return "You can go " + direction + " to get to " + enteringLocation.getName(); //? correct location?
    }

    /**
     * 
     * @return
     */
    public Location getLeaving() {
        return this.leavingLocation;
    }

    /**
     *  
     * @return
     */
    public Location getEntering() {
        return this.enteringLocation;
    }

    /**
     * 
     * @return
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * 
     * @param leave
     */
    public void setLeaving(Location leave) {
        this.leavingLocation = leave;
    }

    /**
     * 
     * @param enter
     */
    public void setEntering(Location enter) {
        this.enteringLocation = enter;
    }

    /**
     * 
     * @param dir
     */
    public void setDirection(String dir) {
        this.direction = dir;
    }

    /**
     * 
     * @return
     */
    public static ArrayList<String> getDirSet() {
        return directions;
    }

    /**
     * 
     * @param dir
     * @return
     */
    public static boolean isValidDir(String dir) {
        if (directions.contains(dir)) {
            return true;
        }
        return false;
    }
}