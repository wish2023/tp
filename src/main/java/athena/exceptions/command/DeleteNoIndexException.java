package athena.exceptions.command;

import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters the delete command without specifying an index of a task to delete.
 */
public class DeleteNoIndexException extends CommandException {
    public DeleteNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to delete.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printDeleteNoIndexException();
    }
}
