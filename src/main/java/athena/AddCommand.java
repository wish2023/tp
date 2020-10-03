package athena;

import java.io.IOException;

/**
 * Handles adding tasks to the TaskList.
 */
public class AddCommand extends Commands {
    protected static String cmd;
    protected static String details;

    public AddCommand(String s, String d) {
        cmd = s;
        details = d;
    }

    /**
     * Adds a task to the taskList based on command and
     * calls Ui to print add message.
     * @param t TaskList
     * @param u Ui
     * @param s Storage
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
//        u.addOutput(task.toString(), t.taskList.size());
    }

}