package athena.task.taskfilter;

import athena.Importance;
import athena.task.Task;

import java.time.LocalDate;

public class ImportanceFilter extends TaskFilter {

    private Importance importance;

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
        boolean isTaskIncluded;
        if (importance == Importance.ALL) {
            isTaskIncluded = true;
        } else {
            isTaskIncluded = (task.getImportance() == importance);
        }
        return isTaskIncluded;
    }
}
