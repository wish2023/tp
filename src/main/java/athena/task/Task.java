package athena.task;

import athena.Importance;
import athena.Recurrence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

public class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    private String name;

    private String startTime;
    private String duration;
    private String deadline;

    private String recurrence;
    private LocalDate recurrenceDate = null;

    private boolean isDone = false;
    private Importance importance;
    private String notes;
    private int index;

    private String getStatus() {
        return (isDone ? YES : NO);
    }

    public Task(String name, String startTime, String duration, String deadline,
                String recurrence, Importance importance, String notes, int index) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.importance = importance;
        this.notes = notes;
        this.index = index;

        if (recurrence.toUpperCase().equals(Recurrence.TODAY.toString())) {
            recurrenceDate = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            recurrenceDate = LocalDate.parse(recurrence, formatter);
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
        if (!name.equals(null)) {
            this.name = name;
        }
        if (!startTime.equals(null)) {
            this.startTime = startTime;
        }
        if (!duration.equals(null)) {
            this.duration = duration;
        }
        if (!deadline.equals(null)) {
            this.deadline = deadline;
        }
        if (!recurrence.equals(null)) {
            this.recurrence = recurrence;
            if (recurrence.toUpperCase().equals(Recurrence.TODAY.toString())) {
                recurrenceDate = LocalDate.now();
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                recurrenceDate = LocalDate.parse(recurrence, formatter);
            }
        }
        if (!importance.equals(null)) {
            this.importance = importance;
        }
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

    public String getStartTime() {
        return startTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }


    public String getNotes() {
        return notes;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public LocalDate getDate() {
        return recurrenceDate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTaskRestore() {
        String taskRestore = "add n/" + this.getName() + " t/" + this.getStartTime() + " d/" + this.getDuration()
                + " D/" + this.getDeadline() + " r/" + this.getRecurrence() + " t/" + this.getImportance()
                + " a/" + this.getNotes();
        return taskRestore;
    }

    @Override
    public String toString() {
        return getStatus() + " " + name + " at " + startTime + " finish by " + deadline;
    }

    /**
     * Checks if 2 tasks have the exact same properties.
     *
     * @param o Object to compare with.
     * @return Whether the tasks have the exact same properties.
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
                && index == task.index
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
        return Objects.hash(name, startTime, duration, deadline, recurrence, isDone, importance, notes, index);
    }
}
