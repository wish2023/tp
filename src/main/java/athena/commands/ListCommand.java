package athena.commands;

import athena.TaskList;
import athena.Ui;

/**
 * Handles the list command.
 */
public class ListCommand extends Command {
    private String taskImportance;
    private String taskForecast;

    public ListCommand(String importance, String forecast) {
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
        TaskList tasksFiltered = taskList.getFilteredList(taskImportance, taskForecast);
        ui.printList(tasksFiltered.getTasks());
    }
}
