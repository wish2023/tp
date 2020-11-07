package athena.task.taskfilter;

import athena.Importance;
import athena.task.Task;


public class ImportanceFilter extends TaskFilter {

    private final Importance importance;

    public ImportanceFilter(Importance importance) {
        this.importance = importance;
    }

    /**
     * Checks whether to include a task based on it's importance.
     *
     * @param task Task to check.
     * @return Whether the task should be included.
     */
    @Override
    public boolean isTaskIncluded(Task task) {
        if (importance == Importance.ALL) {
            return true;
        }
        return task.getImportance() == importance;
    }
}
