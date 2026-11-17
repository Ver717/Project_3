/**
 * BackpackCommand represents the action of looking into the backpack
 */
public class BackpackCommand implements UserInputCommand {

    /**
     * creates backpackcommand
     */
    public BackpackCommand() {}

    /**    (non-Javadoc)
     * @see UserInputCommand#carryOut()
     */
    public String carryOut() {
        return TourStatus.getInstance().listBackpackItems();
    }

}