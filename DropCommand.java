public class DropCommand implements UserInputCommand {
    private String itemName;

    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    public String carryOut() {
        return "";
    }
}