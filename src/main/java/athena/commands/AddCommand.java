package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.AddException;

/**
 * Handles adding tasks to the Tasks list.
 */
public class AddCommand extends Command {
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private Importance taskImportance;
    private String taskNotes;

    public AddCommand(String name, String startTime, String duration, String deadline,
                      String recurrence, String importance, String notes) {
        taskName = name;
        taskStartTime = startTime;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = Importance.valueOf(importance.toUpperCase());
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
        if (taskName.equals("") || taskStartTime.equals("")){
            throw new AddException();
        }
        taskList.addTask(taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
        ui.printTaskAdded(taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance.toString(), taskNotes);
    }
}