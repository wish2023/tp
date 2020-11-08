package athena.timetable;

import athena.TaskList;
import athena.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.function.Function;

public class TimetableDrawer {

    public static final String DAY_BOX_HORIZONTAL_BORDER = "-------";
    public static final String BOX_CORNER = "+";
    public static final String TIME_HEADER_HORIZONTAL_BORDER = "---------";
    public static final String TASK_BOX_HORIZONTAL_BORDER = TIME_HEADER_HORIZONTAL_BORDER + "-";
    public static final String DATE_BOX = "| %02d/%02d |";
    public static final String TASK_NUMBER_LABEL = "[%d]";
    public static final String DAY_BOX = "|  %s  |";
    public static final String EMPTY_TASK_BOX = "          |";
    public static final String TASK_BOX = " %s|";

    private final TreeMap<LocalDate, TimetableDay> timetableDayMap;

    public TimetableDrawer(Timetable timetable) {
        this.timetableDayMap = timetable.getTimetableDayMap();
    }

    /**
     * Generates the timetable header containing hour marks.
     * For example, +------08------09------10------11------+
     *
     * @param startHour The starting hour in 24-hour representation.
     * @param endHour   The ending hour in 24-hour representation.
     * @return A string containing the timetable header with hour marks.
     */
    private String drawTimetableTimeHeader(int startHour, int endHour) {
        StringBuilder header = new StringBuilder();

        header.append(BOX_CORNER + DAY_BOX_HORIZONTAL_BORDER);
        for (int hour = startHour; hour < endHour; hour++) {
            String paddedHourString = String.format("%02d", hour);
            header.append(paddedHourString).append(TIME_HEADER_HORIZONTAL_BORDER);
        }
        header.append(BOX_CORNER + "\n");

        return header.toString();
    }

    /**
     * Generates the bottom border for a day in the timetable.
     *
     * @param startHour The starting hour in 24-hour representation.
     * @param endHour   The ending hour in 24-hour representation.
     * @return A string that represents the bottom border for a day in the timetable.
     */
    private String drawBottomBorder(int startHour, int endHour) {
        StringBuilder row = new StringBuilder();
        row.append(BOX_CORNER + DAY_BOX_HORIZONTAL_BORDER + BOX_CORNER);
        for (int i = startHour; i < endHour; i++) {
            row.append(TASK_BOX_HORIZONTAL_BORDER + BOX_CORNER);
        }
        row.append("\n");
        return row.toString();
    }

    /**
     * Utility method to fit a string into a given length.
     * If the string is shorter than desired, pad it with spaces.
     * If the string is longer than desired, truncate it and replace the end with "..".
     *
     * @param string The string to fit.
     * @param maxLen The desired length.
     * @return The string that is modified to fit into a given length.
     */
    private String shortenOrPadString(String string, int maxLen) {
        if (string.length() == maxLen) {
            return string;
        } else if (string.length() < maxLen) {
            return String.format("%" + -maxLen + "s", string);
        }
        return String.format("%s..", string.substring(0, maxLen - 2));
    }

    /**
     * Finds a task in a task list that starts at the given hour.
     *
     * @param taskList Task list to search through.
     * @param hour     Starting time of the task.
     * @return The task that starts at the given hour. Null if doesn't exist.
     */
    private Task findTaskAtHour(TaskList taskList, int hour) {
        for (Task task : taskList.getTasks()) {
            try {
                int taskHour = task.getTimeInfo().getStartTime().getHour();
                if (taskHour == hour) {
                    return task;
                }
            } catch (NullPointerException e) {
                //do nothing
            }
        }
        return null;
    }

    /**
     * Generates a row for a day in the timetable containing the task information desired.
     *
     * @param day            The TimetableDay object for this day.
     * @param startHour      The starting hour in 24-hour representation.
     * @param endHour        The ending hour in 24-hour representation.
     * @param taskInfoWriter A Function that extracts the information desired from a task.
     * @return A string containing the task information desired.
     */
    private String drawTimetableDayRow(TimetableDay day, int startHour, int endHour,
                                       Function<Task, String> taskInfoWriter) {
        StringBuilder row = new StringBuilder();

        for (int hour = startHour; hour < endHour; hour++) {
            Task task = findTaskAtHour(day.getTaskList(), hour);
            if (task == null) {
                row.append(EMPTY_TASK_BOX);
                continue;
            }

            int duration = task.getTimeInfo().getDuration();
            // TODO: better handle tasks exceeding sleep time, currently it just cuts off at sleep time
            duration = Math.min(duration, endHour - hour);
            hour += duration - 1;
            int boxWidth = duration * (TASK_BOX_HORIZONTAL_BORDER + BOX_CORNER).length() - 2;
            row.append(String.format(TASK_BOX, shortenOrPadString(taskInfoWriter.apply(task), boxWidth)));
        }

        return row.toString();
    }

    /**
     * Generates the first row for a day in the timetable, which contains the name of the day and the task names.
     *
     * @param day       The TimetableDay object for this day.
     * @param startHour The starting hour in 24-hour representation.
     * @param endHour   The ending hour in 24-hour representation.
     * @return A string containing name of the day and the task names.
     */
    private String drawTimetableDayFirstRow(TimetableDay day, int startHour, int endHour) {
        String dayShortName = day.getDate().getDayOfWeek().toString().substring(0, 3).toUpperCase();
        String row = String.format(DAY_BOX, dayShortName);
        row += drawTimetableDayRow(day, startHour, endHour, Task::getName);
        row += "\n";
        return row;
    }

    /**
     * Generates the second row for a day in the timetable, which contains the date of the day and the task numbers.
     *
     * @param day       The TimetableDay object for this day.
     * @param startHour The starting hour in 24-hour representation.
     * @param endHour   The ending hour in 24-hour representation.
     * @return A string containing date of the day and the task numbers.
     */
    private String drawTimetableDaySecondRow(TimetableDay day, int startHour, int endHour) {
        LocalDate date = day.getDate();
        String row = String.format(DATE_BOX, date.getDayOfMonth(), date.getMonthValue());

        row += drawTimetableDayRow(day, startHour, endHour, task -> String.format(TASK_NUMBER_LABEL, task.getNumber()));

        row += "\n";
        return row;
    }

    /**
     * Generates the row representing a day in the timetable.
     *
     * @param day       The TimetableDay object for this day.
     * @param startHour The starting hour in 24-hour representation.
     * @param endHour   The ending hour in 24-hour representation.
     * @return A string containing information about a day in the timetable.
     */
    private String drawTimetableDay(TimetableDay day, int startHour, int endHour) {
        String row = "";
        row += drawTimetableDayFirstRow(day, startHour, endHour);
        row += drawTimetableDaySecondRow(day, startHour, endHour);
        row += drawBottomBorder(startHour, endHour);
        return row;
    }

    /**
     * Draws the timetable.
     *
     * @param dates      Dates to include in the timetable.
     * @param wakeUpHour Starting hour of the timetable.
     * @param sleepHour  Ending hour of the timetable.
     * @return String representation of the timetable.
     */
    public String drawTimetable(ArrayList<LocalDate> dates, int wakeUpHour, int sleepHour) {
        StringBuilder result = new StringBuilder(drawTimetableTimeHeader(wakeUpHour, sleepHour));
        for (LocalDate date : dates) {
            if (timetableDayMap.containsKey(date)) {
                result.append(drawTimetableDay(timetableDayMap.get(date), wakeUpHour, sleepHour));
            } else {
                result.append(drawTimetableDay(new TimetableDay(date), wakeUpHour, sleepHour));
            }
        }
        return result.toString();
    }
}
