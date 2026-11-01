public class PickupCommand implements UserInputCommand{
    private String itemName; 

    public PickupCommand(String itemName) {
        this.itemName = itemName;
    }

    public String carryOut() {
        return "";
    }
}