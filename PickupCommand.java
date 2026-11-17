/**
 * PickupCommand is a userinputcommand that represents the action of picking up an item and putting it in the backpack
 */
public class PickupCommand implements UserInputCommand{
    private String itemName; 

    /**
     * parameterized constructor, sets item name to be picked up
     * @param itemName item name to be picked up: String
     */
    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }

    /**    (non-Javadoc)
     * @see UserInputCommand#carryOut()
     */
    public String carryOut() {
        TourStatus s = TourStatus.getInstance();
        Item item = s.pickUpItemFromLocation(itemName); // dont pick at the guts
        
        String res = "\n\n\n";
        if (item == null) {
            res += "Error: failed to pick up " + this.itemName + " from " + s.getCurrentLocation().getName() + ".\n";
        } else {
            res += "You have picked up " + item.toString() + ". " + item.getMessage() + "\n\n"; 
            res += "Type 'backpack' to reveal the contents of your backpack.\nType 'drop x' to drop an item 'x' from your backpack.\n\n";
        }
        return res;
    }
}