public class TourStatus {
    private Campus campus;
    private Location currentLocation;

    /**
     * TourStatus default constructor. 
     */
    public TourStatus() {
       this.campus = new Campus();
       this.currentLocation = new Location();
    }

    /**
     * TourStatus paramaterized constructor.
     * @param campus
     */
    public TourStatus(Campus campus) {
       this.campus = campus;
       this.currentLocation = campus.getStartingLocation();
    }

    /**
     * 
     * @param campus
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * 
     * @param loc
     */
    public void setCurrentLocation(Location loc) {
        this.currentLocation = loc;
    }

    /**
     * 
     * @return
     */
    public Campus getCampus() {
        return this.campus;
    }

    /**
     * 
     * @return
     */
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * accepts current direction and updates the user’s current location. 
     * This method needs to first figure out if the user can go that direction and then if so, move the user.
     * @param dir
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
}
