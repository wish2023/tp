package athena.task.taskfilter;

import athena.Forecast;
import athena.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class ForecastFilter extends TaskFilter {

    private final Forecast forecast;
    private LocalDate filterDate = LocalDate.now();

    public ForecastFilter(Forecast forecast) {
        this.forecast = forecast;
    }

    public ForecastFilter(LocalDate date) {
        this.forecast = Forecast.DAY;
        this.filterDate = date;
    }

    public void setDate(LocalDate filterDate) {
        this.filterDate = filterDate;
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

    private boolean isDateIncluded(LocalDate taskDate) {
        if (forecast == Forecast.ALL) {
            return true;
        } else if (forecast == Forecast.WEEK) {
            LocalDate oneWeekLater = LocalDate.now().plusWeeks(1);
            return oneWeekLater.compareTo(taskDate) > 0 && LocalDate.now().compareTo(taskDate) <= 0;
        } else {
            return taskDate.equals(filterDate);
        }
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
