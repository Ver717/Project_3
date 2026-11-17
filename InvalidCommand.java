/**
 * InvalidCommand represents something entered by the user that is not a valid action that can be taken on the campus
 */
public class InvalidCommand implements UserInputCommand {
    private String invalidCommand;

    /**
     * stores the invalid user input to invalidCommand field
     * @param invalidCommand    invalid user input: String
     */
    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    /**    (non-Javadoc)
     * @see UserInputCommand#carryOut()
     */
    public String carryOut() {
        return "Error: '" + invalidCommand + "'umw is not a valid command.\n"; 
    }
}