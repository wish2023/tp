package athena.logic.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.AddMissingRequiredParametersException;
import athena.exceptions.ClashInTaskException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of the add command.
 */
class AddCommandTest {

    private TaskList taskList;
    private TaskList taskListWithAddedTask;
    private Ui ui;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing
     */
    public static TaskList getTaskList() throws ClashInTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        return taskList;
    }

    /**
     * Creates a task list that is same as getTaskList() but with added task.
     *
     * @return TaskList for testing with an added task
     */
    public static TaskList getTaskListWithAddedTask() throws ClashInTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Homework 2", "0800", "4", "8pm", "10-12-2020",
                Importance.HIGH, "Very easy homework", false);
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() throws ClashInTaskException {
        ui = new Ui();
        taskList = getTaskList();
        taskListWithAddedTask = getTaskListWithAddedTask();
    }

    /**
     * Tests that the task is added into the Tasks list.
     *
     * @throws AddMissingRequiredParametersException Exception thrown when the compulsory parameters are not given
     */
    @Test
    public void execute_taskIsAdded() throws AddMissingRequiredParametersException, ClashInTaskException {
        assertAddSuccessful(taskList, taskListWithAddedTask);
    }

    /**
     * Creates a new add command.
     */
    private AddCommand createAddCommand() {
        AddCommand command = new AddCommand("Homework 2", "0800", "4", "8pm", "10-12-2020",
                "high", "Very easy homework", false);
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     *
     * @param addCommand       add command
     * @param expectedTaskList expected task list
     * @param actualTaskList   actual task list
     * @throws AddMissingRequiredParametersException Exception thrown when the compulsory parameters are not given
     */
    private void assertCommandBehaviour(AddCommand addCommand, TaskList expectedTaskList, TaskList actualTaskList)
            throws AddMissingRequiredParametersException, ClashInTaskException {
        Ui ui = new Ui();
        addCommand.execute(taskList, ui);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts the task successfully added.
     *
     * @param taskList              TaskList
     * @param taskListWithAddedTask Reference taskList to compare with after adding the task
     * @throws AddMissingRequiredParametersException Exception thrown when the compulsory parameters are not given
     */
    private void assertAddSuccessful(TaskList taskList, TaskList taskListWithAddedTask)
            throws AddMissingRequiredParametersException, ClashInTaskException {
        TaskList expectedTaskList = taskListWithAddedTask;
        TaskList actualTaskList = taskList;

        AddCommand command = createAddCommand();
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }

}