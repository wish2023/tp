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
     * @param name New task name
     * @param startTime New task start time
     * @param duration New task duration
     * @param deadline New task deadline
     * @param recurrence New task recurrence
     * @param importance New task importance
     * @param notes New task notes
     */
    public void changeAttributesTo(String name, String startTime, String duration,
            String deadline, String recurrence, String importance, String notes) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.importance = importance;
        this.notes = notes;
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
     *
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

    @Override
    public String toString() {
        return getStatus() + " " + getName() +  ". Complete by " + deadline;
    }

}
