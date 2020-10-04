package athena;

import java.io.IOException;

/**
 * Handles the delete command.
 */
public class DeleteCommand extends Commands {
    protected static int delete;

    public DeleteCommand(int d) {
        delete = d;
    }

    /**
     * Deletes a task from the taskList and
     * calls Ui to print delete message.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.tasks.get(delete);
        taskList.tasks.remove(delete);
        ui.deleteOutput(task.toString(), taskList.tasks.size());
    }
}
