package athena.timetable;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.common.utils.DateUtils;
import athena.task.Task;
import athena.task.taskfilter.ForecastFilter;
import athena.task.taskfilter.ImportanceFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Takes a TaskList to generate a timetable for the user.
 */
public class Timetable {
    public static final int ATHENA_WAKE_UP_HOUR = 8;
    public static final int ATHENA_SLEEP_HOUR = 24;

    private final TaskList taskList;
    private ArrayList<TimetableDay> timetableDays;
    private TreeMap<LocalDate, TimetableDay> timetableDayMap;

    private final Forecast forecast;

    /**
     * Creates a timetable object from a TaskList object.
     *
     * @param taskList Task list
     */
    public Timetable(TaskList taskList) {
        this(taskList, Importance.ALL, Forecast.WEEK);
    }

    /**
     * Creates a timetable object from a TaskList, ImportanceFilter and ForecastFilter object.
     *
     * @param taskList   Task list
     * @param importance To filter tasks of a certain importance
     * @param forecast   To filter tasks based on given forecast
     */
    public Timetable(TaskList taskList, Importance importance, Forecast forecast) {
        assert taskList != null;
        this.taskList = taskList.getFilteredList(new ImportanceFilter(importance))
                .getFilteredList(new ForecastFilter(forecast));
        this.forecast = forecast;
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

    public TreeMap<LocalDate, TimetableDay> getTimetableDayMap() {
        return timetableDayMap;
    }

    /**
     * Populates the timetable, represented by a list of TimetableDays with the information from the task list.
     * For this version, we only populate the timetable with the tasks for this week (starting from Monday).
     */
    private void populateTimetable() {
        this.timetableDays = new ArrayList<>();

        timetableDayMap = new TreeMap<>();

        for (Task task : taskList.getTasks()) {
            ArrayList<LocalDate> dates = task.getDates();
            for (LocalDate date : dates) {
                assert date != null;
                if (!DateUtils.isDateIncluded(date, forecast)) {
                    continue;
                }

                TimetableDay timetableDay;
                if (timetableDayMap.containsKey(date)) {
                    timetableDay = timetableDayMap.get(date);
                } else {
                    timetableDay = new TimetableDay(date);
                    timetableDayMap.put(date, timetableDay);
                }

                timetableDay.addTask(task);
            }
        }

        for (LocalDate key : timetableDayMap.keySet()) {
            timetableDays.add(timetableDayMap.get(key));
        }
    }

    /**
     * Generates a string containing the user's tasks, separated by their date of occurrence,
     * according to the list of dates given.
     *
     * @param dates Dates to include in the output.
     * @return A list of tasks separated by date.
     */
    private String getTaskListString(ArrayList<LocalDate> dates) {
        StringBuilder list = new StringBuilder("Your task list: \n");
        for (LocalDate date : dates) {
            if (timetableDayMap.containsKey(date)) {
                list.append(timetableDayMap.get(date));
                list.append("\n");
            }
        }
        return list.toString().trim();
    }

    /**
     * Generates a string to show the user the timetable and list of tasks.
     *
     * @return A string representing the timetable and list of tasks.
     */
    @Override
    public String toString() {
        ArrayList<LocalDate> dates = DateUtils.getDatesBasedOnForecast(forecast);

        TimetableDrawer timetableDrawer = new TimetableDrawer(this);
        String timetableString = timetableDrawer.drawTimetable(dates, ATHENA_WAKE_UP_HOUR, ATHENA_SLEEP_HOUR);

        String taskListString = getTaskListString(dates);

        String output = timetableString + taskListString;
        return output + "\n";
    }
}
