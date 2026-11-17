/**
 * MovementCommand represents the action of the user moving to a different location
 */
public class MovementCommand implements UserInputCommand {
    private String dir;

    /**
     * sets the direction to move in
     * @param dir the directin the user wants to move: String
     */
    public MovementCommand(String dir) {
        this.dir = dir;
    }

    /**    (non-Javadoc)
     * @see UserInputCommand#carryOut()
     */
    public String carryOut() { // okay looking at code structure ure not supposed to be plucking at the weeds of tourstatus like this use its methods instead. get the program working before yu fuck with it though
        TourStatus s = TourStatus.getInstance();
        Location prev = s.getCurrentLocation();
        s.updateTourLocation(dir);

        String res = "";
        Location curr = s.getCurrentLocation();
        
        if (curr.equals(prev)) { // if status couldn't update location
            res = "Error: could not move in specified direction.\n";
        }
      
        res += "\n\n" + curr.getName() + "\n"; 
        if (curr.getHaveVisited() == false) { 
            res += curr.getDescription() + "\n";
        }
        curr.setHaveVisited(true);

        res += curr.getItemsInLocation() + "\n";
        res += curr.describeDoors();
  

        return res;
    }
}