package athena;

import java.io.IOException;

public class EditCommand extends Commands {
    protected static int taskIndex;
    protected static String taskName;
    protected static String taskTime;
    protected static String taskDuration;
    protected static String taskDeadline;
    protected static String taskRecurrence;
    protected static String taskImportance;
    protected static String taskNotes;

    public EditCommand(int index, String name, String time, String duration, String deadline, String recurrence, String importance, String notes) {
        taskIndex = index;
        taskName = name;
        taskTime = time;
        taskDuration = duration;
        taskDeadline = deadline;
        taskRecurrence = recurrence;
        taskImportance = importance;
        taskNotes = notes;
    }

    /**
     * Edits a task from the taskList and
     * calls Ui to print edit message.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
         ui.editOutput();
    }
}
