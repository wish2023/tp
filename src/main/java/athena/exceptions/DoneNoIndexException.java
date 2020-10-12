package athena.exceptions;

import athena.Ui;

public class DoneNoIndexException extends CommandException {
    public DoneNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to mark as done.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printNoIndexDoneException();
    }
}
