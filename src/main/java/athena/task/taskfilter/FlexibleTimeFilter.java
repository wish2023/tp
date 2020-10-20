package athena.task.taskfilter;

import athena.task.Task;

public class FlexibleTimeFilter extends TaskFilter {

    private Boolean doesIncludeFlexibleTask;

    public FlexibleTimeFilter(Boolean doesIncludeFlexibleTask) {
        this.doesIncludeFlexibleTask = doesIncludeFlexibleTask;
    }

    /**
     * This is to check if the time for this task is flexible.
     * need to modify this later to access checkFlexible
     * currently it only checks for an empty getDate of the task
     *
     * @param task Task to check.
     * @return
     */
    @Override
    public boolean isTaskIncluded(Task task) {
        return task.isFlexible() ^ doesIncludeFlexibleTask;
    }

}

