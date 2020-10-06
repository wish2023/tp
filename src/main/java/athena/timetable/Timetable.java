package athena.timetable;

import athena.task.Task;
import athena.task.taskfilter.TaskFilter;
import athena.tasklist.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 * Takes a TaskList to generate a timetable for the user.
 */
public class Timetable {
    private TaskList taskList;
    private ArrayList<TimetableDay> timetableDays;

    public Timetable(TaskList taskList) {
        this.taskList = taskList;
        populateTimetable();
    }

    /**
     * Populates the timetable, represented by a list of TimetableDays with the information from the task list.
     * For this version, we only populate the timetable with the tasks for this week (starting from Monday).
     */
    private void populateTimetable() {
        this.timetableDays = new ArrayList<TimetableDay>();

        TreeMap<LocalDate, TimetableDay> timetableDayMap = new TreeMap<LocalDate, TimetableDay>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HHmm");

        for (Task task : taskList.getTasks()) {
            LocalDate date = LocalDate.parse(task.recurrence, formatter);

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
     * Gets the timetable for this week (starting from Monday).
     *
     * @return A list of TimetableDay objects representing the timetable for this week.
     */
    public ArrayList<TimetableDay> getTimetable() {
        return timetableDays;
    }

    /**
     * Gets a list of tasks based on the user's requested filter.
     * The tasks will be categorized by their dates using the TimetableDay class.
     * For days without any tasks, a TimetableDay with an empty TaskList
     * will be returned.
     *
     * @param taskFilters Criteria to filter the list of tasks.
     * @return A list of tasks stored in TimetableDay objects.
     */
    public ArrayList<TimetableDay> getTasksByFilters(ArrayList<TaskFilter> taskFilters) {
        // TODO: Implement this

        return timetableDays;
    }

}
