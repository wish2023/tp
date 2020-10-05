package athena;

/**
 * Handles adding tasks to the Tasks list.
 */
public class AddCommand extends Commands {
    protected static String taskName;
    protected static String taskStartTime;
    protected static String taskDuration;
    protected static String taskDeadline;
    protected static String taskRecurrence;
    protected static String taskImportance;
    protected static String taskNotes;

    public AddCommand(String name, String startTime, String duration, String deadline, String recurrence, String importance, String notes) {
        taskName = name;
        taskStartTime = startTime;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
    }

    /**
     * Adds a task to the Tasks list.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addToList(taskName, taskStartTime,taskDuration, taskDeadline, taskRecurrence, taskImportance, taskNotes);
    }

}