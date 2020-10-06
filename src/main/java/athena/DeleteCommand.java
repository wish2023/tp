package athena;

/**
 * Handles the delete command.
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int index) {
        deleteIndex = index-1;
    }

    /**
     * Deletes a task from the Tasks list and
     * calls Ui to print task deleted.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.deleteTask(deleteIndex);
        ui.printTaskDeleted(deleteIndex);
    }
}
