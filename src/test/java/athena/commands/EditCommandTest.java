package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class EditCommandTest {
    private TaskList taskList;
    private TaskList editedTaskList;
    private Ui ui;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing.
     */
    public static TaskList getTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(1, "Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(2, "Assignment 2", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment");
        taskList.addTask(3, "Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment");
        return taskList;
    }

    /**
     * Creates a task list that is same as getTaskList() but task number 2 is edited.
     *
     * @return TaskList for testing with an edited task number 2.
     */
    public static TaskList getEditedTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(1, "Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(2,"Homework 2", "8am", "4 hrs", "8pm", "10-12-2020",
                Importance.HIGH, "Very easy homework");
        taskList.addTask(3,"Assignment 3", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment");
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() {
        ui = new Ui();
        taskList = getTaskList();
        editedTaskList = getEditedTaskList();
    }

    /**
     * Tests that the details of a task is edited if a valid task number is given.
     *
     * @throws TaskNotFoundException Exception thrown when the given task number is not in the list.
     */
    @Test
    public void execute_validNumber_taskIsEdited() throws TaskNotFoundException {
        assertEditingSuccessful(2, taskList, editedTaskList);
    }

    /**
     * Tests that a TaskNotFoundException is thrown when an task number not in the list is given.
     */
    @Test
    public void execute_invalidNumber_taskListIsUnchanged() {
        assertEditingFailsDueToInvalidNumber(-1, taskList);
    }

    /**
     * Creates a new edit command.
     *
     * @param taskNumber Task number of the task that we want to edit
     */
    private EditCommand createEditCommand(int taskNumber) {
        EditCommand command = new EditCommand(taskNumber, "Homework 2", "8am", "4 hrs", "8pm", "10-12-2020",
                Importance.HIGH, "Very easy homework");
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     */
    private void assertCommandBehaviour(EditCommand editCommand, TaskList expectedTaskList,
                                        TaskList actualTaskList) throws TaskNotFoundException {
        Ui ui = new Ui();
        editCommand.execute(taskList, ui);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts that nothing changes when the task with the given number does not exist in the given task list.
     *
     * @param taskNumber Task number to edit, but it should be an invalid number.
     * @param taskList   TaskList to edit.
     */
    private void assertEditingFailsDueToInvalidNumber(int taskNumber, TaskList taskList) {
        EditCommand command = createEditCommand(taskNumber);
        assertThrows(TaskNotFoundException.class, () -> {
            command.execute(taskList, ui);
        });
    }

    /**
     * Asserts the task with the specified number can be successfully edited.
     *
     * @param taskNumber          Task number of the task to edit.
     * @param taskList            TaskList to edit.
     * @param taskListWithoutTask Reference taskList to compare with after deleting the task.
     */
    private void assertEditingSuccessful(int taskNumber, TaskList taskList, TaskList taskListWithoutTask)
            throws TaskNotFoundException {
        TaskList expectedTaskList = taskListWithoutTask;
        TaskList actualTaskList = taskList;

        EditCommand command = createEditCommand(taskNumber);
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }
}