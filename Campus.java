import java.util.Hashtable;

/**
 * 
 * Campus
 */
public class Campus {
    private String campusName;
    private Hashtable<String, Location> locations;
    private Location startingLocation;
    private String filename;

    /**
     * 
     */
    public Campus() {
        this.campusName = "";
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = "";
    }

    /**
     * 
     * @param name
     */
    public Campus(String name) {
        this.campusName = name;
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = "";
    }

    /**
     * 
     * @param name
     * @param fn
     */
    public Campus(String name, String fn) {
        this.campusName = name;
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = fn;
    }

    /**
     * 
     * @param location
     */
    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }

    /**
     * 
     * @param name
     * @return
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }

    /**
     * 
     * @param n
     */
    public void setCampusName(String n) {
        this.campusName = n;
    }

    /**
     * 
     * @return
     */
    public String getCampusName() {
        return this.campusName;
    }

    /**
     * 
     * @param start
     */
    public void setStartingLocation(Location start) {
        this.startingLocation = start;
    }

    /**
     * 
     * @return
     */
    public Location getStartingLocation() {
        return this.startingLocation;
    }

    /**
     * 
     * @param f
     */
    public void setFilename(String f) {
        this.filename = f;
    }

    /**
     * 
     * @return
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * 
     * @return
     */
    /*public Hashtable<String, Location> getLocations() {
        return this.locations;
    }*/
}
