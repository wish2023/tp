package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.TaskNotFoundException;

import java.util.Objects;

public class EditCommand extends Command {
    private int taskNumber;
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private Importance taskImportance;
    private String taskNotes;

    public EditCommand(int number, String name, String startTime, String duration, String deadline,
                       String recurrence, Importance importance, String notes) {
        taskNumber = number;
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
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        taskList.editTask(taskNumber, taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
        ui.printTaskEdited(taskNumber, taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EditCommand)) {
            return false;
        }
        EditCommand that = (EditCommand) o;
        return taskIndex == that.taskIndex
                && Objects.equals(taskName, that.taskName)
                && Objects.equals(taskStartTime, that.taskStartTime)
                && Objects.equals(taskDuration, that.taskDuration)
                && Objects.equals(taskDeadline, that.taskDeadline)
                && Objects.equals(taskRecurrence, that.taskRecurrence)
                && taskImportance == that.taskImportance
                && Objects.equals(taskNotes, that.taskNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskIndex, taskName, taskStartTime, taskDuration,
                taskDeadline, taskRecurrence, taskImportance, taskNotes);
    }
}
