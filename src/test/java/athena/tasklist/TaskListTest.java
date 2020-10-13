package athena.tasklist;

import athena.Forecast;
import athena.Importance;
import athena.TaskList;
import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import athena.task.taskfilter.ForecastFilter;
import athena.task.taskfilter.ImportanceFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        addTasks();
    }

    @Test
    // Check for normal deletion, and then check for a deletion with index out of range
    // TODO ^^
    void deleteTask() {

    }

    @Test
    void editTask_successfully() throws TaskNotFoundException {
        int index = 0;
        Task task = new Task("Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                "Refer to slides", index);
        taskList.addTask(task);

        Task expectedTask = new Task("Assignment2", "1200",
                "4 hours", "16-11-2020", "13-10-2020", Importance.valueOf("low".toUpperCase()),
                "I have changed", index);

        taskList.editTask(index, "Assignment2", "1200",
                "4 hours", "16-11-2020", "13-10-2020", Importance.valueOf("low".toUpperCase()),
                "I have changed");


        assertEquals(taskList.at(index), expectedTask);
    }

    @Test
    // Filter list using high, low, medium importance
    // Filter list using today, week, all forecast
    // TODO ^^
    void getFilteredList_high_importance_successfully() {
        TaskList expectedTaskList = getExpectedImportance(Importance.HIGH);
        ImportanceFilter highFilter = new ImportanceFilter(Importance.HIGH);
        assertEquals(taskList.getFilteredList(highFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_medium_importance_successfully() {
        TaskList expectedTaskList = getExpectedImportance(Importance.MEDIUM);
        ImportanceFilter mediumFilter = new ImportanceFilter(Importance.MEDIUM);
        assertEquals(taskList.getFilteredList(mediumFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_low_importance_successfully() {
        TaskList expectedTaskList = getExpectedImportance(Importance.LOW);
        ImportanceFilter lowFilter = new ImportanceFilter(Importance.LOW);
        assertEquals(taskList.getFilteredList(lowFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_all_forecast_successfully() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.ALL);
        ForecastFilter allFilter = new ForecastFilter(Forecast.ALL);
        assertEquals(taskList.getFilteredList(allFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_week_forecast_successfully() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.WEEK);
        ForecastFilter weekFilter = new ForecastFilter(Forecast.WEEK);
        assertEquals(taskList.getFilteredList(weekFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_today_forecast_successfully() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.TODAY);
        ForecastFilter todayFilter = new ForecastFilter(Forecast.TODAY);
        assertEquals(taskList.getFilteredList(todayFilter), expectedTaskList);
    }

    private TaskList getExpectedImportance(Importance importance) {
        TaskList taskList = new TaskList();
        if (importance == Importance.HIGH) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                    "Refer to slides", 0));
        } else if (importance == Importance.MEDIUM) {
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.valueOf("medium".toUpperCase()),
                    "Refer to slides", 1));
        } else if (importance == Importance.LOW) {
            taskList.addTask(new Task("tres", "1100",
                    "2 hours", "16-09-2020", "13-11-2020", Importance.valueOf("low".toUpperCase()),
                    "Refer to slides", 2));
        }
        return taskList;
    }

    private TaskList getExpectedForecast(Forecast forecast) {
        TaskList taskList = new TaskList();
        if (forecast == Forecast.ALL) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                    "Refer to slides", 0));
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.valueOf("medium".toUpperCase()),
                    "Refer to slides", 1));
            taskList.addTask(new Task("tres", "1100",
                    "2 hours", "16-09-2020", "13-11-2020", Importance.valueOf("low".toUpperCase()),
                    "Refer to slides", 2));
        } else if (forecast == Forecast.WEEK) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                    "Refer to slides", 0));
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.valueOf("medium".toUpperCase()),
                    "Refer to slides", 1));
        } else if (forecast == Forecast.TODAY) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                    "Refer to slides", 0));
        }
        return taskList;
    }


    private void addTasks() {
        int index = 0;
        taskList.addTask(new Task("uno", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                "Refer to slides", index++));
        taskList.addTask(new Task("dos", "1100",
                "2 hours", "16-09-2020", "15-10-2020", Importance.valueOf("medium".toUpperCase()),
                "Refer to slides", index++));
        taskList.addTask(new Task("tres", "1100",
                "2 hours", "16-09-2020", "13-11-2020", Importance.valueOf("low".toUpperCase()),
                "Refer to slides", index++));
    }
}