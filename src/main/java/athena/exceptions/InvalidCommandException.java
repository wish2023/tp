package athena.exceptions;

import athena.Ui;

public class InvalidCommandException extends CommandException {
    public InvalidCommandException() {

    }

    /**
     * Prints an error message telling user that they entered an invalid command and prompts them to type "help".
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printInvalidCommandException();
    }
}
