/**
 * The Door class represents a connection or route between two places. they are one way and belong to location objects. 
 * it has fields storing the direction the doors leads or points, the location you leave from, the loation you enter to., and a class field representing all possible valid directions.
 */
public class Door {

    private String direction;
    private Location leavingLocation;
    private Location enteringLocation;

    /**
     * sets direction, leavinglocation and enteringlocation to dummy values
     */
    public Door() {
        this.direction = "n";
        this.leavingLocation = new Location();
        this.enteringLocation = new Location();
    }

    /**
     * sets the fields
     * @param dir   the direction of the door: String
     * @param leave the leaving left through the door: Location
     * @param enter the location entered through the door: Location
     */
    public Door(String dir, Location leave, Location enter) {
        this.direction = dir;
        this.leavingLocation = leave;
        this.enteringLocation = enter;
    }

    /**
     * describes where the user can go through the door
     * @return a representation of where the user can go through this door: String
     */
    public String describe() {
        return "You can go " + direction + " to get to " + enteringLocation.getName(); 
    }

    /**
     * gets the leaving location
     * @return the leaving location: Location
     */
    public Location getLeaving() {
        return this.leavingLocation;
    }

    /**
     * gets the entering location
     * @return the entering location: Location
     */
    public Location getEntering() {
        return this.enteringLocation;
    }

    /**
     * gets the direction of the door
     * @return the direction to travel through the door: String
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * sets the location to leave from
     * @param leave the location to leave from: Location
     */
    public void setLeaving(Location leave) {
        this.leavingLocation = leave;
    }

    /**
     * sets the location to enter
     * @param enter the location to enter: Location
     */
    public void setEntering(Location enter) {
        this.enteringLocation = enter;
    }

    /**
     * sets the direction to travel through
     * @param dir   the direction to travel through: String
     */
    public void setDirection(String dir) {
        this.direction = dir;
    }
}