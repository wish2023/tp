package athena.timetable;

import athena.task.taskfilter.TaskFilter;
import athena.tasklist.TaskList;

import java.util.ArrayList;

/**
 * Takes a TaskList to generate a timetable for the user.
 */
public class Timetable {
    private TaskList taskList;

    public Timetable(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Generates a list of tasks based on the user's requested filter.
     * The tasks will be categorized by their dates using the TimetableDay class.
     *
     * @param taskFilters Criteria to filter the list of tasks.
     * @return A list of tasks stored in TimetableDay objects.
     */
    public ArrayList<TimetableDay> getTasksByFilters(ArrayList<TaskFilter> taskFilters) {
        // TODO: Implement

        return null;
    }

}
