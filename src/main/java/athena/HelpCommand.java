package athena;

/**
 * Handles the help command.
 */
public class HelpCommand extends Commands {

    /**
     * Calls Ui to print help output.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui. printHelp();
    }
}
