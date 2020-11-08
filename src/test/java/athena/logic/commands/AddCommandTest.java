package athena.logic.commands;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of the add command.
 */
class AddCommandTest {

    private TaskList taskList;
    private TaskList taskListWithAddedTask;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing
     */
    public static TaskList getTaskList() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        return taskList;
    }

    /**
     * Creates a task list that is same as getTaskList() but with added task.
     *
     * @return TaskList for testing with an added task
     */
    public static TaskList getTaskListWithAddedTask() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Homework 2", "0800", "4", "01-01-2021", "10-12-2020",
                Importance.HIGH, "Very easy homework", false);
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() throws CommandException {
        taskList = getTaskList();
        taskListWithAddedTask = getTaskListWithAddedTask();
    }

    /**
     * Tests that the task is added into the Tasks list.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void execute_taskIsAdded() throws CommandException {
        assertAddSuccessful(taskList, taskListWithAddedTask);
    }

    /**
     * Creates a new add command.
     */
    private AddCommand createAddCommand() {
        AddCommand command = new AddCommand("Homework 2", "0800", "4", "01-01-2021", "10-12-2020",
                Importance.HIGH, "Very easy homework", false);
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     *
     * @param addCommand       add command
     * @param expectedTaskList expected task list
     * @param actualTaskList   actual task list
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    private void assertCommandBehaviour(AddCommand addCommand, TaskList expectedTaskList, TaskList actualTaskList)
            throws CommandException {
        AthenaUi athenaUi = new AthenaUi();
        addCommand.execute(taskList, athenaUi);
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts the task successfully added.
     *
     * @param taskList              TaskList
     * @param taskListWithAddedTask Reference taskList to compare with after adding the task
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    private void assertAddSuccessful(TaskList taskList, TaskList taskListWithAddedTask)
            throws CommandException {
        TaskList expectedTaskList = taskListWithAddedTask;
        TaskList actualTaskList = taskList;

        AddCommand command = createAddCommand();
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }

}