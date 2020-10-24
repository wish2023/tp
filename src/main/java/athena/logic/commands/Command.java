package athena.logic.commands;

import athena.TaskList;
import athena.ui.AthenaUi;
import athena.exceptions.CommandException;

/**
 * Abstract Command class for Command objects.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Set isExit to be false initially.
     */
    public Command() {
        isExit = false;
    }

    /**
     * For Commands execution.
     *
     * @param taskList Tasks list
     * @param athenaUi       Ui
     * @throws CommandException Exception thrown when there is an error in user command
     */
    public abstract void execute(TaskList taskList, AthenaUi athenaUi) throws CommandException;

    /**
     * Check if the command is exit.
     *
     * @return true if exit, false if not exit.
     */
    public boolean getIsExit() {
        return isExit;
    }
}