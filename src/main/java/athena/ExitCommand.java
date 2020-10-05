package athena;

/**
 * Handles the exit command.
 */
public class ExitCommand extends Commands{

    public ExitCommand() {
        super();
        isExit = true;
    }

    /**
     * Calls Ui to print exit message.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printExitMessage();
    }
}