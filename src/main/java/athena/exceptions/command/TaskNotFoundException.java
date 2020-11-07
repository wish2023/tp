package athena.exceptions.command;

import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;

/**
 * Exception that is thrown when the program is unable to locate the task specified by the user.
 */
public class TaskNotFoundException extends CommandException {

    private int taskNumber;

    /**
     * Initializes the task number.
     * @param taskNumber number of the the task in the task list
     */
    public TaskNotFoundException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Prints an error message telling user that there is no task at the index they specified.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printTaskNotFound(taskNumber);
    }
}
