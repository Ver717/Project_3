/**
 * DropCommand represents the action of dropping an item from the user's backpack at the current location
 */
public class DropCommand implements UserInputCommand {
    private String itemName;

    /**
     * sets the name of the item to be dropped
     * @param itemName  the name of the item to be dropped: String
     */
    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    /**    (non-Javadoc)
     * @see UserInputCommand#carryOut()
     */
    public String carryOut() {
        TourStatus s = TourStatus.getInstance();
        Item item = s.dropItemFromBackpack(itemName); // get these methods finalized in places like tours status and location first
        
        String res = "\n\n\n";
        if (item == null) {
            res += "Error: failed to drop the " + itemName + " at " + s.getCurrentLocation().getName() + ".\n";
        }
        else {
            res += "the " + item.getName() + " has been dropped in " + s.getCurrentLocation().getName() + ".\n"; 
            res += "Enter 'pickup x' to pick up an item 'x'.\n";
        }
        return res;
    }
}