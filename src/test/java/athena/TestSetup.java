package athena;

import athena.exceptions.ClashInTaskException;

/**
 * Sets up a task list for testing classes.
 */
public class TestSetup {

    /**
     * Gets a task list filled with default tasks.
     *
     * @return Task list of default tasks
     */
    public static TaskList getTestTaskList() throws ClashInTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(2, "Assignment 3", "1600", "2", "6pm", "14-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(3, "Assignment 4", "1600", "2", "6pm", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(6, "Assignment 7", "1600", "2", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1600", "2", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

    public static TaskList getCommaTestTaskList() throws ClashInTaskException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment,1", "1600", "2", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1600", "2", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(2, "Assignment 3", "1600", "2", "6pm", "14-12-2020",
                Importance.LOW, "Tough ,,,assignment", false);
        taskList.addTask(3, "Assignment 4", "1600", "2", "6pm,", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1600", "2", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(5, "Assignment 6", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment,", false);
        taskList.addTask(6, "Assignment 7", "1600", "2", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "1600", "2", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(8, "Assignment 9", "1600", "2", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment", false);
        taskList.addTask(9, "Assignment 10", "1600", "2", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", false);
        return taskList;
    }

}