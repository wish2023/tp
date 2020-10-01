package athena.timetable;

import athena.tasklist.TaskList;

import java.util.Date;

/**
 * Represents a day in the timetable.
 * Contains the date and the list of tasks, which is a subset of all the tasks
 * that the user registered into the program.
 */
public class TimetableDay {
    private Date date;
    private TaskList taskList;

    public TimetableDay(Date date, TaskList taskList) {

    }

    /**
     * Generates a string containing the date and list of tasks that is to
     * be printed to the user.
     *
     * @return A string containing the date and list of tasks.
     */
    @Override
    public String toString() {
        // TODO: Generate a string containing the date and the tasks
        return "";
    }
}
