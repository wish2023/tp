package athena.task.taskfilter;

import athena.task.Task;

import java.time.LocalDate;

public class DayFilter extends TaskFilter {

    private LocalDate filterDate;

    public DayFilter(LocalDate filterDate) {
        this.filterDate = filterDate;
    }

    @Override
    public boolean isTaskIncluded(Task task) {
        for (LocalDate date : task.getDates()) {
            if (date.equals(filterDate)) {
                return true;
            }
        }
        return false;
    }
}
