package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.task.taskfilter.ImportanceFilter;
import athena.task.taskfilter.TaskFilter;
import athena.timetable.Timetable;

/**
 * Handles the list command.
 */
public class ListCommand extends Command {
    private Importance taskImportance;

    public ListCommand(Importance importance) {
        taskImportance = importance;
    }

    /**
     * Calls TaskList to filter the list based on importance and
     * calls Ui to print the list of tasks.
     *
     * @param taskList Tasks List
     * @param ui       Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ImportanceFilter importanceFilter = new ImportanceFilter(taskImportance);
        Timetable timetable = new Timetable(taskList, importanceFilter);
        ui.printTimetable(timetable);
    }
}
