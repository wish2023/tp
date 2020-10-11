package athena.exceptions;

import athena.Ui;

public class InvalidIndexDoneException extends CommandException {
    public InvalidIndexDoneException() {

    }

    /**
     * Prints an error message telling user to enter a valid index number of a task to mark as done.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printInvalidIndexDoneException();
    }
}
