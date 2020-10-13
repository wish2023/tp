package athena.commands;

import athena.Importance;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.AddException;
import athena.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    private TaskList taskList;
    private TaskList taskListWithAddedTask;
    private Ui ui;

    /**
     * Creates a task list for testing.
     *
     * @return TaskList for testing.
     */
    public static TaskList getTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment");
        return taskList;
    }

    /**
     * Creates a task list that is same as getTaskList() but with added task.
     *
     * @return TaskList for testing with an added task.
     */
    public static TaskList getTaskListWithAddedTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment");
        taskList.addTask(1,"Homework 2", "8am", "4 hrs", "8pm", "10-12-2020",
                Importance.HIGH, "Very easy homework");
        return taskList;
    }

    /**
     * Creates the components needed for testing.
     */
    @BeforeEach
    public void setup() {
        ui = new Ui();
        taskList = getTaskList();
        taskListWithAddedTask = getTaskListWithAddedTask();
    }

    /**
     * Tests that the task is added into the Tasks list.
     *
     * @throws AddException Exception thrown when the compulsory parameters are not given.
     */
    @Test
    public void execute_taskIsAdded() throws AddException {
        assertAddSuccessful(taskList, taskListWithAddedTask);
    }

    /**
     * Creates a new add command.
     */
    private AddCommand createAddCommand() {
        AddCommand command = new AddCommand("Homework 2", "8am", "4 hrs", "8pm", "10-12-2020",
                "high", "Very easy homework");
        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we expect.
     */
    private void assertCommandBehaviour(AddCommand addCommand, TaskList expectedTaskList,
                                        TaskList actualTaskList) throws AddException {
        Ui ui = new Ui();
        addCommand.execute(taskList, ui);
        System.out.println(expectedTaskList.getTasks());
        System.out.println(actualTaskList.getTasks());
        ArrayList<Task> list = expectedTaskList.getTasks();
        ArrayList<Task> actualList = actualTaskList.getTasks();
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Asserts the task successfully added.
     *
     * @param taskList            TaskList.
     * @param taskListWithAddedTask Reference taskList to compare with after adding the task.
     */
    private void assertAddSuccessful(TaskList taskList, TaskList taskListWithAddedTask)
            throws AddException {
        TaskList expectedTaskList = taskListWithAddedTask;
        TaskList actualTaskList = taskList;

        AddCommand command = createAddCommand();
        assertCommandBehaviour(command, expectedTaskList, actualTaskList);
    }

}