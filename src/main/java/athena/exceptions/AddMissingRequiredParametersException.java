package athena.exceptions;

import athena.Ui;

/**
 * Handles exception for when the user does not fill in the name or the start time of the task.
 */
public class AddMissingRequiredParametersException extends CommandException {
    public AddMissingRequiredParametersException() {

    }

    /**
     * Prints an error message telling user to fill in the name and start time of the task.
     */
    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printAddMissingRequiredParametersException();
    }
}
