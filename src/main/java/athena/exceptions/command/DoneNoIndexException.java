package athena.exceptions.command;

import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters the done command without specifying an index of a task to done.
 */
public class DoneNoIndexException extends CommandException {
    public DoneNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to mark as done.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printDoneNoIndexException();
    }
}
