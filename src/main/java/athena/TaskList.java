package athena;

import athena.task.Task;
import athena.task.taskfilter.TaskFilter;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
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

    private Task createTask(String name, String startTime, String duration, String deadline,
                            String recurrence, Importance importance, String notes, int index) {
        Task task = new Task(name, startTime, duration, deadline, recurrence, importance, notes, index);
        return task;
    }

    /**
     * Returns size of the task list.
     *
     * @return Size of the task list.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Marks specified task as done.
     *
     * @param taskNumber Task number.
     * @return Task marked as done.
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
     * Returns the task description of the task with the given number.
     *
     * @param taskNumber Task number.
     * @return Task description.
     */
    public String getTaskDescription(int taskNumber) {
        Task task = getTaskFromNumber(taskNumber);
        return task.toString();
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param taskNumber Number assigned to the task to be deleted.
     * @return Task that is deleted. Null if not found.
     */
    public Task deleteTask(int taskNumber) {
        Task task = getTaskFromNumber(taskNumber);
        if (task != null) {
            tasks.remove(task);
        }
        return task;
    }

    /**
     * Edits a task in the task list with the given number, if present.
     *
     * @param taskNumber Task number
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
        Task task = getTaskFromNumber(taskNumber);
        if (task != null) {
            task.edit(name, startTime, duration, deadline, recurrence, importance, notes);
        }
    }

    /**
     * Gets a task based on the number assigned to it.
     *
     * @param taskNumber number assigned to the task.
     * @return The task with the given number. Null if not found.
     */
    private Task getTaskFromNumber(int taskNumber) {
        for (Task t : tasks) {
            if (t.getNumber() == taskNumber) {
                return t;
            }
        }
        return null;
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskList)) {
            return false;
        }
        TaskList taskList = (TaskList) o;
        return getMaxIndex() == taskList.getMaxIndex() && getTasks().equals(taskList.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTasks(), getMaxIndex());
    }
}
