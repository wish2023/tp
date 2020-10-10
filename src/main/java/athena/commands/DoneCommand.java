package athena.commands;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task taskDone = taskList.markTaskAsDone(doneIndex);
            ui.printTaskDone(taskDone);
        } catch (IndexOutOfBoundsException e) {
            ui.printTaskNotFound(doneIndex);
        }
    }
}