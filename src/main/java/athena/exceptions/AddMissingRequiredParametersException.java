package athena.exceptions;

import athena.Ui;

public class AddMissingRequiredParametersException extends CommandException {
    public AddMissingRequiredParametersException() {

    }

    /**
     * Prints an error message telling user to fill in the name and start time of the task.
     */
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printAddException();
    }
}
