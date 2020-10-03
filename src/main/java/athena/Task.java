package athena;

public class Task {
    public static final String TICK = "[\u2713]";
    public static final String CROSS = "[\u2718]";
    private String description;

    private String startTime;
    private int duration = 1;
    private String deadline;

    private boolean isDone = false;
    private Importance importance = Importance.MEDIUM;
    private String notes = null;


    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks the task as done
     *
     */
    public void setDone() {
        isDone = true;
    }


    /**
     * Returns the description of the task
     *
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }
}
