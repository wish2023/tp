package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DoneCommandTest {
    private TaskList taskList;
    private TaskList taskListWithDone;
    private Ui ui;

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
     * Creates a task list that is same as getTaskList() but task number 2 is done.
     *
     * @return TaskList for testing with a done task number 2.
     */
    public static TaskList getTaskListWithDone() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        Task doneTask = new Task("Assignment 2", "4pm", "2 hrs", "6pm",
                "13-12-2020", Importance.MEDIUM, "Tough assignment", 2);
        doneTask.setDone();
        taskList.addTask(doneTask);
        taskList.addTask("Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 3);
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() {
        ui = new Ui();
        taskList = getTaskList();
        taskListWithDone = getTaskListWithDone();
    }

    /**
     * Tests that a task is marked as done if a valid task number is given.
     *
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list.
     */
    @Test
    public void execute_validNumber_taskIsDone() throws TaskNotFoundException {
        assertDoneSuccessful(2, taskList, taskListWithDone);
    }

    /**
     * Tests that a TaskNotFoundException is thrown when an task number not in the list is given.
     */
    @Test
    public void execute_invalidNumber_taskListIsUnchanged() {
        assertDoneFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new done command.
     *
     * @param taskNumber Task number of the task that we want to mark as done
     */
    private DoneCommand createDoneCommand(int taskNumber) {
        DoneCommand command = new DoneCommand(taskNumber);
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     */
    private void assertCommandBehaviour(DoneCommand doneCommand, TaskList expectedTaskList,
                                        TaskList actualTaskList) throws TaskNotFoundException {
        Ui ui = new Ui();
        doneCommand.execute(taskList, ui);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts that nothing changes when the task with the given number does not exist in the given task list.
     *
     * @param taskNumber Task number to mark as done, but it should be an invalid number.
     * @param taskList   TaskList to modify.
     */
    private void assertDoneFailsDueToInvalidNumber(int taskNumber, TaskList taskList) {
        DoneCommand command = createDoneCommand(taskNumber);
        assertThrows(TaskNotFoundException.class, () -> {
            command.execute(taskList, ui);
        });
    }

    /**
     * Asserts the task with the specified number can be successfully marked as done.
     *
     * @param taskNumber          Task number of the task to mark as done.
     * @param taskList            TaskList to modify.
     * @param taskListWithoutTask Reference taskList to compare with after marking the task as done.
     */
    private void assertDoneSuccessful(int taskNumber, TaskList taskList, TaskList taskListWithoutTask)
            throws TaskNotFoundException {
        TaskList expectedTaskList = taskListWithoutTask;
        TaskList actualTaskList = taskList;

        DoneCommand command = createDoneCommand(taskNumber);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}