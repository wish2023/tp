package athena;

/**
 * Handles the list command.
 */
public class ListCommand extends Commands {

    /**
     * Calls Ui to print TaskList.
     * @param t TaskList
     * @param u Ui
     * @param s Storage
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) {
        u.list(t);
    }
}
