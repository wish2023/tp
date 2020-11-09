package athena.commands;

import athena.DateChecker;
import athena.Importance;
import athena.TaskList;
import athena.exceptions.command.CommandException;
import athena.exceptions.command.AddMissingRequiredParametersException;
import athena.exceptions.command.AddDateWrongFormatException;
import athena.task.Task;
import athena.ui.AthenaUi;

import java.util.Objects;

/**
 * Handles adding tasks to the Tasks list.
 */
public class AddCommand extends Command {
    public static final String TODAY = "today";
    private String taskName;
    private String taskStartTime;
    private String taskDuration;
    private String taskDeadline;
    private String taskRecurrence;
    private Importance taskImportance;
    private String taskNotes;
    private Boolean isTaskFlexible;

    /**
     * Initializes the object with the parameters.
     *
     * @param name       String representing name of task.
     * @param startTime  String representing start time of task.
     * @param duration   String representing duration of task.
     * @param deadline   String representing deadline of task.
     * @param recurrence String representing recurrence of task.
     * @param importance String representing importance of task.
     * @param notes      String representing additional notes of task.
     * @param isFlexible Boolean representing if task time is flexible
     */
    public AddCommand(String name, String startTime, String duration, String deadline,
                      String recurrence, Importance importance, String notes, boolean isFlexible) {
        taskName = name;
        assert !taskName.equals("");
        taskStartTime = startTime;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
        isTaskFlexible = isFlexible;
    }

    /**
     * Adds a task to the Tasks list and
     * calls Ui to print out the task added.
     *
     * @param taskList Tasks list
     * @param athenaUi       Ui
     * @throws CommandException Exception thrown when there is an error when the user inputs a command
     */
    @Override
    public void execute(TaskList taskList, AthenaUi athenaUi)
            throws CommandException {
        if (taskName.equals("")) {
            throw new AddMissingRequiredParametersException();
        }
        if ((taskRecurrence.contains("-") && (taskRecurrence.length() == "dd-MM-yyyy".length()))
                || taskRecurrence.toLowerCase().equals(TODAY)) {
            DateChecker dateChecker = new DateChecker(taskRecurrence, taskStartTime);
        }
        try {
            Task task = taskList.addTask(taskName, taskStartTime, taskDuration, taskDeadline,
                    taskRecurrence, taskImportance, taskNotes, isTaskFlexible);
            athenaUi.printTaskAdded(task);
        } catch (NumberFormatException e) {
            throw new AddDateWrongFormatException();
        }

    }

    /**
     * Determines if two objects have the same attributes.
     *
     * @param o object
     * @return true if the two objects have the same attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddCommand)) {
            return false;
        }
        AddCommand that = (AddCommand) o;
        return Objects.equals(taskName, that.taskName)
                && Objects.equals(taskStartTime, that.taskStartTime)
                && Objects.equals(taskDuration, that.taskDuration)
                && Objects.equals(taskDeadline, that.taskDeadline)
                && Objects.equals(taskRecurrence, that.taskRecurrence)
                && taskImportance == that.taskImportance
                && Objects.equals(taskNotes, that.taskNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskStartTime, taskDuration,
                taskDeadline, taskRecurrence, taskImportance, taskNotes);
    }

}