package athena;

public class EditCommand extends Command {
    protected static int taskIndex;
    protected static String taskName;
    protected static String taskStartTime;
    protected static String taskDuration;
    protected static String taskDeadline;
    protected static String taskRecurrence;
    protected static String taskImportance;
    protected static String taskNotes;

    public EditCommand(int index, String name, String startTime, String duration, String deadline, String recurrence, String importance, String notes) {
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
     * Edits a task from the Tasks list.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
         taskList.editTask(taskIndex, taskName, taskStartTime,taskDuration, taskDeadline, taskRecurrence, taskImportance, taskNotes);
    }
}
