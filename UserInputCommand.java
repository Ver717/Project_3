/**
 * this interface represents user input while interacting with simulator
 * UserInputCommand
 */
public interface UserInputCommand {
    /**
     * carries out the action the user indicated
     * @return a string describing result of action to print to user: String
     */
    String carryOut();
}