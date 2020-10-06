package athena;

/**
 * Handles the list command.
 */
public class ListCommand extends Command {
    private String taskImportance;

    public ListCommand(String importance) {
        taskImportance = importance;
    }
    /**
     * Calls TaskList to filter the list based on importance and
     * calls Ui to print the list of tasks.
     * @param taskList Tasks List
     * @param ui Ui
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        TaskList tasksFiltered = taskList.getFilteredList(taskImportance);
        ui.printList(tasksFiltered.getTasks());
    }
}
