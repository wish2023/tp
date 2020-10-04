package athena;

/**
 * Handles the list command.
 */
public class ListCommand extends Commands {
    protected static String taskForecast;
    protected static String taskImportance;

    public ListCommand(String forecast, String importance) {
        taskForecast = forecast;
        taskImportance = importance;
    }
    /**
     * Calls Ui to print TaskList.
     * @param taskList TaskList
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.list(taskList);
    }
}
