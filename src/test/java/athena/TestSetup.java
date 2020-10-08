package athena;

import athena.task.Task;

public class TestSetup {

    public static TaskList getTestTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask("Assignment 1", "4pm", "2 hrs", "6pm", "12-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        taskList.addTask("Assignment 2", "4pm", "2 hrs", "6pm", "13-12-2020",
                Importance.MEDIUM, "Tough assignment", 1);
        taskList.addTask("Assignment 3", "4pm", "2 hrs", "6pm", "14-12-2020",
                Importance.LOW, "Tough assignment", 1);
        taskList.addTask("Assignment 4", "4pm", "2 hrs", "6pm", "14-12-2020",
                Importance.MEDIUM, "Tough assignment", 1);
        taskList.addTask("Assignment 5", "4pm", "2 hrs", "6pm", "14-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        taskList.addTask("Assignment 6", "4pm", "2 hrs", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", 1);
        taskList.addTask("Assignment 7", "4pm", "2 hrs", "6pm", "15-12-2020",
                Importance.HIGH, "Tough assignment", 1);
        taskList.addTask("Assignment 8", "4pm", "2 hrs", "6pm", "15-12-2020",
                Importance.MEDIUM, "Tough assignment", 1);
        taskList.addTask("Assignment 9", "4pm", "2 hrs", "6pm", "16-12-2020",
                Importance.LOW, "Tough assignment", 1);
        taskList.addTask("Assignment 10", "4pm", "2 hrs", "6pm", "16-12-2020",
                Importance.MEDIUM, "Tough assignment", 1);
        return taskList;
    }

}
