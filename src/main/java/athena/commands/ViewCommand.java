package athena.commands;

import athena.exceptions.TaskNotFoundException;
import athena.TaskList;
import athena.Ui;

/**
 * Handles the view command.
 */
public class ViewCommand extends Command {
    private int taskNumber;

    /**
     * Initializes the object with the task number of task to be viewed.
     *
     * @param taskNumber Integer representing the task number of task.
     */
    public ViewCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * View a task from the Tasks list and
     * calls Ui to print task details.
     *
     * @param taskList Tasks list
     * @param ui       Ui
     * @throws TaskNotFoundException Exception thrown when the user tries to enter the index of a task that
     *                               does not exist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        String taskDescription = taskList.getTaskDescription(taskNumber);
        ui.printTaskDetails(taskDescription);
    }

}
