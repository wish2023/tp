package athena.tasklist;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of TaskList.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Creates a new task list before every test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Asserts if the deleted task at a certain index is the same task that is added to the task list.
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list
     */
    @Test
    void deleteTask_validTaskIndex_correctTaskDeleted() throws TaskNotFoundException {
        Task expectedTask = new Task("Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.HIGH,
                "Refer to slides", 0);
        taskList.addTask(expectedTask);
        Task actualTask = taskList.deleteTask(0);
        assertEquals(expectedTask, actualTask);
    }

    /**
     * Asserts if the edited task at a certain index is the same task that is expected.
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list
     */
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