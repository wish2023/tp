package athena;

import athena.exceptions.ClashInTaskException;
import athena.exceptions.CommandException;

/**
 * Sets up a task list for testing classes.
 */
public class TestSetup {

    /**
     * Gets a task list filled with default tasks.
     *
     * @return Task list of default tasks
     */
    public static TaskList getTestTaskList() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "01-01-2021", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(2, "Assignment 3", "1000", "2", "01-01-2021", "14-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(3, "Assignment 4", "1300", "2", "01-01-2021", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "01-01-2021", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "01-01-2021", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(6, "Assignment 7", "1900", "2", "01-01-2021", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "2100", "2", "01-01-2021", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "01-01-2021", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1300", "2", "01-01-2021", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    public static TaskList getCommaTestTaskList() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment,1", "1600", "2", "01-01-2021", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "01-01-2021", "13-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(2, "Assignment 3", "1000", "2", "01-01-2021", "14-12-2020",
                Importance.LOW, "Tough ,,,assignment", false);
        taskList.addTask(3, "Assignment 4", "1300", "2", "01-01-2021", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "01-01-2021", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "01-01-2021", "15-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(6, "Assignment 7", "1900", "2", "01-01-2021", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "2100", "2", "01-01-2021", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "01-01-2021", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1300", "2", "01-01-2021", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

}