package athena.logic.commands;

import athena.TaskList;
import athena.Ui;

/**
 * Handles the help command.
 */
public class HelpCommand extends Command {

    /**
     * Calls Ui to print help output.
     *
     * @param taskList Tasks list
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printHelp();
    }
}
