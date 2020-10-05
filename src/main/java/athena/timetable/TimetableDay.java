package athena.timetable;

import athena.tasklist.TaskList;

import java.time.LocalDate;
import java.util.Date;

/**
 * Represents a day in the timetable.
 * Contains the date and the list of tasks, which is a subset of all the tasks
 * that the user registered into the program.
 */
public class TimetableDay {
    private LocalDate date;
    private TaskList taskList;

    /**
     * Initializes the object with the date and an empty task list.
     *
     * @param date Date represented by this object.
     */
    public TimetableDay(LocalDate date) {
        this.date = date;
        this.taskList = new TaskList();
    }

    /**
     * Initializes the object with the date and a task list.
     *
     * @param date     Date represented by this object.
     * @param taskList Task list for this day.
     */
    public TimetableDay(LocalDate date, TaskList taskList) {
        this.date = date;
        this.taskList = taskList;
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
