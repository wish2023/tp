package athena.tasklist;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    void deleteTask_validTaskIndex_correctTaskDeleted() throws TaskNotFoundException {
        Task expectedTask = new Task("Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                "Refer to slides", 0);
        taskList.addTask(expectedTask);
        Task actualTask = taskList.deleteTask(0);
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void editTask() throws TaskNotFoundException {
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


        assertEquals(taskList.getTaskFromNumber(index), expectedTask);
    }

    @Test
    // Filter list using high, low, medium importance
    // Filter list using today, week, all forecast
    // TODO ^^
    void getFilteredList() {
    }
}