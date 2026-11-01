import java.util.Hashtable;

/**
 * The Campus class represents a campus to be toured. It has fields storing the campus name, a hashtable of locations on the campus,
 * a location to start the tour, and the filename the data of the class is read from. Objects of this class need to be fully initialized via the TourUMW class.
 */
public class Campus {
    private String campusName;
    private Hashtable<String, Location> locations;
    private Location startingLocation;
    private String filename;

    /**
     * default constructor
     */
    public Campus() {
        this.campusName = "";
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = "";
    }

    /**
     * Paramaterized constructor that takes a campus name. It is otherwise identical to default constructor.
     * @param name  the name of the campus: String
     */
    public Campus(String name) {
        this.campusName = name;
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = "";
    }

    /**
     * Paramaterized constructor that takes a campus name and input file name.
     * @param name  the name of the campus: String
     * @param fn    the name of the input file: String
     */
    public Campus(String name, String fn) {
        this.campusName = name;
        this.locations = new Hashtable<String, Location>();
        this.startingLocation = new Location();
        this.filename = fn;
    }

    /**
     * adds a location to the campus's list of locations.
     * @param location  location to be added to the campus: Location
     */
    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }

    /**
     * returns the location object paired with the given key.
     * @param name  name of the location to be retuned: String
     * @return
     */
    public Location getLocation(String name) {
        return locations.get(name);
    }

    /**
     * updates the name of the campus
     * @param n the new name of the campus: String
     */
    public void setCampusName(String n) {
        this.campusName = n;
    }

    /**
     * returns the name of the campus
     * @return  the name of the campus: String
     */
    public String getCampusName() {
        return this.campusName;
    }

    /**
     * updates the starting location of the tour
     * @param start the starting location of the tour: Location
     */
    public void setStartingLocation(Location start) {
        this.startingLocation = start;
    }

    /**
     * returns the starting location of the tour
     * @return  the new starting location of the tour: Location
     */
    public Location getStartingLocation() {
        return this.startingLocation;
    }

    /**
     * updates the input file name of the campus
     * @param f the new input file name of the campus: String
     */
    public void setFilename(String f) {
        this.filename = f;
    }

    /**
     * returns the input file name of the campus
     * @return  the input file name of the campus: String
     */
    public String getFilename() {
        return this.filename;
    }
}
