package athena;

import athena.task.Task;
import athena.task.Time;

import java.util.ArrayList;
import java.util.Collections;

public class Log {
    private ArrayList<Integer> numberList;
    private int size;
    private int start;
    private int end;
    private ArrayList<Task> carryOverTasks = new ArrayList<>();


    /**
     * Makes a record of the task allocation.
     *
     * @param start start time of log
     * @param end end time of log
     */
    public Log(int start, int end) {
        this.size = end - start;
        this.numberList = new ArrayList<Integer>((Collections.nCopies(size, -1)));
        this.start = start;
        this.end = end;
    }

    /**
     * Places the taskNumber in the log.
     *
     * @param pos position of the number in the record
     * @param number taskNumber of the associated task
     */
    public void setNumber(int pos, int number) {
        numberList.set(pos, number);
    }

    public ArrayList<Integer> getNumberList() {
        return this.numberList;
    }

    public void setNumberList(ArrayList<Task> taskList) {
        int space = end - start;
        for (Task currTask : taskList) {
            int span = currTask.getTimeInfo().getDuration();
            if (span <= space) {
                placeTask(space, currTask, span);
                space -= span;
            }
            if (space == 0) {
                break;
            }
        }
    }

    private void placeTask(int space, Task currTask, int span) {
        int taskNumber = currTask.getNumber();
        for (int i = 0; i < span; i++) {
            int relativePos = end - space - start;
            numberList.set(i + relativePos, taskNumber);
        }
    }

    public void setFixedTasks(ArrayList<Task> fixedTaskList) {
        for (Task currTask : fixedTaskList) {
            int tag = currTask.getNumber();
            Time timeInfo = currTask.getTimeInfo();
            for (int i = 0; i < timeInfo.getDuration(); i++) {
                this.setNumber(timeInfo.getStartTime().getHour() + i, tag);
            }
        }

    }

    public boolean hasSpace() {
        return numberList.contains(-1);
    }

    public int getSpaceNumber() {
        return numberList.indexOf(-1);
    }


    public int getStart(int start) {
        return numberList.get(start);
    }

    public ArrayList<Task> getCarryOverTasks() {
        return this.carryOverTasks;
    }

    public Log(TimeSlot currSlot, ArrayList<Task> undefinedTimeTasks) {
        this.start = currSlot.getStart();
        this.end = currSlot.getEnd();
        Log currentLog = new Log(currSlot.getStart(), currSlot.getEnd());
        Log bestLog = currentLog;
        boolean hasUsableVacancy = true;
        while (hasUsableVacancy) {
            currentLog.setNumberList(undefinedTimeTasks);
            hasUsableVacancy = currentLog.hasSpace();
            if ((bestLog.getSpaceNumber() < currentLog.getSpaceNumber()) | !hasUsableVacancy) {
                bestLog = currentLog;
            }
            if (!undefinedTimeTasks.isEmpty()) {
                carryOverTasks.add(undefinedTimeTasks.get(0));
                undefinedTimeTasks.remove(0);
            } else {
                break;
            }
        }
        this.numberList = bestLog.getNumberList();
    }

    /**
     * Removes the assigned tasks from the remaining tasks.
     */
    public void removeAssignedTasks(TaskList taskList) {
        for (int taskNumber : this.numberList) {
            try {
                this.carryOverTasks.remove(taskList.getTaskFromNumber(taskNumber));
            } catch (Exception e) {
                //do nothing
            }
        }
    }

    /**
     * Assigns the times to the tasks.
     * @param start beginning position
     * @param bestLog log containing the order of the taskNumbers
     */
    public void populateLog(int start, Log bestLog) {
        int count = 0;
        for (int taskNumber : bestLog.getNumberList()) {
            this.getNumberList().set(count + start, taskNumber);
            count++;
        }
    }
}

