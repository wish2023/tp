package athena.exceptions;

import athena.Ui;

public class NoIndexDeleteException extends CommandException {
    public NoIndexDeleteException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to delete.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printNoIndexDeleteException();
    }
}
