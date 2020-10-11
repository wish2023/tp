package athena.task.taskfilter;

import athena.Importance;
import athena.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ImportanceFilterTest {
    private TaskList taskList;
    private TaskList taskList2;
    private TaskList mediumImportanceTaskList;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing.
     */
    public static TaskList getTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        taskList.addTask("Assignment 2", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 2);
        taskList.addTask("Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 3);
        return taskList;
    }

    /**
     * Creates a task list that only contains MEDIUM importance tasks.
     *
     * @return TaskList for testing that only contains MEDIUM importance tasks.
     */
    public static TaskList getMediumImportanceTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 2", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 2);
        taskList.addTask("Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 3);
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() {
        taskList = getTaskList();
        taskList2 = getTaskList();
        mediumImportanceTaskList = getMediumImportanceTaskList();
    }

    //    /**
    //     * Tests that an ALL ImportanceFilter will filter return all Tasks in a TaskList.
    //     */
    //    @Test
    //    public void getFilteredList_allImportance_returnsAllTasks() {
    //        assertFilteringSuccessful(Importance.ALL, taskList, taskList2);
    //    }

    /**
     * Tests that a MEDIUM ImportanceFilter will filter all medium importance Tasks in a TaskList.
     */
    @Test
    public void getFilteredList_mediumImportance_returnsMediumImportanceTasks() {
        assertFilteringSuccessful(Importance.MEDIUM, taskList, mediumImportanceTaskList);
    }

    /**
     * Creates a new importance filter.
     *
     * @param importance Importance that we want to filter.
     */
    private ImportanceFilter createImportanceFilter(Importance importance) {
        return new ImportanceFilter(importance);
    }

    /**
     * Asserts the TaskList is correctly filtered.
     *
     * @param importance       Importance to filter.
     * @param taskList         TaskList to filter from.
     * @param expectedTaskList Reference taskList to compare with after filtering the TaskList.
     */
    private void assertFilteringSuccessful(Importance importance, TaskList taskList,
                                           TaskList expectedTaskList) {
        ImportanceFilter importanceFilter = createImportanceFilter(importance);
        TaskList filteredTaskList = taskList.getFilteredList(importanceFilter);
        assertEquals(filteredTaskList, expectedTaskList);
    }
}