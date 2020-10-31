package athena.exceptions;

import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters a '/' in a parameter's description.
 */
public class InvalidParameterException extends CommandException {
    public InvalidParameterException() {

    }

    /**
     * Prints an error message telling user that they entered an invalid parameter description.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidParameterException();
    }
}
