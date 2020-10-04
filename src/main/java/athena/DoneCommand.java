package athena;

import java.io.IOException;

/**
 * Handles the done command.
 */
public class DoneCommand extends Commands {
    protected static int done;

    public DoneCommand(int d) {
        done = d;
    }

    /**
     * Marks a task as done from the taskList and
     * calls Ui to print done message.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.tasks.get(done);
        task.setDone();
        ui.doneOutput(task.toString());
    }
}