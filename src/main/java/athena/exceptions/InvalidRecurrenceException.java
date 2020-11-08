package athena.exceptions;

import athena.ui.AthenaUi;

public class InvalidRecurrenceException extends CommandException {
    public InvalidRecurrenceException() {

    }

    /**
     * Prints error message telling user to input recurrence date in correct format.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidRecurrenceException();
    }
}
