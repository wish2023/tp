package athena;

import athena.task.Task;

import java.util.Comparator;

public class TaskTimeComparator implements Comparator<Task> {

    public int compare(Task o1, Task o2) {
        Integer t1 = o1.getTimeInfo().getDuration();
        Integer t2 = o1.getTimeInfo().getDuration();
        if (t1 > t2) {
            return 1;
        } else if (t1 < t2) {
            return -1;
        }
        return 0;
    }
}
