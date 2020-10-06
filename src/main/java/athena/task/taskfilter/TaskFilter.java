package athena.task.taskfilter;

import athena.task.Task;

/**
 * Contains information about how to filter a task.
 * This is used by athena.timetable.Timetable to filter out
 * tasks based on the user's request.
 */
public abstract class TaskFilter {
    /**
     * Should be overridden by children classes to filter a task based on certain
     * criteria.
     *
     * @param task Task to check.
     * @return Whether the task should be included.
     */
    public abstract boolean filterTask(Task task);
}
