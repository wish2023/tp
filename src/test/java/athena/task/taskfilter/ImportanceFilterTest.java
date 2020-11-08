package athena.task.taskfilter;

import athena.Importance;
import athena.exceptions.command.CommandException;
import athena.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ImportanceFilterTest {

    /**
     * Tests that the task should be included when Importance.ALL is chosen.
     */
    @Test
    void isTaskIncluded_all_returnsTrue() throws CommandException {
        ImportanceFilter importanceFilter = new ImportanceFilter(Importance.ALL);
        Task task = new Task("testName", "0900", "1", "05-11-2020",
                "20-12-2020", Importance.MEDIUM, "testNotes", 0, false);
        boolean isTaskIncluded = importanceFilter.isTaskIncluded(task);
        assertTrue(isTaskIncluded);
    }

    /**
     * Tests that the task should be included when an Importance that is same as the task's is chosen.
     */
    @Test
    void isTaskIncluded_sameImportance_returnsTrue() throws CommandException {
        ImportanceFilter importanceFilter = new ImportanceFilter(Importance.HIGH);
        Task task = new Task("testName", "0900", "1", "05-11-2020",
                "20-12-2020", Importance.HIGH, "testNotes", 0, false);
        boolean isTaskIncluded = importanceFilter.isTaskIncluded(task);
        assertTrue(isTaskIncluded);
    }

    /**
     * Tests that the task should not be included when an Importance that is different from the task's is chosen.
     */
    @Test
    void isTaskIncluded_differentImportance_returnsFalse() throws CommandException {
        ImportanceFilter importanceFilter = new ImportanceFilter(Importance.HIGH);
        Task task = new Task("testName", "0900", "1", "05-11-2020",
                "20-12-2020", Importance.LOW, "testNotes", 0, false);
        boolean isTaskIncluded = importanceFilter.isTaskIncluded(task);
        assertFalse(isTaskIncluded);
    }
}