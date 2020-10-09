package athena.commands;

import athena.TaskList;
import athena.Ui;

/**
 * Handles adding tasks to the Tasks list.
 */
public class AddCommand extends Command {
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private String taskImportance;
    private String taskNotes;

    public AddCommand(String name, String startTime, String duration, String deadline,
                      String recurrence, String importance, String notes) {
        taskName = name;
        taskStartTime = startTime;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
    }

    /**
     * Adds a task to the Tasks list and
     * calls Ui to print out the task added.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws AddException {
        if (taskName == "" || taskStartTime == ""){
            throw new AddException("Please input task name and start time!");
        }
        taskList.addTask(taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
        ui.printTaskAdded(taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
    }

    /**
     * Signals that the user input has to contain task name and start time.
     */
    public static class AddException extends Exception {
        AddException(String message) {
            super(message);
        }
    }

}