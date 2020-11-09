package athena;

import athena.exceptions.command.IllegalTimeModificationException;
import athena.exceptions.command.ClashInTaskException;
import athena.exceptions.command.InvalidDeadlineException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.InvalidTimeFormatException;
import athena.exceptions.command.TaskDuringSleepTimeException;
import athena.exceptions.command.TaskIsDoneException;
import athena.exceptions.command.TaskNotFoundException;

import athena.task.Task;
import athena.task.Time;
import athena.task.taskfilter.TaskFilter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The list that stores the user's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int maxNumber = -1;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList using an existing ArrayList.
     *
     * @param tasks ArrayList to convert to TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);

        for (Task task : tasks) {
            maxNumber = Math.max(maxNumber, task.getNumber());
        }
    }

    /**
     * Gets an ArrayList of the tasks.
     *
     * @return The ArrayList of the tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates a new task.
     *
     * @param number     Task number
     * @param name       Task name
     * @param startTime  Start time of the task
     * @param duration   Duration of the task
     * @param deadline   Due date of the task
     * @param recurrence When the task occurs again
     * @param importance Importance of the task
     * @param notes      Additional notes
     * @return Task as Task object
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     * @throws InvalidRecurrenceException   Exception thrown when user mistypes recurrence
     * @throws InvalidDeadlineException     Exception thrown when user mistypes deadline
     */
    private Task createTask(int number, String name, String startTime, String duration, String deadline,
                            String recurrence, Importance importance, String notes, Boolean isFlexible)
            throws TaskDuringSleepTimeException, InvalidRecurrenceException, InvalidDeadlineException {
        Task task = new Task(name, startTime, duration, deadline, recurrence, importance, notes, number, isFlexible);
        return task;
    }


    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        updateMaxNumber(task.getNumber());
        tasks.add(task);
    }

    /**
     * Adds a task to the task list.
     *
     * @param number     Number assigned to the task
     * @param name       Name of task
     * @param startTime  Start time of task
     * @param duration   Duration of task
     * @param deadline   Deadline of task
     * @param recurrence Recurrence of task
     * @param importance Importance of task
     * @param notes      Additional notes of task
     * @return Task added.
     */

    public Task addTask(int number, String name, String startTime, String duration,
                        String deadline, String recurrence,
                        Importance importance, String notes, boolean isFlexible)
            throws ClashInTaskException, TaskDuringSleepTimeException, InvalidTimeFormatException,
            InvalidRecurrenceException, InvalidDeadlineException {
        try {
            number = checkNumber(number);
            Task task = createTask(number, name, startTime,
                    duration, deadline, recurrence, importance, notes, isFlexible);
            decrementMaxNumber();
            checkClash(task);
            updateMaxNumber(number);
            tasks.add(task);
            return task;
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
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
     * @return Task added.
     */
    public Task addTask(String name, String startTime, String duration,
                        String deadline, String recurrence,
                        Importance importance, String notes, Boolean isFlexible)
            throws ClashInTaskException, TaskDuringSleepTimeException,
            InvalidTimeFormatException, InvalidRecurrenceException, InvalidDeadlineException {
        incrementMaxNumber();
        Task task = addTask(maxNumber, name, startTime, duration, deadline, recurrence, importance, notes, isFlexible);
        return task;
    }

    /**
     * Decrements maxNumber.
     */
    private void decrementMaxNumber() {
        maxNumber--;
    }

    /**
     * Increments maxNumber.
     */
    private void incrementMaxNumber() {
        maxNumber++;
    }

    /**
     * Checks if new task clashes with TaskList.
     *
     * @param taskToCompare the new task that may be added.
     * @throws ClashInTaskException Exception thrown when the task clashes with TaskList
     */
    private void checkClash(Task taskToCompare) throws ClashInTaskException {
        for (Task task : tasks) {
            checkTimeClash(taskToCompare, task);
        }
    }

    /**
     * Checks for a clash between two tasks.
     *
     * @param taskToCompare the new task that may be added
     * @param task          the current task in tasklist
     * @throws ClashInTaskException Exception thrown when the task clashes with TaskList
     */
    private void checkTimeClash(Task taskToCompare, Task task) throws ClashInTaskException {
        if (isTimeClash(taskToCompare, task)) {
            checkRecurrenceClash(taskToCompare, task);
        }
    }


    /**
     * Checks if there is a date clash between tasks.
     *
     * @param taskToCompare the new task that may be added
     * @param task          the current task in tasklist
     * @throws ClashInTaskException Exception thrown when the task clashes with TaskList
     */
    private void checkRecurrenceClash(Task taskToCompare, Task task) throws ClashInTaskException {
        LocalDate dateToCompare = taskToCompare.getTimeInfo().getRecurrenceDates().get(0);
        for (LocalDate date : task.getTimeInfo().getRecurrenceDates()) {
            if (dateToCompare.equals(date) && taskToCompare.getNumber() != task.getNumber()
                    && !task.isFlexible() && !taskToCompare.isFlexible()) {
                throw new ClashInTaskException();
            }
        }
    }

    /**
     * Checks if there is a timing clash between tasks.
     *
     * @param taskToCompare the new task that may be added
     * @param task          the current task in tasklist
     * @return
     */
    private boolean isTimeClash(Task taskToCompare, Task task) {
        LocalTime taskStartTime = taskToCompare.getTimeInfo().getStartTime();
        if (taskStartTime == null) {
            return false;
        }
        LocalTime taskEndTime = taskToCompare.getTimeInfo().getEndTime();

        LocalTime existingTaskStartTime = task.getTimeInfo().getStartTime();
        if (existingTaskStartTime == null) {
            return false;
        }
        LocalTime existingTaskEndTime = task.getTimeInfo().getEndTime();
        if (isIndividualTimeClash(taskStartTime, taskEndTime, existingTaskStartTime, existingTaskEndTime)) {
            return true;
        }
        return false;
    }


    /**
     * Checks if times of a task overlap each other.
     *
     * @param taskStartTime             the starting time of the new task that may be added
     * @param taskEndTime               the ending time of the new task that may be added
     * @param existingTaskStartTime     the starting time of the existing task
     * @param existingTaskEndTime       the ending time of the existing task
     * @return
     */
    private boolean isIndividualTimeClash(LocalTime taskStartTime, LocalTime taskEndTime,
                                          LocalTime existingTaskStartTime, LocalTime existingTaskEndTime) {
        boolean isTimeClash = !(taskEndTime.compareTo(existingTaskStartTime) <= 0
                || (taskStartTime.compareTo(existingTaskEndTime) >= 0
                && existingTaskEndTime.compareTo(existingTaskStartTime) > 0));
        boolean isMidnightClash = taskEndTime.compareTo(taskStartTime) < 0
                && existingTaskEndTime.compareTo(existingTaskStartTime) < 0;
        return isTimeClash || isMidnightClash;
    }

    /**
     * Updates maxNumber based on the task ID.
     *
     * @param number the Task-ID
     */
    private void updateMaxNumber(int number) {
        maxNumber++;
        if (maxNumber < number) {
            maxNumber = number;
        }
    }

    /**
     * Updates the Task-ID based on maxNumber.
     *
     * @param number the Task-ID
     * @return the new Task-ID
     */
    private int checkNumber(int number) {
        if (number < maxNumber) {
            number = maxNumber;
        }
        return number;
    }


    /**
     * Returns the task description of the task with the given number.
     *
     * @param taskNumber Task number.
     * @return Task description.
     * @throws TaskNotFoundException Exception thrown when the program is unable to find a task at the index
     *                               specified by the user
     */
    public String getTaskDescription(int taskNumber) throws TaskNotFoundException {
        Task task = getTaskFromNumber(taskNumber);
        return task.getDetailsAsString();
    }


    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param taskNumber Number assigned to the task to be deleted.
     * @return Task that is deleted. Null if not found.
     * @throws TaskNotFoundException thrown when the program is unable to find a task at the index
     *                               specified by the user
     */
    public Task deleteTask(int taskNumber) throws TaskNotFoundException {
        Task task = getTaskFromNumber(taskNumber);
        tasks.remove(task);
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
     * @return Edited task.
     * @throws TaskNotFoundException thrown when the program is unable to find a task at the index
     *                               specified by the user
     * @throws ClashInTaskException thrown when there is a clash with another task.
     * @throws TaskDuringSleepTimeException thrown when the user wants a task to be done during sleep time.
     * @throws InvalidRecurrenceException when user mistypes recurrence
     * @throws InvalidDeadlineException when user mistypes deadline
     */
    public Task editTask(int taskNumber, String name, String startTime, String duration,
                         String deadline, String recurrence, Importance importance,
                         String notes)
            throws TaskNotFoundException, ClashInTaskException,
            TaskDuringSleepTimeException, InvalidRecurrenceException, InvalidDeadlineException,
            IllegalTimeModificationException {
        Task task = getTaskFromNumber(taskNumber);
        Time time = task.getTimeInfo();
        if (isFlexibleTaskEdit(startTime, recurrence, time)) {
            throw new IllegalTimeModificationException();
        }
        Task possibleEditedTask = createTask(taskNumber, name, startTime,
                duration, deadline, recurrence, importance, notes, task.isFlexible());
        checkClash(possibleEditedTask);
        task.edit(name, startTime, duration, deadline, recurrence, importance, notes);
        return possibleEditedTask;

    }

    /**
     * Checks if the task being edited is a flexible task.
     *
     * @param startTime     New start time of task
     * @param recurrence    New recurrence of task
     * @param time          Time related information of task
     * @return
     */
    private boolean isFlexibleTaskEdit(String startTime, String recurrence, Time time) {
        return time.getFlexible() && ((startTime != time.getStartTimeString()) || (recurrence != time.getRecurrence()));
    }

    /**
     * Marks specified task as done.
     *
     * @param taskNumber Task number.
     * @return Task marked as done.
     * @throws TaskNotFoundException thrown when the program is unable to find a task at the index
     *                               specified by the user
     * @throws TaskIsDoneException   Exception thrown when user tries to mark a task as done which is done.
     */
    public Task markTaskAsDone(int taskNumber)
            throws TaskNotFoundException, TaskIsDoneException {
        Task task = getTaskFromNumber(taskNumber);
        task.setDone();
        return task;
    }

    /**
     * Gets a task based on the number assigned to it.
     *
     * @param taskNumber number assigned to the task.
     * @return The task with the given number. Null if not found.
     * @throws TaskNotFoundException thrown when the program is unable to find a task at the index
     *                               specified by the user
     */
    public Task getTaskFromNumber(int taskNumber) throws TaskNotFoundException {
        for (Task t : tasks) {
            if (t.getNumber() == taskNumber) {
                return t;
            }
        }
        throw new TaskNotFoundException(taskNumber);
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

    /**
     * Gets the max index.
     *
     * @return Max index
     */
    public int getMaxNumber() {
        return maxNumber;
    }


    /**
     * Determines if two objects have the same attributes.
     *
     * @param o object
     * @return true if the two objects have the same attributes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskList)) {
            return false;
        }
        TaskList taskList = (TaskList) o;

        return maxNumber == taskList.maxNumber && getTasks().equals(taskList.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTasks(), maxNumber);
    }
}
