package athena.exceptions;

import athena.Ui;

public class EditException extends CommandException {
    public EditException() {

    }

    /**
     * Prints an error message telling user to enter a valid task index of a task to edit.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printEditException();
    }
}
