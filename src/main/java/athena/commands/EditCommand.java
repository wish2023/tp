package athena.commands;

import athena.TaskList;
import athena.Ui;

public class EditCommand extends Command {
    private int taskIndex;
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private String taskImportance;
    private String taskNotes;

    public EditCommand(int index, String name, String startTime, String duration, String deadline,
                       String recurrence, String importance, String notes) {
        taskIndex = index;
        taskName = name;
        taskStartTime = startTime;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
    }

    /**
     * Edits a task from the Tasks list and
     * calls Ui to print task edited.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.editTask(taskIndex, taskName, taskStartTime,taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
        ui.printTaskEdited(taskIndex, taskName, taskStartTime,taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
    }
}
