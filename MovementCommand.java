public class MovementCommand implements UserInputCommand {
    private String dir;

    public MovementCommand(String dir) {
        this.dir = dir;
    }

    public String carryOut() {
        TourStatus s = TourStatus.getInstance();
        Location prev = s.getCurrentLocation();
        s.updateTourLocation(dir);

        String res = "";
        Location curr = s.getCurrentLocation();
        
        if (curr.equals(prev)) { // if status couldn't update location
            res = "Error: could not move in specified direction.\n";
        }
        // use location.describeLocation if dont have to account for haveVisited. 
        res += "\n\n" + curr.getName() + "\n"; // append description of name, des, 
        if (curr.getHaveVisited() == false) { // add description of list of items and doors. this code should be in location
            res += curr.getDescription() + "\n";
        }
        curr.setHaveVisited(true);

        res += curr.getItemsInLocation()+ "\n";
        res += curr.describeDoors();

        return res;
    }
}