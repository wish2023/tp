package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.task.taskfilter.ImportanceFilter;
import athena.task.taskfilter.TaskFilter;
import athena.timetable.Timetable;

import java.util.Objects;

/**
 * Handles the list command.
 */
public class ListCommand extends Command {
    private Importance taskImportance;
    private String taskForecast;

    public ListCommand(Importance importance, String forecast) {
        taskImportance = importance;
        taskForecast = forecast;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListCommand)) {
            return false;
        }
        ListCommand that = (ListCommand) o;
        return taskImportance == that.taskImportance && Objects.equals(taskForecast, that.taskForecast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskImportance, taskForecast);
    }
}
