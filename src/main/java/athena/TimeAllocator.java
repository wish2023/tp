package athena;

import athena.exceptions.TaskNotFoundException;
import athena.task.Task;
import athena.task.Time;
import athena.task.taskfilter.FlexibleTimeFilter;
import athena.task.taskfilter.ForecastFilter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class TimeAllocator {
    TaskList flexibleTaskList;
    TaskList fixedTaskList;
    TaskList taskList;


    /**
     * This is run manually by the user to generate a timetable that they like.
     * option to auto assign at every input, reassignment at the end is possible
     * need to maintain the flexibility of the tasks to allow for multiple reruns
     *
     * @param tasks tasks in the current TaskList
     */
    public TimeAllocator(TaskList tasks) {
        this.flexibleTaskList = tasks.getFilteredList(new FlexibleTimeFilter(true));
        this.fixedTaskList = tasks.getFilteredList(new FlexibleTimeFilter(false));
        this.taskList = tasks;

    }

    public void runAllocate() throws TaskNotFoundException {
        LocalDate currDay = LocalDate.now();
        ForecastFilter forecast = new ForecastFilter(Forecast.DAY);
        for (int day = 0; day < 31; day++) {
            System.out.println(day);
            ArrayList<Integer> dayLog = new ArrayList<Integer>(Collections.nCopies(24, -1));
            ArrayList<Task> predefinedTimeTasks = getSortedFixedTasks(getFixedDayTasks(currDay));
            ArrayList<Task> undefinedTimeTasks = getSortedFixedTasks(getFlexibleDayTasks(currDay));

            for (Task currTask : predefinedTimeTasks) {
                int tag = currTask.getNumber();
                Time timeInfo = currTask.getTimeInfo();
                for (int i = 0; i < timeInfo.getDuration(); i++) {
                    dayLog.set(timeInfo.getStartTime().getHour(), tag);
                }
            }

            int start = 0;
            int sleep = 24;
            boolean done = false;
            while (!done) {
                int pos = start;
                while (dayLog.get(pos) != -1) {
                    pos++;
                    if (pos == sleep) {
                        done = true;
                        break;
                    }
                }
                int end = pos;
                while (dayLog.get(end) == -1) {
                    end++;
                    if (end == sleep) {
                        done = true;
                        break;
                    }
                }
                int space = end - pos;
                ArrayList<Integer> temp = new ArrayList<Integer>(Collections.nCopies(space, -1));
                ArrayList<Task> tasksToRemove = new ArrayList<Task>();
                while (temp.contains(-1)) {
                    for (Task currTask : undefinedTimeTasks) {
                        Integer span = currTask.getTimeInfo().getDuration();
                        if (span <= space) {
                            int taskNumber = currTask.getNumber();
                            this.flexibleTaskList.getTaskFromNumber(taskNumber)
                                    .getTimeInfo().setStartTime(LocalTime.of(end - space, 0));
                            for (int i = 0; i < span; i++) {
                                temp.set(i + end - space - pos, currTask.getNumber());
                            }
                            space -= span;
                            tasksToRemove.add(currTask);
                            if (space == 0) {
                                break;
                            }
                        }
                    }
                    removeAssignedTasks(undefinedTimeTasks, tasksToRemove);
                    setTempToPerm(dayLog, temp);
                    if (start == end) {
                        break;
                    } else {
                        start = end;
                    }
                    if (undefinedTimeTasks.size() == 0) {
                        done = true;
                        break;
                    }
                }

            }
            //System.out.println(fixedDayTasks.getTasks());
            //System.out.println(flexibleDayTasks.getTasks());
            currDay.plusDays(1);
        }

    }

    private ArrayList<Task> getSortedFixedTasks(TaskList taskList) {
        TaskList fixedDayTasks = taskList;
        ArrayList<Task> sortedTimeTasks = fixedDayTasks.getTasks();
        sortedTimeTasks.sort(new TaskTimeComparator());
        sortedTimeTasks.sort(new TaskImportanceComparator());
        return sortedTimeTasks;
    }

    private void removeAssignedTasks(ArrayList<Task> undefinedTimeTasks, ArrayList<Task> tasksToRemove) {
        for (Task remTask : tasksToRemove) {
            undefinedTimeTasks.remove(remTask);
        }
    }

    private void setTempToPerm(ArrayList<Integer> dayLog, ArrayList<Integer> temp) {
        int count = 0;
        for (int taskNumber : temp) {
            dayLog.set(count, taskNumber);
            count++;
        }
    }


    private TaskList getFixedDayTasks(LocalDate date) {
        ForecastFilter forecast = new ForecastFilter(Forecast.DAY);
        forecast.setDate(date);
        TaskList a = this.fixedTaskList.getFilteredList(forecast);
        return a;
    }

    private TaskList getFlexibleDayTasks(LocalDate date) {
        ForecastFilter forecast = new ForecastFilter(Forecast.DAY);
        forecast.setDate(date);
        TaskList a = this.flexibleTaskList.getFilteredList(forecast);
        return a;
    }

    // Psuedocode for allocation runs might refactor into a command class
    //    public void runAllocate() {
    //        Collections.sort(fixedTask, earlier());
    //        Collections.sort(flexibleTask,priority());
    //        //priority sorts according to importance(high to low) then duration(high to low)
    //        //might need toposort
    //
    //        for (int i = 0; i < fixedTask.size() - 1; i++) {
    //            currentTime = fixedTask.get(i).getStartTime() + fixedTask.get(i).getDuration();
    //            nextTime = fixedTask.get(i + 1).getStartTime();
    //            duration = nextTime-currentTime;
    //
    //            // select task with best fit into the time
    //            // select important task then long tasks then check for space
    //            // if there is no time left then continue
    //            // if not then choose another task that can go in
    //            // ?? not sure if want to go through the entire list to get 0 time wastage
    //            // failure cases will be unable to meet deadline
    //            // set time for flexible tasks
    //            // repeat until all flexible tasks are assigned
    //
    //
    //        }
    //    }


}
