package athena;

import athena.exceptions.ClashInTaskException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests Storage methods.
 */
class StorageTest {

    /**
     * Checks if a save file is created correctly if a save file doesn't originally exist.
     */
    @Test
    void saveTaskListData_noPreviousSave_createSaveFile() throws ClashInTaskException {
        Ui ui = new Ui();
        TaskList taskList = TestSetup.getTestTaskList();
        Storage storage = new Storage("src/test/java/athena/loadTask.csv", ui);
        storage.saveTaskListData(taskList);
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
    void loadTaskListData_saveFileFound_createTaskList() throws ClashInTaskException {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer1.csv", ui);
        TaskList taskList;
        taskList = storage.loadTaskListData();
        TaskList tester = TestSetup.getTestTaskList();
        assertTrue(tester.equals(taskList));
    }

    @Test
    void loadTaskListData_commaInTaskAttribute_commaIsReplaced() throws ClashInTaskException {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer2.csv", ui);
        TaskList taskList;
        taskList = storage.loadTaskListData();
        TaskList tester = TestSetup.getCommaTestTaskList();
        assertTrue(tester.equals(taskList));

    }
}