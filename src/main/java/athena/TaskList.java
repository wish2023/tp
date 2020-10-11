package athena;

import athena.task.Task;
import athena.task.taskfilter.TaskFilter;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    public static final String NO_FILTER = "";
    private ArrayList<Task> tasks;
    private int maxIndex = -1;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = new ArrayList<>();
        tasks.addAll(taskList);
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private Task createTask(String name, String startTime,
                            String duration, String deadline, String recurrence, Importance importance, String notes,
                            int index) {

        Task task = new Task(name, startTime, duration,
                deadline, recurrence, importance, notes, index);
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
     * @return Task marked as done
     */
    public Task markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone();
        return tasks.get(taskNumber);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the task list.
     *
     * @param name       Name of task
     * @param startTime  Start time of task
     * @param duration   Duration of task
     * @param deadline   Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes      Additional notes of task
     * @param index      Index of the task
     */
    public void addTask(String name, String startTime, String duration,
                        String deadline, String recurrence, Importance importance, String notes, int index) {

        Task task = createTask(name, startTime, duration, deadline, recurrence, importance, notes, index);
        tasks.add(task);
    }

    /**
     * Adds a task to the task list.
     *
     * @param name       Name of task
     * @param startTime  Start time of task
     * @param duration   Duration of task
     * @param deadline   Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes      Additional notes of task
     */
    public void addTask(String name, String startTime, String duration,
                        String deadline, String recurrence, Importance importance, String notes) {
        maxIndex++;
        addTask(name, startTime, duration, deadline, recurrence, importance, notes, maxIndex);
    }

    /**
     * Returns the task description at the specified position in task list.
     *
     * @param index Position of task in the task list
     * @return Task description
     */
    public String getDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param taskNumber Position of task in task list
     * @return Task deleted
     */
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task taskToDelete = null;
        int counter = -1;
        int index = -1;
        for (Task t : tasks) {
            counter++;
            if (t.getIndex() == taskNumber) {
                taskToDelete = tasks.get(taskNumber);
                index = counter;
            }
        }
        tasks.remove(index);
        return taskToDelete;
    }

    /**
     * Edits a task in the task list.
     *
     * @param taskNumber Index of task
     * @param name       Name of task
     * @param startTime  Start time of task
     * @param duration   Duration of task
     * @param deadline   Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes      Additional notes of task
     */
    public void editTask(int taskNumber, String name, String startTime, String duration,
                         String deadline, String recurrence, Importance importance, String notes) {

        tasks.get(taskNumber).edit(name, startTime, duration,
                deadline, recurrence, importance, notes);
    }

    /**
     * Returns a filtered task list.
     *
     * @param taskFilter The filter that decides which tasks are printed
     * @return Filtered task list
     */
    public TaskList getFilteredList(TaskFilter taskFilter) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (taskFilter.isTaskIncluded(task)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskList)) return false;
        TaskList taskList = (TaskList) o;
        return getMaxIndex() == taskList.getMaxIndex() &&
                getTasks().equals(taskList.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTasks(), getMaxIndex());
    }
}
