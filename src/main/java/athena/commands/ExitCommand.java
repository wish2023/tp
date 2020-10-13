package athena.commands;

import athena.TaskList;
import athena.Ui;

/**
 * Handles the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Setting isExit to be true so program exits.
     */
    public ExitCommand() {
        super();
        isExit = true;
    }

    /**
     * Calls Ui to print exit message.
     *
     * @param taskList Tasks list
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printExitMessage();
    }
}