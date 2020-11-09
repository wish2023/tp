package athena.exceptions.command;

import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters the edit command without specifying the index of the task they want to edit.
 */
public class EditNoIndexException extends CommandException {
    public EditNoIndexException() {

    }

    /**
     * Prints an error message telling user to enter an index number of a task to mark as done.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printEditNoIndexException();
    }
}
