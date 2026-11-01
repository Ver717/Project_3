public class InvalidCommand implements UserInputCommand {
    private String invalidCommand;

    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    public String carryOut() {
        return "\"" + invalidCommand + "\" is an invalid command."; 
    }
}