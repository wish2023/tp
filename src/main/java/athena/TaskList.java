package athena;

import java.util.ArrayList;

public class TaskList {
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
     * Adds specified task to the task list.
     *
     * @param task Task to be added
     */
    public void addToList(Task task) {
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
     * Edit the task at the specified position in the task list.
     *
     * @param taskNumber Position of task in task list
     * @param task The new task it should be edited to
     */
    public void editTask(int taskNumber, Task task) {
        tasks.set(taskNumber, task);
    }
    

}
