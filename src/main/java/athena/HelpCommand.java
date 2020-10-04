package athena;

/**
 * Handles the help command.
 */
public class HelpCommand extends Commands {

    /**
     * Calls Ui to print TaskList.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.helpOutput();
    }
}
