package athena;

/**
 * Handles the done command.
 */
public class DoneCommand extends Command {
    private int doneIndex;

    public DoneCommand(int index) {
        doneIndex = index-1;
    }

    /**
     * Marks a task as done from the Tasks list.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markTaskAsDone(doneIndex);
    }
}