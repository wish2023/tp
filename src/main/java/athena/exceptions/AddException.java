package athena.exceptions;

import athena.Ui;

public class AddException extends CommandException {
    public AddException() {

    }

    /**
     * Prints an error message telling user to fill in the name and start time of the task.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printAddException();
    }
}
