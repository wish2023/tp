package athena.exceptions;

import athena.Ui;

/**
 * Exception that is thrown when the user enters an index without a task when using the view command.
 */
public class ViewInvalidIndexException extends CommandException {
    public ViewInvalidIndexException() {

    }

    /**
     * Prints an error message telling user to enter a valid index number of a task to view.
     */
    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printViewInvalidIndexException();
    }
}