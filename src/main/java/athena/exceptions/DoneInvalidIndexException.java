package athena.exceptions;

import athena.Ui;

public class DoneInvalidIndexException extends CommandException {
    public DoneInvalidIndexException() {

    }

    /**
     * Prints an error message telling user to enter a valid index number of a task to mark as done.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printDoneInvalidIndexException();
    }
}
