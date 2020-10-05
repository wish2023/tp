package athena;

import java.util.ArrayList;

public class TaskList {
    public static final String NO_FILTER = "";
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
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

    private void printAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void printTasksWithFilter(String importanceFilter) {
        for (Task task : tasks) {
            if (task.getImportance().equals(importanceFilter)) {
                System.out.println(task);
            }
        }
    }


    private Task createTask(String name, String startTime,
            String duration, String deadline, String recurrence, String importance, String notes) {
        Task task = new Task(name, startTime, duration,
                deadline, recurrence, importance, notes);
        return task;
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
     * @param taskNumber Position of task in task list
     */
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone();
    }


    /**
     * Adds a task to the task list.
     *
     * @param name Name of task
     * @param startTime Start time of task
     * @param duration Duration of task
     * @param deadline Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes Additional notes of task
     */
    public void addToList(String name, String startTime, String duration,
                String deadline, String recurrence, String importance, String notes) {
        Task task = createTask(name, startTime, duration, deadline, recurrence, importance, notes);
        tasks.add(task);
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
     * @param taskNumber Position of task in task list
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }


    /**
     * Edits a task in the task list.
     *
     * @param taskNumber Index of task
     * @param name Name of task
     * @param startTime Start time of task
     * @param duration Duration of task
     * @param deadline Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes Additional notes of task
     */
    public void editTask(int taskNumber, String name, String startTime, String duration,
                         String deadline, String recurrence, String importance, String notes) {

        tasks.get(taskNumber).changeAttributesTo(name, startTime, duration,
                deadline, recurrence, importance, notes);
    }

    /**
     * Displays a filtered task list based on importance.
     *
     * @param importanceFilter The filter that decides which tasks are printed
     */
    public void displayList(String importanceFilter) {
        if (importanceFilter.equals(NO_FILTER)) {
            printAllTasks();
        } else {
            printTasksWithFilter(importanceFilter);
        }
    }



}
