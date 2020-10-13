package athena.exceptions;

import athena.Ui;

public class EditNoIndexException extends CommandException {
    public EditNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to mark as done.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printEditNoIndexException();
    }
}
