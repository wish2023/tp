package athena.exceptions;

import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters the delete command without specifying an index of a task to view.
 */
public class ViewNoIndexException extends CommandException {
    public ViewNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to view.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printViewNoIndexException();
    }
}
