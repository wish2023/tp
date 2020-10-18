package athena.task;

import athena.Importance;
import athena.Recurrence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;

/**
 * Handles task objects.
 */
public class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    private String name;

    private String startTime;
    private String duration;
    private String deadline;

    private String recurrence;
    private LocalDate recurrenceDate;

    private boolean isDone = false;
    private Importance importance;
    private String notes;
    private int number;

    /**
     * Determines if the task is done.
     * @return string representing if the task is done
     */
    private String getStatus() {
        return (isDone ? YES : NO);
    }

    /**
     * Constructor for the task class.
     * @param name name of the task
     * @param startTime starting time of the task
     * @param duration how long the task is scheduled to last for
     * @param deadline when the task is due
     * @param recurrence when the task repeats
     * @param importance importance of the task
     * @param notes additional notes for the task
     * @param number task number
     */
    public Task(String name, String startTime, String duration, String deadline,
                String recurrence, Importance importance, String notes, int number) {
        this.name = name;
        assert !this.name.equals("");
        this.startTime = startTime;
        assert !this.startTime.equals("");
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.importance = importance;
        this.notes = notes;
        this.number = number;

        if (recurrence.toUpperCase().equals(Recurrence.TODAY.toString())) {
            recurrenceDate = LocalDate.now();
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                recurrenceDate = LocalDate.parse(recurrence, formatter);
            } catch (DateTimeParseException e) {
                // TODO: Handle this properly
                System.out.println("I don't understand the date you gave. So I set it to today.");
                recurrenceDate = LocalDate.now();
            }
        }
    }

    /**
     * Edits the features of the task.
     *
     * @param name       New task name
     * @param startTime  New task start time
     * @param duration   New task duration
     * @param deadline   New task deadline
     * @param recurrence New task recurrence
     * @param importance New task importance
     * @param notes      New task notes
     */
    public void edit(String name, String startTime, String duration,
                     String deadline, String recurrence, Importance importance, String notes) {
        this.name = name;
        assert !this.name.equals("");
        this.startTime = startTime;
        assert !this.startTime.equals("");
        this.duration = duration;
        assert !this.duration.equals("");
        this.deadline = deadline;
        assert !this.deadline.equals("");

        if (!recurrence.equals(null)) {
            this.recurrence = recurrence;
            if (recurrence.toUpperCase().equals(Recurrence.TODAY.toString())) {
                recurrenceDate = LocalDate.now();
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                recurrenceDate = LocalDate.parse(recurrence, formatter);
            }
        }
        assert !this.recurrenceDate.equals(null);

        this.importance = importance;
        assert this.importance != null;

        if (!notes.equals(null)) {
            this.notes = notes;
        }
    }

    /**
     * Return the importance of the task.
     *
     * @return Importance of task
     */
    public Importance getImportance() {
        return importance;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns start time of the task.
     * @return Start time of task
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns duration of the task.
     * @return Duration of task
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns due date of the task.
     * @return Due date of task
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns if the task is done.
     * @return Status of task completion
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns task notes.
     * @return Task notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Returns when the task repeats.
     * @return When the task repeats
     */
    public String getRecurrence() {
        return recurrence;
    }

    /**
     * Returns when the task repeats as a LocalDate object.
     * @return When the task repeats as a LocalDate object
     */
    public LocalDate getDate() {
        return recurrenceDate;
    }

    /**
     * Returns the task number.
     * @return Task number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the task number.
     * @param number Number that the user wants to set the task to.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Restores a task that the user has just deleted.
     * @return String representing details of the task the user wants to restore
     */
    public String getTaskRestore() {
        String taskRestore = "add n/" + this.getName() + " t/" + this.getStartTime() + " d/" + this.getDuration()
                + " D/" + this.getDeadline() + " r/" + this.getRecurrence() + " t/" + this.getImportance()
                + " a/" + this.getNotes();
        return taskRestore;
    }

    /**
     * Converts a task object to a string.
     * @return task as a string
     */
    @Override
    public String toString() {
        return getStatus() + " " + name + " at " + startTime + " finish by " + deadline;
    }

    /**
     * Converts a task object to a string of details.
     * @return task as a string
     */
    public String toStringDetails() {
        return "\n Done? " + getStatus() + "\n Name: " + name + "\n Start time: " + startTime
                + "\n Deadline: " + deadline + "\n Duration: " + duration + "\n Recurrence: " + recurrence
                + "\n Importance: " + importance + "\n Notes: " + notes;
    }

    /**
     * Compare this task with another object.
     *
     * @param o Object to compare with.
     * @return Whether the object compared with is also a task and has the exact same properties.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone
                && number == task.number
                && Objects.equals(name, task.name)
                && Objects.equals(startTime, task.startTime)
                && Objects.equals(duration, task.duration)
                && Objects.equals(deadline, task.deadline)
                && Objects.equals(recurrence, task.recurrence)
                && importance == task.importance
                && Objects.equals(notes, task.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, duration, deadline, recurrence, isDone, importance, notes, number);
    }
}
