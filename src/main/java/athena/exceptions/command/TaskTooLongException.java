package athena.exceptions.command;

import athena.ui.AthenaUi;

public class TaskTooLongException extends CommandException {
    private int taskNumber;

    public TaskTooLongException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printTaskTooLong(taskNumber);
    }
}
