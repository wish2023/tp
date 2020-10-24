package athena.logic.commands;

import athena.TaskList;
import athena.ui.AthenaUi;

/**
 * Handles the help command.
 */
public class HelpCommand extends Command {

    /**
     * Calls Ui to print help output.
     *
     * @param taskList Tasks list
     * @param athenaUi       Ui
     */
    @Override
    public void execute(TaskList taskList, AthenaUi athenaUi) {
        athenaUi.printHelp();
    }
}
