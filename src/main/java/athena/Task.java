package athena;

public class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    private String name;

    private String startTime;
    private String duration;
    private String deadline;

    private String recurrence;
    private boolean isDone = false;
    private String importance;
    private String notes;

    private String getStatus() {
        return (isDone ? YES : NO);
    }

    public Task(String name, String startTime, String duration, String deadline,
                String recurrence, String importance, String notes) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.importance = importance;
        this.notes = notes;
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
                     String deadline, String recurrence, String importance, String notes) {
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
    public String getImportance() {
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

}
