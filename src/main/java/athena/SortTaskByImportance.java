package athena;

import athena.task.Task;

import java.util.Comparator;

public class SortTaskByImportance implements Comparator<Task> {


    @Override
    public int compare(Task o1, Task o2) {
        Importance i1 = o1.getImportance();
        Importance i2 = o2.getImportance();
        if (i1 == i2) {
            return 0;
        } else if (i1 == Importance.HIGH) {
            return 1;
        } else if (i2 == Importance.HIGH) {
            return -1;
        } else if (i1 == Importance.MEDIUM) {
            return 1;
        } else if (i2 == Importance.MEDIUM) {
            return -1;
        }
        return 0;

    }
}


