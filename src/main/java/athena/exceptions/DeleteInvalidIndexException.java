package athena.exceptions;

import athena.Ui;

public class DeleteInvalidIndexException extends CommandException {
    public DeleteInvalidIndexException() {

    }

    /**
     * Prints an error message telling user to enter a valid index number of a task to delete.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printDeleteInvalidIndexException();
    }
}
