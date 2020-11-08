package athena;

import athena.ui.AthenaUi;

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
     * @param athenaUi       Ui
     */
    @Override
    public void execute(TaskList taskList, AthenaUi athenaUi) {
        athenaUi.printExitMessage();
    }
}