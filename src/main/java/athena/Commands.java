package athena;

/**
 * Abstract Command class for Command objects.
 */
public abstract class Commands {
    protected static boolean isExit;

    public Commands() {
        isExit = false;
    }

    /**
     * For Commands execution.
     * @param t TaskList
     * @param u Ui
     * @param s Storage
     */
    public abstract void execute(TaskList taskList, Ui ui);

    public static boolean isExit() {
        return isExit;
    }
}