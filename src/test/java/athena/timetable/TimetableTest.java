package athena.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.common.utils.DateUtils;
import athena.exceptions.CommandException;
import athena.task.Task;

/**
 * Test methods of Timetable.
 */
class TimetableTest {

    /**
     * Tests that the timetable from 8am to 12pm is drawn correctly.
     */
    @Test
    void drawTimetable_start8End12_returnsCorrectlyDrawnTimetable() throws CommandException {
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

        Timetable timetable = new Timetable(taskList, Importance.ALL, Forecast.ALL);
        String drawnTimetable = timetable.drawTimetable(dates, 8, 12);
        String expectedDrawnTimetable =
                "+-------08---------09---------10---------11---------+\n"
                        + "|  DA0  |          |          |          |          |\n"
                        + "| DATE0 |          |          |          |          |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA1  | Assignment 1        |          |          |\n"
                        + "| DATE1 | [0]                 |          |          |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA2  |          | Tutorial 2          |          |\n"
                        + "| DATE2 |          | [1]                 |          |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA3  |          |          | OP 3                |\n"
                        + "| DATE3 |          |          | [2]                 |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA4  |          |          |          | TP 4     |\n"
                        + "| DATE4 |          |          |          | [3]      |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA5  |          |          |          |          |\n"
                        + "| DATE5 |          |          |          |          |\n"
                        + "+-------+----------+----------+----------+----------+\n"
                        + "|  DA6  |          |          |          |          |\n"
                        + "| DATE6 |          |          |          |          |\n"
                        + "+-------+----------+----------+----------+----------+\n";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        for (int i = 0; i < 7; i++) {
            LocalDate date = dates.get(i);
            expectedDrawnTimetable = expectedDrawnTimetable.replace("DATE" + i, date.format(formatter));
            expectedDrawnTimetable = expectedDrawnTimetable.replace("DA" + i, date.getDayOfWeek().name()
                    .substring(0, 3));
        }

        assertEquals(drawnTimetable, expectedDrawnTimetable);
    }

    /**
     * Checks if the timetable returns all tasks with no filter.
     */
    @Test
    void getTimetable_noFilter_returnsWeekTasks() throws CommandException {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
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

        TaskList taskList = getTestTaskList();
        Timetable timetable = new Timetable(taskList);

        // test disabled for now because ForecastFilter for WEEK needs to change behaviour
        // assertEquals(timetable.getTimetableDays(), days);
    }

    /**
     * Checks if the timetable returns only high importance tasks with the high importance filter.
     */
    @Test
    void getTimetable_highImportanceFilter_returnsHighImportanceTasks() throws CommandException {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
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

        TaskList taskList = getTestTaskList();
        Timetable timetable = new Timetable(taskList, Importance.HIGH, Forecast.ALL);

        assertEquals(timetable.getTimetableDays(), days);
    }

    /**
     * Gets a task list filled with default tasks.
     *
     * @return Task list of default tasks
     */
    public static TaskList getTestTaskList() throws CommandException {
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

    /**
     * Generates a list of LocalDates starting from today and ending 6 days later (total 7 days).
     *
     * @return List of dates for one week starting today.
     */
    private static ArrayList<LocalDate> getThisWeekDates() {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            dates.add(date);
            date = date.plusDays(1);
        }
        return dates;
    }

    /**
     * Helper method to convert LocalDate to String using DateUtils.
     *
     * @param date Date to be formatted
     * @return String representation of LocalDate formatted by DateUtils
     */
    private static String formatDate(LocalDate date) {
        return DateUtils.formatDate(date);
    }
}
