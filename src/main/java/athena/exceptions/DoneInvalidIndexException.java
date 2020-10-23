package athena.exceptions;

import athena.ui.Ui;

/**
 * Exception that is thrown when the user enters an index without a task when using the done command.
 */
public class DoneInvalidIndexException extends CommandException {
    public DoneInvalidIndexException() {

    }

    /**
     * Prints an error message telling user to enter a valid index number of a task to mark as done.
     */
    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printDoneInvalidIndexException();
    }
}
