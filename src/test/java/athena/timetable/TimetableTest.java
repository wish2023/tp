package athena.timetable;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.exceptions.CommandException;
import athena.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static athena.common.utils.DateUtils.formatDate;
import static athena.timetable.TimetableTestsUtils.getThisWeekDates;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test methods of Timetable.
 */
class TimetableTest {


    /**
     * Checks if the timetable returns today's task when forecast is DAY.
     */
    @Test
    void getTimetable_dayFilter_returnsTodayTasks() throws CommandException {
        final ArrayList<TimetableDay> days = new ArrayList<>();
        ArrayList<LocalDate> dates = getThisWeekDates();
        TimetableDay day;

        day = new TimetableDay(dates.get(0));
        day.addTask(new Task("Assignment 1", "1600", "2", "01-01-2021",
                formatDate(dates.get(0)), Importance.HIGH, "Tough assignment", 0, false));
        days.add(day);

        TaskList taskList = getDaysTestTaskList();
        Timetable timetable = new Timetable(taskList, Importance.ALL, Forecast.DAY);

        assertEquals(timetable.getTimetableDays(), days);
    }

    /**
     * Checks if the timetable returns all tasks with no filter.
     */
    @Test
    void getTimetable_noFilter_returnsWeekTasks() throws CommandException {
        final ArrayList<TimetableDay> days = new ArrayList<>();
        ArrayList<LocalDate> dates = getThisWeekDates();
        TimetableDay day;

        day = new TimetableDay(dates.get(0));
        day.addTask(new Task("Assignment 1", "1600", "2", "01-01-2021",
                formatDate(dates.get(0)), Importance.HIGH, "Tough assignment", 0, false));
        days.add(day);

        day = new TimetableDay(dates.get(1));
        day.addTask(new Task("Assignment 2", "1600", "2", "01-01-2021",
                formatDate(dates.get(1)), Importance.MEDIUM, "Tough assignment", 1, false));
        days.add(day);

        day = new TimetableDay(dates.get(2));
        day.addTask(new Task("Assignment 3", "1000", "2", "01-01-2021",
                formatDate(dates.get(2)), Importance.LOW, "Tough assignment", 2, false));
        day.addTask(new Task("Assignment 4", "1300", "2", "01-01-2021",
                formatDate(dates.get(2)), Importance.MEDIUM, "Tough assignment", 3, false));
        day.addTask(new Task("Assignment 5", "1600", "2", "01-01-2021",
                formatDate(dates.get(2)), Importance.HIGH, "Tough assignment", 4, false));
        days.add(day);

        day = new TimetableDay(dates.get(3));
        day.addTask(new Task("Assignment 6", "1600", "2", "01-01-2021",
                formatDate(dates.get(3)), Importance.MEDIUM, "Tough assignment", 5, false));
        day.addTask(new Task("Assignment 7", "1900", "2", "01-01-2021",
                formatDate(dates.get(3)), Importance.HIGH, "Tough assignment", 6, false));
        day.addTask(new Task("Assignment 8", "2100", "2", "01-01-2021",
                formatDate(dates.get(3)), Importance.MEDIUM, "Tough assignment", 7, false));
        days.add(day);

        day = new TimetableDay(dates.get(4));
        day.addTask(new Task("Assignment 9", "1600", "2", "01-01-2021",
                formatDate(dates.get(4)), Importance.LOW, "Tough assignment", 8, false));
        day.addTask(new Task("Assignment 10", "1300", "2", "01-01-2021",
                formatDate(dates.get(4)), Importance.MEDIUM, "Tough assignment", 9, false));
        days.add(day);

        TaskList taskList = getDaysTestTaskList();
        Timetable timetable = new Timetable(taskList);

        assertEquals(timetable.getTimetableDays(), days);
    }

    /**
     * Checks if the timetable returns only high importance tasks with the high importance filter.
     */
    @Test
    void getTimetable_highImportanceFilter_returnsHighImportanceTasks() throws CommandException {
        final ArrayList<TimetableDay> days = new ArrayList<>();
        ArrayList<LocalDate> dates = getThisWeekDates();
        TimetableDay day;

        day = new TimetableDay(dates.get(0));
        day.addTask(new Task("Assignment 1", "1600", "2", "01-01-2021",
                formatDate(dates.get(0)), Importance.HIGH, "Tough assignment", 0, false));
        days.add(day);

        day = new TimetableDay(dates.get(2));
        day.addTask(new Task("Assignment 5", "1600", "2", "01-01-2021",
                formatDate(dates.get(2)), Importance.HIGH, "Tough assignment", 4, false));
        days.add(day);

        day = new TimetableDay(dates.get(3));
        day.addTask(new Task("Assignment 7", "1900", "2", "01-01-2021",
                formatDate(dates.get(3)), Importance.HIGH, "Tough assignment", 6, false));
        days.add(day);

        TaskList taskList = getDaysTestTaskList();
        Timetable timetable = new Timetable(taskList, Importance.HIGH, Forecast.ALL);

        assertEquals(timetable.getTimetableDays(), days);
    }

    /**
     * Gets a task list filled with default tasks.
     *
     * @return Task list of default tasks
     */
    public static TaskList getDaysTestTaskList() throws CommandException {
        ArrayList<LocalDate> dates = getThisWeekDates();

        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", formatDate(dates.get(0)),
                Importance.HIGH, "Tough assignment", false);

        taskList.addTask(1, "Assignment 2", "1600", "2", "01-01-2021", formatDate(dates.get(1)),
                Importance.MEDIUM, "Tough assignment", false);

        taskList.addTask(2, "Assignment 3", "1000", "2", "01-01-2021", formatDate(dates.get(2)),
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(3, "Assignment 4", "1300", "2", "01-01-2021", formatDate(dates.get(2)),
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "01-01-2021", formatDate(dates.get(2)),
                Importance.HIGH, "Tough assignment", false);

        taskList.addTask(5, "Assignment 6", "1600", "2", "01-01-2021", formatDate(dates.get(3)),
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(6, "Assignment 7", "1900", "2", "01-01-2021", formatDate(dates.get(3)),
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "2100", "2", "01-01-2021", formatDate(dates.get(3)),
                Importance.MEDIUM, "Tough assignment", false);

        taskList.addTask(8, "Assignment 9", "1600", "2", "01-01-2021", formatDate(dates.get(4)),
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1300", "2", "01-01-2021", formatDate(dates.get(4)),
                Importance.MEDIUM, "Tough assignment", false);

        return taskList;
    }

    private TaskList getOutputTestTaskList() throws CommandException {
        ArrayList<LocalDate> dates = getThisWeekDates();

        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "0800", "2", "01-01-2021", formatDate(dates.get(1)),
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask("Tutorial 2", "0900", "2", "01-01-2021", formatDate(dates.get(2)),
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask("OP 3", "1000", "2", "01-01-2021", formatDate(dates.get(3)),
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask("TP 4", "1100", "2", "01-01-2021", formatDate(dates.get(4)),
                Importance.HIGH, "Tough assignment", false);

        return taskList;
    }
}
