package athena;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }


    private void printNumberOfTasks() {
        System.out.printf("Now you have %d task%s in the list.\n", tasks.size(),
                (tasks.size() == 1) ? "" : "s");
    }

    private void printDeleteMessage() {
        System.out.println("I have deleted this task!");
    }

    private void printErrorMessage() {
        System.out.println("You entered an invalid number");
    }

    private int getTaskNumber(String index) {
        return Integer.parseInt(index) - 1;
    }


    private ArrayList<Task> getShortlistedTasks(String instance) {
        ArrayList<Task> shortlistedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getName().contains(instance)) {
                shortlistedTasks.add(task);
            }
        }
        return shortlistedTasks;
    }

    private void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }



    /**
     * Prints every task in the task list.
     */
    public void displayList() {
        System.out.println("Here's your TASK LIST");
        printTasks();
    }


    /**
     * Returns size of the task list.
     *
     * @return Size of the task list
     */
    public int getTaskListSize() {
        return tasks.size();
    }


    /**
     * Marks specified task as done.
     * Lets the user know specified task has been marked as done.
     *
     * @param task Task to be marked
     */
    public void markTaskAsDone(String task) {
        int taskNumber = getTaskNumber(task);
        try {
            tasks.get(taskNumber).setDone();
            System.out.println("I have marked your task as done!");
            System.out.printf("\t%s\n", tasks.get(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage();
        }

    }


    /**
     * Adds specified task to the task list.
     * Lets the user know specified task has been added to the task list.
     *
     * @param task Task to be added
     */
    public void addToList(Task task) {
        tasks.add(task);
        System.out.println("Okay! I have added this:");
        System.out.printf("\t%s\n", task);
        printNumberOfTasks();
    }




    /**
     * Returns the task description at the specified position in task list.
     *
     * @param index Position of task in the task list
     * @return Task description
     */
    public String getLine(int index) {
        return tasks.get(index).toString();
    }


    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param index Position of task in task list
     */
    public void deleteTask(String index) {
        int taskNumber = getTaskNumber(index);
        try {
            String deletedTask = "\t" + tasks.get(taskNumber);
            printDeleteMessage();
            System.out.println(deletedTask);
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage();
        }
        printNumberOfTasks();
    }




    /**
     * Edit the task at the specified position in the task list.
     *
     * @param index Position of task in task list
     * @param task The new task it should be edited to
     */
    public void editTask(String index, Task task) {
        int taskNumber = getTaskNumber(index);
        try {
            tasks.set(taskNumber, task);
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage();
        }
    }



}
