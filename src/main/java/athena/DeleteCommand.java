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
     * @param t TaskList
     * @param u Ui
     * @param s Storage
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        Task task = t.taskList.get(delete);
        t.taskList.remove(delete);
        try {
            s.writeTaskToFile(false, "", t);
        } catch (IOException e) {
            u.showError(e.getMessage());
        }
        u.deleteOutput(task.toString(), t.taskList.size());
    }
}
