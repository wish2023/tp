package athena.task.taskfilter;

import athena.task.Task;
import athena.Forecast;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class ForecastFilter extends TaskFilter {

    private Forecast forecast;
    private LocalDate todayDate = LocalDate.now();

    public ForecastFilter(Forecast forecast) {
        this.forecast = forecast;
    }


    /**
     * Checks whether to include a task based on its date.
     *
     * @param task Task to check.
     * @return Whether the task should be included.
     */
    @Override
    public boolean isTaskIncluded(Task task) {
        boolean isTaskIncluded = false;
        if (forecast == Forecast.ALL) {
            isTaskIncluded = true;
        } else if (forecast == Forecast.TODAY) {
            LocalDate taskDate = task.getDate();
            isTaskIncluded = taskDate.equals(todayDate);
        } else if (forecast == Forecast.WEEK) {
            int currentWeekNumber = getWeekNumber(todayDate);
            LocalDate taskDate = task.getDate();
            int taskWeekNumber = getWeekNumber(taskDate);
            isTaskIncluded = (currentWeekNumber == taskWeekNumber);
        }
        return isTaskIncluded;
    }

    private static int getWeekNumber(LocalDate taskDate) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return taskDate.get(woy);
    }


}
