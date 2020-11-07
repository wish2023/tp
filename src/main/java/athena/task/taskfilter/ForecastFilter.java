package athena.task.taskfilter;

import athena.Forecast;
import athena.common.utils.DateUtils;
import athena.task.Task;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class ForecastFilter extends TaskFilter {

    private final Forecast forecast;
    private LocalDate filterDate = LocalDate.now();

    public ForecastFilter(Forecast forecast) {
        this.forecast = forecast;
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
            if (DateUtils.isDateIncluded(date, forecast)) {
                return true;
            }
        }
        return false;
    }
}
