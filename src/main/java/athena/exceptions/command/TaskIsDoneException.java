package athena.exceptions.command;

import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;

/**
 * Exception thrown when user attempts to mark a task as done which is already done.
 */
public class TaskIsDoneException extends CommandException {
    public TaskIsDoneException() {

    }

    /**
     * Print message when user attempts to mark a task as done which is already done.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printTaskIsDoneException();
    }
}
