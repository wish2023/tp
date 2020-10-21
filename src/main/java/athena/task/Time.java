package athena.task;

import athena.Recurrence; // Can delete recurrence enum since we don't use it anymore?

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
 * Stores information related to time.
 * examples include startTime, duration, deadline
 * Recurrence and recurrenceDate can also be stored here
 * setters and getter are needed
 * new things to add ifFlexibleTime to let the TimeAllocate change the time values
 */
public class Time implements Comparable<Time> {

    private static final int DATE_TIME_FORMAT = 5;
    private Boolean isFlexible;
    private String startTime;
    private String duration;
    private String deadline;

    private String recurrence;
    private ArrayList<LocalDate> recurrenceDates = new ArrayList<>();

    public Time(Boolean isFlexible, String startTime, String duration, String deadline, String recurrence) {
        this.isFlexible = isFlexible;
        this.startTime = startTime;
        assert !this.startTime.equals("");
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;

        setRecurrence(recurrence);
        assert recurrenceDates.size() != 0;
    }

    public Time getClone() {
        return new Time(isFlexible, startTime, duration, deadline, recurrence);
    }

    public void setRecurrence(String recurrence) {
        switch (recurrence.toUpperCase()) {
        case "TODAY":
            recurrenceDates.add(LocalDate.now());
            break;
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
            try {
                setRecurrenceDate(recurrence);
            } catch (DateTimeParseException e) {
                // TODO: Handle this properly
                System.out.println("I don't understand the date you gave. So I set it to today.");
                recurrenceDates.add(LocalDate.now());
            }
        }
    }

    private void addDates(LocalDate startDate) {
        for (int i = 0; i < 10; i++) { // Max number of weeks now is 10 to prevent infinite recurrence
            recurrenceDates.add(startDate.plusWeeks(i));
        }
    }

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

    private void setRecurrenceDate(String recurrence) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (recurrence.length() == "dd-MM".length()) {
            int year = getYear(recurrence);
            this.recurrence = recurrence + "-" + year;
            recurrenceDates.add(LocalDate.parse(recurrence + "-"
                    + Integer.toString(year), formatter));
        } else {
            recurrenceDates.add(LocalDate.parse(recurrence, formatter));
        }
    }

    private void resetRecurrence() {
        recurrenceDates.clear();
    }

    private int getMonth(String recurrence) {
        return Integer.parseInt(recurrence.substring(3,5));
    }

    private int getDay(String recurrence) {
        return Integer.parseInt(recurrence.substring(0,2));
    }

    private int getYear(String recurrence) {
        LocalDate currentDate = LocalDate.now();
        int month = getMonth(recurrence);
        int day = getDay(recurrence);
        int year;
        if (currentDate.getMonthValue() > month) {
            year = currentDate.getYear() + 1;
        } else if (currentDate.getMonthValue() == month
                && currentDate.getDayOfMonth() > day) {
            System.out.println("Hello!");
            year = currentDate.getYear() + 1;
        } else {
            year = currentDate.getYear();
        }
        return year;
    }

    public ArrayList<LocalDate> getRecurrenceDates() {
        return recurrenceDates;
    }

    /**
     * Returns start time of the task.
     *
     * @return Start time of task
     */
    public String getStartTime() {
        return startTime;
    }

    public String getRecurrence() {
        return recurrence;
    }

    /**
     * Returns duration of the task.
     *
     * @return Duration of task
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns due date of the task.
     *
     * @return Due date of task
     */
    public String getDeadline() {
        return deadline;
    }



    public Boolean getFlexible() {
        return isFlexible;
    }

    @Override
    public int compareTo(Time o) {
        return 0;
    }

    public void edit(String startTime, String duration, String deadline, String recurrence) {
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = deadline;

        this.recurrence = recurrence;
        resetRecurrence();
        setRecurrence(recurrence);
        assert !this.recurrenceDates.equals(null);
    }

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
        Time time = (Time) o;

        return isFlexible == time.isFlexible
                && Objects.equals(startTime, time.startTime)
                && Objects.equals(duration, time.duration)
                && Objects.equals(deadline, time.deadline)
                && Objects.equals(recurrenceDates, time.recurrenceDates);
    }


    //    @Override
    //    public int compareTo(Time o) {
    //        if(this.startTime>o.startTime){
    //            return 1;
    //        }
    //        return 0;
    //    }
}
