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
     * @param t TaskList
     * @param u Ui
     * @param s Storage
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
//        u.doneOutput(task.toString());
    }
}