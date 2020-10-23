package athena.task.taskfilter;

import athena.task.Task;
import athena.Forecast;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class ForecastFilter extends TaskFilter {

    private Forecast forecast;
    private LocalDate todayDate = LocalDate.now();

    public ForecastFilter(Forecast forecast) {
        this.forecast = forecast;
    }

    public Forecast getForecast() {
        return forecast;
    }


    /**
     * Checks whether to include a task based on its date.
     *
     * @param task Task to check.
     * @return Whether the task should be included.
     */
    @Override
    public boolean isTaskIncluded(Task task) {
        for (LocalDate date : task.getDates()) {
            if (isDateIncluded(date)) {
                return true;
            }
        }
        return false;
    }

    private static int getWeekNumber(LocalDate taskDate) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return taskDate.get(woy);
    }

    private boolean isDateIncluded(LocalDate taskDate) {
        boolean isDateIncluded;
        if (forecast == Forecast.ALL) {
            isDateIncluded = true;
        } else if (forecast == Forecast.WEEK) {
            int currentWeekNumber = getWeekNumber(todayDate);
            int taskWeekNumber = getWeekNumber(taskDate);
            isDateIncluded = (currentWeekNumber == taskWeekNumber);
        } else {
            isDateIncluded = taskDate.equals(todayDate);
        }
        return isDateIncluded;
    }

    public Task removeExcludedDates(Task task) {
        Task taskCopy = task.getClone();
        ArrayList<LocalDate> datesToDelete = new ArrayList<>();
        for (LocalDate date : taskCopy.getDates()) {
            if (!isDateIncluded(date)) {
                datesToDelete.add(date);
            }
        }
        for (LocalDate date : datesToDelete) {
            taskCopy.removeDate(date);
        }
        return taskCopy;
    }


}
