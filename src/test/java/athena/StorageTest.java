package athena;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests Storage methods.
 */
class StorageTest {

    private TaskList getTestTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(2, "Assignment 3", "1600", "2", "6pm", "14-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(3, "Assignment 4", "1600", "2", "6pm", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(6, "Assignment 7", "1600", "2", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1600", "2", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    private TaskList getCommaTestTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment,1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(2, "Assignment 3", "1600", "2", "6pm", "14-12-2020",
                Importance.LOW, "Tough ,,,assignment", false);
        taskList.addTask(3, "Assignment 4", "1600", "2", "6pm,", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(6, "Assignment 7", "1600", "2", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1600", "2", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    /**
     * Checks if a save file is created correctly if a save file doesn't originally exist.
     */
    @Test
    void saveTaskListData_noPreviousSave_createSaveFile() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/loadTask.csv", ui);
        storage.saveTaskListData(getTestTaskList());
        assertTrue(areFilesSame("src/test/java/athena/loadTask.csv", "src/test/java/athena/StorageTestAnswer1.csv"));
    }

    /**
     * Checks if two save files are the same based on their contents.
     *
     * @param file1 First file
     * @param file2 Second file
     * @return True or false depending on if the files are the same
     */
    private boolean areFilesSame(String file1, String file2) {
        BufferedReader expected = null;
        try {
            expected = new BufferedReader(new FileReader(file1));
            BufferedReader actual = new BufferedReader(new FileReader(file2));
            String expectedLine = expected.readLine();
            String actualLine = actual.readLine();
            while ((expectedLine != null) || (actualLine != null)) {
                assert expectedLine != null;
                if (!expectedLine.contentEquals(actualLine)) {
                    return false;
                }
                expectedLine = expected.readLine();
                actualLine = actual.readLine();


            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Checks if the program is able to load a save file correctly.
     */
    @Test
    void loadTaskListData_saveFileFound_createTaskList() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer1.csv", ui);
        TaskList loadedTaskList;
        loadedTaskList = storage.loadTaskListData();
        TaskList testTaskList = getTestTaskList();
        assertTrue(loadedTaskList.equals(testTaskList));
    }

    @Test
    void loadTaskListData_commaSave_comma() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer2.csv", ui);
        TaskList loadedTaskList;
        loadedTaskList = storage.loadTaskListData();
        TaskList commaTestTaskList = getCommaTestTaskList();
        assertTrue(commaTestTaskList.equals(loadedTaskList));
    }
}
