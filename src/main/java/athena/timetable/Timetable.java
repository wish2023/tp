package athena.timetable;

import athena.TaskList;
import athena.task.Task;
import athena.task.taskfilter.ForecastFilter;
import athena.task.taskfilter.ImportanceFilter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Takes a TaskList to generate a timetable for the user.
 */
public class Timetable {
    private TaskList taskList;
    private ArrayList<TimetableDay> timetableDays;

    /**
     * Creates a timetable object from a TaskList object.
     * @param taskList Task list
     */
    public Timetable(TaskList taskList) {
        assert taskList != null;
        this.taskList = taskList;
        populateTimetable();
    }

    /**
     * Creates a timetable object from a TaskList, ImportanceFilter and ForecastFilter object.
     * @param taskList Task list
     * @param importanceFilter Filters tasks of a certain importance
     * @param forecastFilter Filters tasks based on forecast
     */
    public Timetable(TaskList taskList, ImportanceFilter importanceFilter, ForecastFilter forecastFilter) {
        assert taskList != null;
        this.taskList = taskList.getFilteredList(importanceFilter).getFilteredList(forecastFilter);
        populateTimetable();
    }

    /**
     * Getter for timetableDays.
     *
     * @return timetableDays.
     */
    public ArrayList<TimetableDay> getTimetableDays() {
        return timetableDays;
    }

    /**
     * Setter for timetableDays.
     */
    public void setTimetableDays(ArrayList<TimetableDay> timetableDays) {
        this.timetableDays = timetableDays;
    }

    /**
     * Populates the timetable, represented by a list of TimetableDays with the information from the task list.
     * For this version, we only populate the timetable with the tasks for this week (starting from Monday).
     */
    private void populateTimetable() {
        this.timetableDays = new ArrayList<TimetableDay>();

        TreeMap<LocalDate, TimetableDay> timetableDayMap = new TreeMap<LocalDate, TimetableDay>();

        for (Task task : taskList.getTasks()) {
            LocalDate date = task.getDate();
            assert date != null;

            TimetableDay timetableDay;
            if (timetableDayMap.containsKey(date)) {
                timetableDay = timetableDayMap.get(date);
            } else {
                timetableDay = new TimetableDay(date);
                timetableDayMap.put(date, timetableDay);
            }

            timetableDay.addTask(task);
        }

        for (LocalDate key : timetableDayMap.keySet()) {
            timetableDays.add(timetableDayMap.get(key));
        }
    }

    /**
     * Generates a string to show the user the timetable.
     *
     * @return A string representing the timetable.
     */
    @Override
    public String toString() {
        String message = "";
        for (TimetableDay timetableDay : timetableDays) {
            message += timetableDay;
            message += "\n";
        }
        return message;
    }
}
