package athena.commands;

import athena.TaskList;
import athena.Ui;
import athena.exceptions.AddException;
import athena.exceptions.CommandException;

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
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    public abstract void execute(TaskList taskList, Ui ui) throws CommandException, AddException;

    public boolean getIsExit() {
        return isExit;
    }
}