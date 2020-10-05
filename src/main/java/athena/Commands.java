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
     * @param t Tasks List
     * @param u Ui
     */
    public abstract void execute(TaskList taskList, Ui ui);

    public static boolean isExit() {
        return isExit;
    }
}