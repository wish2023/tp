package athena.exceptions;

import athena.ui.Ui;

/**
 * Exception thrown when the user enters the edit command without specifying the index of the task they want to edit.
 */
public class EditNoIndexException extends CommandException {
    public EditNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to mark as done.
     */
    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printEditNoIndexException();
    }
}
