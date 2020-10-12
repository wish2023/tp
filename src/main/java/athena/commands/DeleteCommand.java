package athena.commands;

import athena.exceptions.DeleteInvalidIndexException;
import athena.task.Task;
import athena.TaskList;
import athena.Ui;

/**
 * Handles the delete command.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int index) {
        deleteIndex = index - 1;
    }

    /**
     * Deletes a task from the Tasks list and
     * calls Ui to print task deleted.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     * @throws DeleteInvalidIndexException Exception thrown when the user tries to enter the index of a task that
     *     does not exist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DeleteInvalidIndexException {
        try {
            Task deletedTask = taskList.deleteTask(deleteIndex);
            String taskRestore = deletedTask.getTaskRestore();
            ui.printTaskDeleted(deletedTask, taskRestore);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteInvalidIndexException();
        }
    }
}
