package athena.exceptions;

import athena.ui.AthenaUi;

public class InvalidDeadlineException extends CommandException {
    public InvalidDeadlineException() {

    }

    /**
     * Print message telling user they have mistyped the deadline date.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidDeadlineException();
    }
}
