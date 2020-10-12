package athena.commands;

import athena.exceptions.DoneInvalidIndexException;
import athena.task.Task;
import athena.TaskList;
import athena.Ui;

/**
 * Handles the done command.
 */
public class DoneCommand extends Command {
    private int doneIndex;

    public DoneCommand(int index) {
        doneIndex = index - 1;
    }

    /**
     * Marks a task as done from the Tasks list and
     * calls Ui to print task marked as done.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     * @throws DoneInvalidIndexException Exception thrown when the user tries to enter the index of a task that
     *     does not exist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DoneInvalidIndexException {
        try {
            Task taskDone = taskList.markTaskAsDone(doneIndex);
            ui.printTaskDone(taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DoneInvalidIndexException();
        }
    }
}