package athena.exceptions.command;

import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters a command not recognized by the program.
 */
public class InvalidCommandException extends CommandException {
    public InvalidCommandException() {

    }

    /**
     * Prints an error message telling user that they entered an invalid command and prompts them to type "help".
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidCommandException();
    }
}
