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

    public static TaskList getTaskListWithoutTask() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        taskList.addTask("Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 3);
        return taskList;
    }

    @BeforeEach
    public void setup() {
        ui = new Ui();
        taskList = getTaskList();
        taskListWithoutTask = getTaskListWithoutTask();
    }

    @Test
    public void execute_validIndex_taskIsDeleted() throws TaskNotFoundException {
        assertDeletionSuccessful(2, taskList, taskListWithoutTask);
    }

    @Test
    public void execute_invalidNumber_taskListIsUnchanged() throws TaskNotFoundException {
        assertDeletionFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new delete command.
     *
     * @param targetIndex of the task that we want to delete
     */
    private DeleteCommand createDeleteCommand(int targetIndex) {
        DeleteCommand command = new DeleteCommand(targetIndex);
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
     */
    private void assertDeletionFailsDueToInvalidNumber(int invalidIndex, TaskList taskList) {
        DeleteCommand command = createDeleteCommand(invalidIndex);
        assertThrows(TaskNotFoundException.class, () -> {
            command.execute(taskList, ui);
        });
    }

    /**
     * Asserts the task at the specified index can be successfully deleted.
     */
    private void assertDeletionSuccessful(int targetIndex, TaskList taskList, TaskList taskListWithoutTask)
            throws TaskNotFoundException {
        TaskList expectedTaskList = taskListWithoutTask;
        TaskList actualTaskList = taskList;

        DeleteCommand command = createDeleteCommand(targetIndex);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}