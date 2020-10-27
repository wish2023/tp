package athena.exceptions;

import athena.ui.AthenaUi;

/**
 * Handles exception for when the user does not format some of the parameters in the add command correctly.
 */
public class AddDateWrongFormatException extends CommandException {
    public AddDateWrongFormatException() {

    }

    /**
     * Prints an error message telling user to format their parameters in the correct way.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printAddDateWrongFormatException();
    }
}
