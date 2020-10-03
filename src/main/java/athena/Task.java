package athena;

public class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    private String description;

    private String startTime;
    private int duration = 1;
    private String deadline;

    private boolean isDone = false;
    private Importance importance = Importance.MEDIUM;
    private String notes = null;

    private String getStatus() {
        return (isDone? YES: NO);
    }


    public Task(String description, String startTime, String deadline) {
        this.description = description;
        this.startTime = startTime;
        this.deadline = deadline;
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
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription() +  ". Complete by " + deadline;
    }

}
