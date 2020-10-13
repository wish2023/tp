package athena.exceptions;

import athena.Ui;

public class TaskNotFoundException extends CommandException {

    private int taskNumber;

    public TaskNotFoundException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printTaskNotFound(taskNumber);
    }
}
