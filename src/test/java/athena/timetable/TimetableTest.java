package athena.timetable;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.TestSetup;
import athena.task.Task;
import athena.task.taskfilter.ForecastFilter;
import athena.task.taskfilter.ImportanceFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimetableTest {

    @Test
    void getTimetable_noFilter_returnsAllTasks() {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        TimetableDay day;

        date = LocalDate.parse("12-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 1", "4pm", "2 hrs", "6pm",
                "12-12-2020", Importance.HIGH, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("13-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 2", "4pm", "2 hrs", "6pm",
                "13-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("14-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 3", "4pm", "2 hrs", "6pm",
                "14-12-2020", Importance.LOW, "Tough assignment", 1));
        day.addTask(new Task("Assignment 4", "4pm", "2 hrs", "6pm",
                "14-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        day.addTask(new Task("Assignment 5", "4pm", "2 hrs", "6pm",
                "14-12-2020", Importance.HIGH, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("15-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 6", "4pm", "2 hrs", "6pm",
                "15-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        day.addTask(new Task("Assignment 7", "4pm", "2 hrs", "6pm",
                "15-12-2020", Importance.HIGH, "Tough assignment", 1));
        day.addTask(new Task("Assignment 8", "4pm", "2 hrs", "6pm",
                "15-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("16-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 9", "4pm", "2 hrs", "6pm",
                "16-12-2020", Importance.LOW, "Tough assignment", 1));
        day.addTask(new Task("Assignment 10", "4pm", "2 hrs", "6pm",
                "16-12-2020", Importance.MEDIUM, "Tough assignment", 1));
        days.add(day);

        TaskList taskList = TestSetup.getTestTaskList();
        Timetable timetable = new Timetable(taskList);

        assertTrue(areTimetablesSame(timetable, days));
    }

    @Test
    void getTimetable_highImportanceFilter_returnsHighImportanceTasks() {
        final ArrayList<TimetableDay> days = new ArrayList<TimetableDay>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date;
        TimetableDay day;

        date = LocalDate.parse("12-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 1", "4pm", "2 hrs", "6pm",
                "12-12-2020", Importance.HIGH, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("14-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 5", "4pm", "2 hrs", "6pm",
                "14-12-2020", Importance.HIGH, "Tough assignment", 1));
        days.add(day);

        date = LocalDate.parse("15-12-2020", formatter);
        day = new TimetableDay(date);
        day.addTask(new Task("Assignment 7", "4pm", "2 hrs", "6pm",
                "15-12-2020", Importance.HIGH, "Tough assignment", 1));
        days.add(day);

        TaskList taskList = TestSetup.getTestTaskList();
        Timetable timetable = new Timetable(taskList, new ImportanceFilter(Importance.HIGH),
                new ForecastFilter(Forecast.ALL));

        assertTrue(areTimetablesSame(timetable, days));
    }

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