package athena.timetable;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.exceptions.CommandException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static athena.common.utils.DateUtils.formatDate;
import static athena.timetable.TimetableTestsUtils.getThisWeekDates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableDrawerTest {

    /**
     * Tests that the timetable from 8am to 12pm is drawn correctly.
     */
    @Test
    void drawTimetable_start8End12_returnsCorrectlyDrawnTimetable() throws CommandException {
        TaskList taskList = getOutputTestTaskList();
        ArrayList<LocalDate> dates = getThisWeekDates();
        Timetable timetable = new Timetable(taskList, Importance.ALL, Forecast.ALL);
        TimetableDrawer timetableDrawer = new TimetableDrawer(timetable);
        String drawnTimetable = timetableDrawer.drawTimetable(dates, 8, 12);
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