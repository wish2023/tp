package athena.task;

import athena.exceptions.command.TaskTooLongException;
import athena.exceptions.command.InvalidDeadlineException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.TaskDuringSleepTimeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Stores information related to time.
 * examples include startTime, duration, deadline
 * Recurrence and recurrenceDate can also be stored here
 */
public class TimeData implements Comparable<TimeData> {

    private static final int DATE_TIME_FORMAT = 5;
    private static final LocalTime WAKE_TIME = LocalTime.of(8,0);
    private static final LocalTime SLEEP_TIME = LocalTime.of(0,0);
    public static final String NO_DEADLINE = "No deadline";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DD_MM = "dd-MM";
    public static final String DASH = "-";
    private boolean isFlexible;
    private LocalTime startTime = null;
    private int duration;
    private LocalTime endTime;
    private String deadline;
    private LocalDate deadlineDate;
    private String recurrence;
    private ArrayList<LocalDate> recurrenceDates = new ArrayList<>();

    /**
     * Constructor for timeData class.
     *
     * @param isFlexible    time flexibility
     * @param startTime     starting time of task
     * @param duration      how long the task is scheduled to last for
     * @param deadline      when the task is due
     * @param recurrence    when the task occurs/repeats
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     * @throws InvalidRecurrenceException   Exception thrown when user mistypes recurrence
     * @throws InvalidDeadlineException     Exception thrown when user mistypes deadline
     */
    public TimeData(boolean isFlexible, LocalTime startTime, int duration, String deadline, String recurrence)
            throws TaskDuringSleepTimeException, InvalidDeadlineException, InvalidRecurrenceException {
        if (startTime != null) {
            this.startTime = startTime;
            this.endTime = startTime.plusHours(duration);
            if (isClashWithSleep()) {
                throw new TaskDuringSleepTimeException();
            }
        }
        this.isFlexible = isFlexible;
        this.duration = duration;
        this.deadline = deadline;
        setDeadlineDate(deadline);
        this.recurrence = recurrence;
        setRecurrence(recurrence);
        setTime(startTime);
    }

    /**
     * Constructor for time class.
     *
     * @param isFlexible    time flexibility
     * @param startTime     starting time of task
     * @param duration      how long the task is scheduled to last for
     * @param deadline      when the task is due
     * @param recurrence    when the task occurs/repeats
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     * @throws InvalidRecurrenceException   Exception thrown when user mistypes recurrence
     * @throws InvalidDeadlineException     Exception thrown when user mistypes deadline
     */
    public TimeData(Boolean isFlexible, String startTime, String duration, String deadline, String recurrence)
            throws TaskDuringSleepTimeException, DateTimeParseException, TaskTooLongException,
            InvalidRecurrenceException, InvalidDeadlineException {
        setIsFlexible(isFlexible);
        setDuration(duration);
        setDeadline(deadline);
        setDeadlineDate(deadline);
        this.recurrence = recurrence;
        setRecurrence(recurrence);
        if (startTime.length() > 0) {
            this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
            this.endTime = this.startTime.plusHours(this.duration);
            if (isClashWithSleep()) {
                throw new TaskDuringSleepTimeException();
            } else if (this.duration > 16) {
                throw new TaskTooLongException(this.duration);
            }
        }
    }

    /**
     * Edits attributes of time information of task.
     *
     * @param startTime     starting time of task
     * @param duration      how long the task is scheduled to last for
     * @param deadline      when the task is due
     * @param recurrence    when the task occurs/repeats
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     * @throws InvalidRecurrenceException   Exception thrown when user mistypes recurrence
     * @throws InvalidDeadlineException     Exception thrown when user mistypes deadline
     */
    public void edit(String startTime, String duration, String deadline, String recurrence)
            throws TaskDuringSleepTimeException,
            InvalidRecurrenceException, InvalidDeadlineException {
        setTime(startTime);
        setDuration(duration);
        setDeadline(deadline);
        setDeadlineDate(deadline);
        resetRecurrence();
        setRecurrence(recurrence);
        assertDates();
    }

    /**
     * Ensures dates are non empty.
     */
    private void assertDates() {
        assert !this.recurrenceDates.equals(null);
    }

    /**
     * Checks if starting time of task is empty.
     *
     * @param startTime starting time of task
     * @return whether starting time of task is empty
     */
    private boolean isNotEmpty(String startTime) {
        return startTime.length() > 0;
    }

    /**
     * Sets the start time of a task.
     *
     * @param startTime starting time of task
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     */
    private void setTime(String startTime) throws TaskDuringSleepTimeException {
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
        setEndTime();
    }

    /**
     * Sets the start time of a task.
     *
     * @param startTime starting time of task
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     */
    private void setTime(LocalTime startTime) throws TaskDuringSleepTimeException {
        if (startTime != null) {
            this.startTime = startTime;
            setEndTime();
        }
    }

    /**
     * Sets the ending time of a task.
     *
     * @throws TaskDuringSleepTimeException Exception thrown when task clashes with sleep time
     */
    private void setEndTime() throws TaskDuringSleepTimeException {
        endTime = startTime.plusHours(duration);
        if (isClashWithSleep()) {
            throw new TaskDuringSleepTimeException();
        }
    }

    /**
     * Sets the deadline date of a task.
     *
     * @param deadline when the task is due
     */
    private void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Sets the duration of a task.
     *
     * @param duration how long the task is scheduled to last for
     */
    private void setDuration(String duration) {
        this.duration = Integer.parseInt(duration);
    }

    /**
     * Sets the duration of a task.
     *
     * @param duration how long the task is scheduled to last for
     */
    private void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets the flexibility of a task.
     *
     * @param isFlexible time flexibility
     */
    private void setIsFlexible(Boolean isFlexible) {
        this.isFlexible = isFlexible;
    }

    /**
     * Checks if the task has a flexible time.
     *
     * @return whether task is flexible.
     */
    public boolean getFlexible() {
        return isFlexible;
    }

    /**
     * Checks if a task is between 12am and 8am.
     *
     * @return whether the task clashes with sleep time
     */
    private boolean isClashWithSleep() {
        return !isNoClashWithSleep();
    }

    /**
     * Checks if a task is not between 12am and 8am.
     *
     * @return whether the task doesn't clash with sleep time
     */
    private boolean isNoClashWithSleep() {
        return startTime.compareTo(WAKE_TIME) >= 0
                && !(endTime.compareTo(WAKE_TIME) < 0 && endTime.compareTo(SLEEP_TIME) > 0)
                && duration <= 16;
    }

    /**
     * Creates a deep clone of a Time object.
     *
     * @return Cloned Time object
     */
    public TimeData getClone() {
        try {
            return new TimeData(isFlexible, startTime, duration, deadline, recurrence);
        } catch (TaskDuringSleepTimeException | InvalidRecurrenceException | InvalidDeadlineException e) {
            assert false;
            return null;
        }
    }


    /**
     * Adds all dates for when task is supposed to occur in recurrenceDates.
     *
     * @param recurrence when the task occurs/repeats
     * @throws InvalidRecurrenceException Exception thrown when user mistypes recurrence
     */
    public void setRecurrence(String recurrence) throws InvalidRecurrenceException {
        switch (recurrence.toUpperCase()) {
        case "MONDAY":
            LocalDate mondayDate = getFirstDateMatchingDay(DayOfWeek.MONDAY);
            addDates(mondayDate);
            break;
        case "TUESDAY":
            LocalDate tuesdayDate = getFirstDateMatchingDay(DayOfWeek.TUESDAY);
            addDates(tuesdayDate);
            break;
        case "WEDNESDAY":
            LocalDate wednesdayDate = getFirstDateMatchingDay(DayOfWeek.WEDNESDAY);
            addDates(wednesdayDate);
            break;
        case "THURSDAY":
            LocalDate thursdayDate = getFirstDateMatchingDay(DayOfWeek.THURSDAY);
            addDates(thursdayDate);
            break;
        case "FRIDAY":
            LocalDate fridayDate = getFirstDateMatchingDay(DayOfWeek.FRIDAY);
            addDates(fridayDate);
            break;
        case "SATURDAY":
            LocalDate saturdayDate = getFirstDateMatchingDay(DayOfWeek.SATURDAY);
            addDates(saturdayDate);
            break;
        case "SUNDAY":
            LocalDate sundayDate = getFirstDateMatchingDay(DayOfWeek.SUNDAY);
            addDates(sundayDate);
            break;
        default:
            setRecurrenceDate(recurrence);
            return;
        }
        this.recurrence = recurrence;
    }

    /**
     * Adds dates of tasks in recurrenceDates for 10 weeks.
     *
     * @param startDate the start date of the task
     */
    private void addDates(LocalDate startDate) {
        for (int i = 0; i < 10; i++) {
            recurrenceDates.add(startDate.plusWeeks(i));
        }
    }

    /**
     * Returns the date of the first indicated day of the week.
     *
     * @param dayOfWeek day of the week whose first date needs to be found
     * @return first date from today of day of week
     */
    private LocalDate getFirstDateMatchingDay(DayOfWeek dayOfWeek) {
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 6; i++) {
            if (startDate.getDayOfWeek().equals(dayOfWeek)) {
                break;
            } else {
                startDate = startDate.plusDays(1);
            }
        }
        return startDate;
    }

    /**
     * Adds task date to recurrenceDates.
     *
     * @param recurrence Date task occurs
     * @throws InvalidRecurrenceException Exception thrown when user mistypes recurrence
     */
    private void setRecurrenceDate(String recurrence) throws InvalidRecurrenceException {
        try {
            addSingleDate(recurrence);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        } catch (NumberFormatException e) {
            throw new InvalidRecurrenceException();
        }
    }

    /**
     * Adds a date to recurrenceDates.
     *
     * @param recurrence Date of task occurrence
     */
    private void addSingleDate(String recurrence) {
        LocalDate date = getDate(recurrence);
        if (recurrence.length() == "dd-MM".length()) {
            this.recurrence = recurrence + "-" + date.getYear();
        } else {
            this.recurrence = recurrence;
        }
        recurrenceDates.add(date);
    }

    /**
     * Sets the deadline of task.
     *
     * @param deadline Date of deadline for task
     * @throws InvalidDeadlineException if user mistypes deadline date
     */
    private void setDeadlineDate(String deadline) throws InvalidDeadlineException {
        if (!deadline.equals(NO_DEADLINE)) {
            trySetHardDeadline(deadline);
        }
    }

    /**
     * Attempts to set a hard deadline for a task.
     *
     * @param deadline Date of deadline for task
     * @throws InvalidDeadlineException if user mistypes deadline date
     */
    private void trySetHardDeadline(String deadline) throws InvalidDeadlineException {
        try {
            setHardDeadline(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        } catch (NumberFormatException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Sets a hard deadline for task.
     *
     * @param deadline Date of deadline for task
     */
    private void setHardDeadline(String deadline) {
        LocalDate date = getDate(deadline);
        if (deadline.length() == "dd-MM".length()) {
            this.deadline = deadline + "-" + date.getYear();
        }
        this.deadlineDate = date;
    }

    /**
     * Converts a date from String to LocalDate.
     *
     * @param dateString Date to be returned in a string
     * @return The converted date
     */
    private LocalDate getDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
        LocalDate date;
        if (dateString.length() == DD_MM.length()) {
            int year = getYear(dateString);
            date = LocalDate.parse(dateString + DASH
                    + year, formatter);
        } else {
            date = LocalDate.parse(dateString, formatter);
        }
        return date;
    }


    /**
     * Clears all dates in recurrenceDates.
     */
    public void resetRecurrence() {
        recurrenceDates.clear();
    }

    /**
     * Extracts the month of task occurrence.
     *
     * @param recurrence Date of task occurrence
     * @return month of task
     */
    private int getMonth(String recurrence) {
        return Integer.parseInt(recurrence.substring(3, 5));
    }


    /**
     * Extracts the day of month of task occurrence.
     *
     * @param recurrence Date of task occurrence
     * @return day of task
     */
    private int getDay(String recurrence) {
        return Integer.parseInt(recurrence.substring(0, 2));
    }


    /**
     * Extracts the year of task occurrence.
     *
     * @param recurrence Date of task occurrence
     * @return year of task
     */
    private int getYear(String recurrence) {
        int month = getMonth(recurrence);
        int day = getDay(recurrence);
        int year = calculateYear(month, day);
        return year;
    }

    /**
     * Calculates the year of a task based on month and day.
     *
     * @param month     Month of task occurrence
     * @param day       Day of task occurrence
     * @return year of task occurrence.
     */
    private int calculateYear(int month, int day) {
        LocalDate currentDate = LocalDate.now();
        int year;
        if (isCurrentMonthAhead(currentDate, month)) {
            year = currentDate.getYear() + 1;
        } else if (isCurrentDayAhead(currentDate, month, day)) {
            year = currentDate.getYear() + 1;
        } else {
            year = currentDate.getYear();
        }
        return year;
    }

    /**
     * Checks if current day is ahead of task day in calender year.
     *
     * @param currentDate   Today's date
     * @param month         Month of task occurrence
     * @param day           Day of task occurrence
     * @return whether current day is ahead of task day
     */
    private boolean isCurrentDayAhead(LocalDate currentDate, int month, int day) {
        return currentDate.getMonthValue() == month
                && currentDate.getDayOfMonth() > day;
    }


    /**
     * Checks if current month is ahead of task month in calender year.
     *
     * @param currentDate   Today's date
     * @param month         Month of task occurrence
     * @return whether current month is ahead of task month
     */
    private boolean isCurrentMonthAhead(LocalDate currentDate, int month) {
        return currentDate.getMonthValue() > month;
    }

    /**
     * Returns the dates of when the task will occur.
     *
     * @return dates the task will occur
     */
    public ArrayList<LocalDate> getRecurrenceDates() {
        return recurrenceDates;
    }


    /**
     * Returns the starting time of a task.
     *
     * @return the start time of the task
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the time a task is expected to finish.
     *
     * @return ending time of a task
     */
    public LocalTime getEndTime() {
        return endTime;
    }


    /**
     * Set the start time of the task.
     *
     * @param startTime Start time of task
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }


    /**
     * Converts the start time to a string.
     *
     * @return Start time of task as a string.
     */
    public String getStartTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        if (startTime == null) {
            return "";
        }
        return startTime.format(timeFormatter);
    }

    /**
     * Returns the date or occurrence of task.
     *
     * @return when the task occurs/repeats
     */
    public String getRecurrence() {
        return recurrence;
    }

    /**
     * Returns duration of the task.
     *
     * @return Duration of task
     */
    public int getDuration() {
        return duration;
    }


    /**
     * Converts the duration to a string.
     *
     * @return Duration of task as a string.
     */
    public String getDurationString() {
        return Integer.toString(duration);
    }


    /**
     * Returns due date of the task.
     *
     * @return Due date of task
     */
    public String getDeadline() {
        return deadline;
    }


    @Override
    public int compareTo(TimeData o) {
        return 0;
    }


    /**
     * Removes a date from recurrenceDates.
     *
     * @param date Date to be removed
     */
    public void removeDate(LocalDate date) {
        recurrenceDates.remove(date);
    }

    /**
     * Compare this time with another object.
     *
     * @param o Object to compare with.
     * @return Whether the object compared with is also a time and has the exact same properties.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeData time = (TimeData) o;

        return isFlexible == time.isFlexible
                && Objects.equals(startTime, time.startTime)
                && Objects.equals(duration, time.duration)
                && Objects.equals(deadline, time.deadline)
                && Objects.equals(recurrenceDates, time.recurrenceDates);
    }

}
