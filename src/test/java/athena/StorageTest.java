package athena;

import athena.task.Task;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {

    @Test
    void saveTaskListData_noPreviousSave_createSaveFile() {
        Ui ui = new Ui();
        TaskList taskList = TestSetup.getTestTaskList();
        Storage storage = new Storage("src/test/java/athena/loadTask.csv", ui);
        storage.saveTaskListData(taskList);
        assertTrue(areFilesSame("src/test/java/athena/loadTask.csv", "src/test/java/athena/TaskAnswer.csv"));
    }

    private boolean areFilesSame(String file1, String file2) {
        BufferedReader answer = null;
        try {
            answer = new BufferedReader(new FileReader(file1));
            BufferedReader attempt = new BufferedReader(new FileReader(file2));
            String answerLine = answer.readLine();
            String attemptLine = attempt.readLine();
            while ((answerLine != null) || (attemptLine != null)) {
                if (!answerLine.contentEquals(attemptLine)) {
                    return false;
                } else {
                    answerLine = answer.readLine();
                    attemptLine = attempt.readLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Test
    void loadTaskListData_saveFileFound_createTaskList() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/test/java/athena/TaskAnswer.csv", ui);
        TaskList taskList;
        taskList = storage.loadTaskListData();
        TaskList tester = TestSetup.getTestTaskList();
        assertTrue(tester.equals(taskList));
    }
}