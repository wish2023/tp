package athena.commands;

import athena.task.Task;
import athena.TaskList;
import athena.Ui;

/**
 * Handles the delete command.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int index) {
        deleteIndex = index;
    }

    /**
     * Deletes a task from the Tasks list and
     * calls Ui to print task deleted.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task deletedTask = taskList.deleteTask(deleteIndex);
        if (deletedTask != null) {
            String taskRestore = deletedTask.getTaskRestore();
            ui.printTaskDeleted(deletedTask, taskRestore);
        } else {
            ui.printTaskNotFound(deleteIndex);
        }
    }
}
