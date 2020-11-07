package athena.exceptions;

import athena.ui.AthenaUi;

public class InvalidDateFormatException extends CommandException {
    public InvalidDateFormatException() {

    }

    /**
     * Prints error message telling user to input date in correct format.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidDateFormatException();
    }
}
