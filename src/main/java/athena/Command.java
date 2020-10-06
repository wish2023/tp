package athena;

/**
 * Abstract Command class for Command objects.
 */
public abstract class Command {
    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    /**
     * For Commands execution.
     * @param taskList Tasks List
     * @param ui Ui
     */
    public abstract void execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return isExit;
    }
}