package athena;

import athena.exceptions.ClashInTaskException;
import athena.exceptions.CommandException;
import athena.exceptions.StorageException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests Storage methods.
 */
class StorageTest {

    /**
     * Checks if a save file is created correctly if a save file doesn't originally exist.
     */
    @Test
    void saveTaskListData_noPreviousSave_createSaveFile() throws CommandException {
        TaskList taskList = null;
        try {
            taskList = TestSetup.getTestTaskList();
        } catch (ClashInTaskException e) {
            assert false;
        }
        Storage storage = new Storage("src/test/java/athena/loadTask.csv");
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
    void loadTaskListData_saveFileFound_createTaskList() throws CommandException {
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer1.csv");
        TaskList taskList = null;
        try {
            taskList = storage.loadTaskListData();
        } catch (StorageException e) {
            assert false;
        }
        TaskList tester = TestSetup.getTestTaskList();
        assertTrue(tester.equals(taskList));
    }

    @Test
    void loadTaskListData_commaInTaskAttribute_commaIsReplaced() throws CommandException {
        Storage storage = new Storage("src/test/java/athena/StorageTestAnswer2.csv");
        TaskList taskList = null;
        try {
            taskList = storage.loadTaskListData();
        } catch (StorageException e) {
            assert false;
        }

        TaskList tester = null;
        try {
            tester = TestSetup.getCommaTestTaskList();
        } catch (ClashInTaskException e) {
            assert false;
        }
        assertTrue(tester.equals(taskList));

    }

    @Test
    void loadTaskListData_scrambledTaskNumbers_correctMaxNumber() {
        Storage storage = new Storage("src/test/java/athena/StorageMaxNumberTest.csv");
        TaskList taskList = null;
        try {
            taskList = storage.loadTaskListData();
        } catch (StorageException e) {
            assert false;
        }
        assertEquals(taskList.getMaxNumber(), 61);
    }
}