package athena.timetable;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.task.Task;
import athena.task.taskfilter.ForecastFilter;
import athena.task.taskfilter.ImportanceFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test methods of Timetable.
 */
class TimetableTest {

    TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(1, "Assignment 2", "1600", "2", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment");
        taskList.addTask(2, "Assignment 3", "1600", "2", "6pm", "14-12-2020",
                Importance.LOW, "Tough assignment");
        taskList.addTask(3, "Assignment 4", "1600", "2", "6pm", "14-12-2020",
                Importance.MEDIUM, "Tough assignment");
        taskList.addTask(4, "Assignment 5", "1600", "2", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(5, "Assignment 6", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment");
        taskList.addTask(6, "Assignment 7", "1600", "2", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(7, "Assignment 8", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment");
        taskList.addTask(8, "Assignment 9", "1600", "2", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment");
        taskList.addTask(9, "Assignment 10", "1600", "2", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment");
    }

    /**
     * Checks if the timetable returns all tasks with no filter.
     */
    @Test
    void getTimetable_noFilter_returnsAllTasks() {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        TimetableDay day;

        date = LocalDate.parse("12-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 1", "1600", "2", "6pm",
                "12-12-2020", Importance.HIGH, "Tough assignment", 0));
        days.add(day);

        date = LocalDate.parse("13-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 2", "1600", "2", "6pm",
                "13-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("14-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 3", "1600", "2", "6pm",
                "14-12-2020", Importance.LOW, "Tough assignment", 2));
        day.addTask(new Task("Assignment 4", "1600", "2", "6pm",
                "14-12-2020", Importance.MEDIUM, "Tough assignment", 3));
        day.addTask(new Task("Assignment 5", "1600", "2", "6pm",
                "14-12-2020", Importance.HIGH, "Tough assignment", 4));
        days.add(day);

        date = LocalDate.parse("15-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 6", "1600", "2", "6pm",
                "15-12-2020", Importance.MEDIUM, "Tough assignment", 5));
        day.addTask(new Task("Assignment 7", "1600", "2", "6pm",
                "15-12-2020", Importance.HIGH, "Tough assignment", 6));
        day.addTask(new Task("Assignment 8", "1600", "2", "6pm",
                "15-12-2020", Importance.MEDIUM, "Tough assignment", 7));
        days.add(day);

        date = LocalDate.parse("16-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 9", "1600", "2", "6pm",
                "16-12-2020", Importance.LOW, "Tough assignment", 8));
        day.addTask(new Task("Assignment 10", "1600", "2", "6pm",
                "16-12-2020", Importance.MEDIUM, "Tough assignment", 9));
        days.add(day);

        Timetable timetable = new Timetable(taskList);

        assertTrue(areTimetablesSame(timetable, days));
    }

    /**
     * Checks if the timetable returns only high importance tasks with the high importance filter.
     */
    @Test
    void getTimetable_highImportanceFilter_returnsHighImportanceTasks() {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        TimetableDay day;

        date = LocalDate.parse("12-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 1", "1600", "2", "6pm",
                "12-12-2020", Importance.HIGH, "Tough assignment", 0));
        days.add(day);

        date = LocalDate.parse("14-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 5", "1600", "2", "6pm",
                "14-12-2020", Importance.HIGH, "Tough assignment", 4));
        days.add(day);

        date = LocalDate.parse("15-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 7", "1600", "2", "6pm",
                "15-12-2020", Importance.HIGH, "Tough assignment", 6));
        days.add(day);

        Timetable timetable = new Timetable(taskList, new ImportanceFilter(Importance.HIGH),
                new ForecastFilter(Forecast.ALL));

        assertTrue(areTimetablesSame(timetable, days));
    }

    /**
     * Checks if two timetables are the same.
     * @param timetable Pre-created timetable
     * @param days ArrayList of days
     * @return True or false depending if the two timetables are the same
     */
    private boolean areTimetablesSame(Timetable timetable, ArrayList<TimetableDay> days) {
        ArrayList<TimetableDay> timetableDays = timetable.getTimetableDays();

        if (timetableDays.size() != days.size()) {
            return false;
        }

        for (int i = 0; i < timetableDays.size(); ++i) {
            ArrayList<Task> tasks1 = timetableDays.get(i).getTaskList().getTasks();
            ArrayList<Task> tasks2 = days.get(i).getTaskList().getTasks();

            if (tasks1.size() != tasks2.size()) {
                return false;
            }

            for (int j = 0; j < tasks1.size(); ++j) {
                if (!tasks1.get(j).equals(tasks2.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

}