package athena.exceptions;

import athena.Ui;

public class EmptyTaskListException extends CommandException {
    public EmptyTaskListException() {

    }

    /**
     * Prints an error message when the user tries to list out their task, but does not have any
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printEmptyTaskListException();
    }
}
