package athena;

import java.io.IOException;

/**
 * Handles adding tasks to the TaskList.
 */
public class AddCommand extends Commands {
    protected static String taskName;
    protected static String taskTime;
    protected static String taskDuration;
    protected static String taskDeadline;
    protected static String taskRecurrence;
    protected static String taskImportance;
    protected static String taskNotes;

    public AddCommand(String name, String time, String duration, String deadline, String recurrence, String importance, String notes) {
        taskName = name;
        taskTime = time;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
    }

    /**
     * Adds a task to the taskList based on command and
     * calls Ui to print add message.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
           ui.addOutput(task.toString(), t.taskList.size());
    }

}