package athena;

public class Task {
    public static final String YES = "Y";
    public static final String NO = "N";
    private String name;

    private String startTime;
    private int duration = 1;
    private String deadline;

    private boolean isDone = false;
    private Importance importance = Importance.MEDIUM;
    private String notes = null;

    private String getStatus() {
        return (isDone ? YES : NO);
    }


    public Task(String name, String startTime, String deadline) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getName() +  ". Complete by " + deadline;
    }

}
