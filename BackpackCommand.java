public class BackpackCommand implements UserInputCommand {

    public BackpackCommand() {

    }

    public String carryOut() {
        return TourStatus.getInstance().listBackpackItems();
    }

}