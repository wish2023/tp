package athena.exceptions;

import athena.ui.AthenaUi;

public class DateHasPassedException extends CommandException {
    public DateHasPassedException() {

    }

    /**
     * Prints an error message telling user their proposed date has passed.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printDateHasPassedException();
    }
}
