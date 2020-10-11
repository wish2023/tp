package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DeleteCommandTest {
    private TaskList taskList;
    private TaskList taskListWithoutTask;
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
     * Creates a task list that is same as getTaskList() but without task number 2.
     *
     * @return TaskList for testing without task number 2.
     */
    public static TaskList getTaskListWithoutTask() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", 1);
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
        taskListWithoutTask = getTaskListWithoutTask();
    }

    /**
     * Tests that a task is a deleted from a list if a valid task number is given.
     *
     * @throws TaskNotFoundException
     */
    @Test
    public void execute_validNumber_taskIsDeleted() throws TaskNotFoundException {
        assertDeletionSuccessful(2, taskList, taskListWithoutTask);
    }

    /**
     * Tests that a TaskNotFoundException is thrown when an task number not in the list is given.
     *
     * @throws TaskNotFoundException
     */
    @Test
    public void execute_invalidNumber_taskListIsUnchanged() {
        assertDeletionFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new delete command.
     *
     * @param taskNumber of the task that we want to delete
     */
    private DeleteCommand createDeleteCommand(int taskNumber) {
        DeleteCommand command = new DeleteCommand(taskNumber);
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     */
    private void assertCommandBehaviour(DeleteCommand deleteCommand, TaskList expectedTaskList,
                                        TaskList actualTaskList) throws TaskNotFoundException {
        Ui ui = new Ui();
        deleteCommand.execute(taskList, ui);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts that nothing changes when the task with the given number does not exist in the given task list.
     *
     * @param taskNumber Task number to delete, but it should be an invalid number.
     * @param taskList   TaskList to delete from.
     */
    private void assertDeletionFailsDueToInvalidNumber(int taskNumber, TaskList taskList) {
        DeleteCommand command = createDeleteCommand(taskNumber);
        assertThrows(TaskNotFoundException.class, () -> {
            command.execute(taskList, ui);
        });
    }

    /**
     * Asserts the task with the specified number can be successfully deleted.
     *
     * @param taskNumber          Task number of the task to delete.
     * @param taskList            TaskList to delete from.
     * @param taskListWithoutTask Reference taskList to compare with after deleting the task.
     */
    private void assertDeletionSuccessful(int taskNumber, TaskList taskList, TaskList taskListWithoutTask)
            throws TaskNotFoundException {
        TaskList expectedTaskList = taskListWithoutTask;
        TaskList actualTaskList = taskList;

        DeleteCommand command = createDeleteCommand(taskNumber);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}