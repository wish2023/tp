package athena.commands;

import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import athena.TaskList;
import athena.Ui;

import java.util.Objects;

/**
 * Handles the done command.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as done from the Tasks list and
     * calls Ui to print task marked as done.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        Task taskDone = taskList.markTaskAsDone(taskNumber);
        ui.printTaskDone(taskDone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoneCommand)) {
            return false;
        }
        DoneCommand that = (DoneCommand) o;
        return doneIndex == that.doneIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doneIndex);
    }
}