package athena.logic.commands;

import athena.Importance;
import athena.TaskList;
import athena.ui.AthenaUi;
import athena.exceptions.TaskNotFoundException;
import java.util.Objects;

/**
 * Handles the edit command.
 */
public class EditCommand extends Command {
    private int taskNumber;
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private Importance taskImportance;
    private String taskNotes;

    /**
     * Initializes the object with the parameters.
     *
     * @param number     Integer representing index of task.
     * @param name       String representing name of task.
     * @param startTime  String representing start time of task.
     * @param duration   String representing duration of task.
     * @param deadline   String representing deadline of task.
     * @param recurrence String representing recurrence of task.
     * @param importance String representing importance of task.
     * @param notes      String representing additional notes of task.
     */
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
     * @param taskList Tasks list
     * @param athenaUi       Ui
     * @throws TaskNotFoundException Exception thrown when the user tries to enter the index of a task that
     *                               does not exist
     */
    @Override
    public void execute(TaskList taskList, AthenaUi athenaUi) throws TaskNotFoundException {
        taskList.editTask(taskNumber, taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
        athenaUi.printTaskEdited(taskNumber, taskName, taskStartTime, taskDuration, taskDeadline,
                taskRecurrence, taskImportance, taskNotes);
    }

    /**
     * Determines if two objects have the same attributes.
     * @param o object
     * @return true if the two objects have the same attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EditCommand)) {
            return false;
        }
        EditCommand that = (EditCommand) o;
        return taskNumber == that.taskNumber
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
        return Objects.hash(taskNumber, taskName, taskStartTime, taskDuration,
                taskDeadline, taskRecurrence, taskImportance, taskNotes);
    }
}
