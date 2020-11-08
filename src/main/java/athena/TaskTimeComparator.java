package athena;

import athena.task.Task;

import java.util.Comparator;

/**
 *  Sorts the tasks descending order according to time.
 */
public class TaskTimeComparator implements Comparator<Task> {

    public int compare(Task o1, Task o2) {
        int t1 = o1.getTimeInfo().getDuration();
        int t2 = o2.getTimeInfo().getDuration();
        if (t1 > t2) {
            return -1;
        } else if (t1 < t2) {
            return 1;
        }
        return 0;
    }
}
