package athena.task;

import athena.Recurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Stores information related to time.
 * examples include startTime, duration, deadline
 * Recurrence and recurrenceDate can also be stored here
 * setters and getter are needed
 * new things to add ifFlexibleTime to let the TimeAllocate change the time values
 */
public class Time implements Comparable<Time> {

    private static final int DATE_TIME_FORMAT = 5;
    private Boolean flexible;
    private String startTime;
    private String duration;
    private String deadline;

    private String recurrence;
    private LocalDate recurrenceDate;

    public Time(Boolean flexible, String startTime, String duration, String deadline, String recurrence) {
        this.flexible = flexible;
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = deadline;
        this.recurrence = recurrence;
    }

    public LocalDate getRecurrenceDate() {
        return recurrenceDate;
    }

    private void setRecurrenceDate(String recurrence) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (recurrence.length() == DATE_TIME_FORMAT) {
            String year = Integer.toString(LocalDate.now().getYear());
            recurrenceDate = LocalDate.parse(recurrence + "-", formatter);
        } else {
            recurrenceDate = LocalDate.parse(recurrence, formatter);
        }
    }

    public void setRecurrenceDate(LocalDate recurrenceDate) {
        if (recurrence.toUpperCase().equals(Recurrence.TODAY.toString())) {
            recurrenceDate = LocalDate.now();
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                recurrenceDate = LocalDate.parse(recurrence, formatter);
            } catch (DateTimeParseException e) {
                // TODO: Handle this properly
                System.out.println("I don't understand the date you gave. So I set it to today.");
                recurrenceDate = LocalDate.now();
            }
        }
        this.recurrenceDate = recurrenceDate;
    }

    public Boolean getFlexible() {
        return flexible;
    }

    @Override
    public int compareTo(Time o) {
        return 0;
    }


    //    @Override
    //    public int compareTo(Time o) {
    //        if(this.startTime>o.startTime){
    //            return 1;
    //        }
    //        return 0;
    //    }
}
