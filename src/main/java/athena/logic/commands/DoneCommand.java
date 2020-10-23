package athena.logic.commands;

import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import athena.TaskList;
import athena.ui.Ui;
import java.util.Objects;

/**
 * Handles the done command.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Initializes the object with the task number of task to be mark as done.
     *
     * @param taskNumber Integer representing the task number of task.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as done from the Tasks list and
     * calls Ui to print task marked as done.
     *
     * @param taskList Tasks list
     * @param ui       Ui
     * @throws TaskNotFoundException Exception thrown when the user tries to enter the index of a task that
     *                               does not exist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        Task taskDone = taskList.markTaskAsDone(taskNumber);
        ui.printTaskDone(taskDone);
    }

    /**
     * Determines if two objects have the same attributes.
     * @param o object
     * @return true if the two objects have the same attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoneCommand)) {
            return false;
        }
        DoneCommand that = (DoneCommand) o;
        return taskNumber == that.taskNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskNumber);
    }
}