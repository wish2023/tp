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
    void deleteTask_validTaskIndex_correctTaskDeleted() throws TaskNotFoundException {
        Task expectedTask = new Task("Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                "Refer to slides", 12);
        taskList.addTask(expectedTask);
        Task actualTask = taskList.deleteTask(12);
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void editTask_givenAttributes_attributeChanged() throws TaskNotFoundException {
        int index = 0;
        Task task = new Task("Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                "Refer to slides", index);
        taskList.addTask(task);

        Task expectedTask = new Task("Assignment2", "1200",
                "4 hours", "16-11-2020", "13-10-2020", Importance.LOW,
                "I have changed", index);

        taskList.editTask(index, "Assignment2", "1200",
                "4 hours", "16-11-2020", "13-10-2020", Importance.LOW,
                "I have changed");


        assertEquals(taskList.getTaskFromNumber(index), expectedTask);
    }

    @Test
    // Filter list using high, low, medium importance
    // Filter list using today, week, all forecast
    // TODO ^^
    void getFilteredList_highImportance_returnTasksWithHighImportance() {
        TaskList expectedTaskList = getExpectedImportance(Importance.HIGH);
        ImportanceFilter highFilter = new ImportanceFilter(Importance.HIGH);
        assertEquals(taskList.getFilteredList(highFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_mediumImportance_returnTasksWithMediumImportance() {
        TaskList expectedTaskList = getExpectedImportance(Importance.MEDIUM);
        ImportanceFilter mediumFilter = new ImportanceFilter(Importance.MEDIUM);
        assertEquals(taskList.getFilteredList(mediumFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_lowImportance_returnTasksWithLowImportance() {
        TaskList expectedTaskList = getExpectedImportance(Importance.LOW);
        ImportanceFilter lowFilter = new ImportanceFilter(Importance.LOW);
        assertEquals(taskList.getFilteredList(lowFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_allForecast_returnAllTasks() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.ALL);
        ForecastFilter allFilter = new ForecastFilter(Forecast.ALL);
        assertEquals(taskList.getFilteredList(allFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_weekForecast_returnTasksForWeek() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.WEEK);
        ForecastFilter weekFilter = new ForecastFilter(Forecast.WEEK);
        assertEquals(taskList.getFilteredList(weekFilter), expectedTaskList);
    }

    @Test
    void getFilteredList_todayForecast_returnTasksForDay() {
        TaskList expectedTaskList = getExpectedForecast(Forecast.TODAY);
        ForecastFilter todayFilter = new ForecastFilter(Forecast.TODAY);
        assertEquals(taskList.getFilteredList(todayFilter), expectedTaskList);
    }

    private TaskList getExpectedImportance(Importance importance) {
        TaskList taskList = new TaskList();
        if (importance == Importance.HIGH) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                    "Refer to slides", 0));
        } else if (importance == Importance.MEDIUM) {
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.MEDIUM,
                    "Refer to slides", 1));
        } else if (importance == Importance.LOW) {
            taskList.addTask(new Task("tres", "1100",
                    "2 hours", "16-09-2020", "13-11-2020", Importance.LOW,
                    "Refer to slides", 2));
        }
        return taskList;
    }

    private TaskList getExpectedForecast(Forecast forecast) {
        TaskList taskList = new TaskList();
        if (forecast == Forecast.ALL) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                    "Refer to slides", 0));
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.MEDIUM,
                    "Refer to slides", 1));
            taskList.addTask(new Task("tres", "1100",
                    "2 hours", "16-09-2020", "13-11-2020", Importance.LOW,
                    "Refer to slides", 2));
        } else if (forecast == Forecast.WEEK) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                    "Refer to slides", 0));
            taskList.addTask(new Task("dos", "1100",
                    "2 hours", "16-09-2020", "15-10-2020", Importance.MEDIUM,
                    "Refer to slides", 1));
        } else if (forecast == Forecast.TODAY) {
            taskList.addTask(new Task("uno", "1100",
                    "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                    "Refer to slides", 0));
        }
        return taskList;
    }


    private void addTasks() {
        int index = 0;
        taskList.addTask(new Task("uno", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                "Refer to slides", index++));
        taskList.addTask(new Task("dos", "1100",
                "2 hours", "16-09-2020", "15-10-2020", Importance.MEDIUM,
                "Refer to slides", index++));
        taskList.addTask(new Task("tres", "1100",
                "2 hours", "16-09-2020", "13-11-2020", Importance.LOW,
                "Refer to slides", index++));
    }
}