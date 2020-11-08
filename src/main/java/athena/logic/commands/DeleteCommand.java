package athena.logic.commands;

import athena.exceptions.command.DeleteInvalidIndexException;
import athena.exceptions.command.TaskNotFoundException;
import athena.task.Task;
import athena.TaskList;
import athena.ui.AthenaUi;
import java.util.Objects;

/**
 * Handles the delete command.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Initializes the object with the index of task to be deleted.
     *
     * @param index Integer representing the index of task.
     */
    public DeleteCommand(int index) {
        deleteIndex = index;
    }

    /**
     * Deletes a task from the Tasks list and
     * calls Ui to print task deleted.
     *
     * @param taskList Tasks list
     * @param athenaUi       Ui
     * @throws TaskNotFoundException Exception thrown when the user tries to enter the index of a task that
     *                               does not exist
     */
    @Override
    public void execute(TaskList taskList, AthenaUi athenaUi) throws DeleteInvalidIndexException {
        try {
            Task deletedTask = taskList.deleteTask(deleteIndex);
            String taskRestore = deletedTask.getTaskRestore();
            athenaUi.printTaskDeleted(deletedTask, taskRestore);
        } catch (TaskNotFoundException e) {
            throw new DeleteInvalidIndexException();
        }

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
        if (!(o instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return deleteIndex == that.deleteIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteIndex);
    }
}
