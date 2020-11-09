package athena;


import athena.exceptions.command.DateHasPassedException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.InvalidTimeFormatException;
import athena.exceptions.command.TimeNotHourlyException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Checks if a user's date is valid and has not passed.
 */
public class DateChecker {
    public static final String TODAY = "today";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    String recurrenceString;
    String startTimeString;
    LocalDate recurrence;
    LocalTime startTime;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public DateChecker(String recurrenceString, String startTimeString)
            throws DateHasPassedException, InvalidRecurrenceException,
            InvalidTimeFormatException, TimeNotHourlyException {
        setStringAttributes(recurrenceString, startTimeString);
        setRecurrence(recurrenceString);
        if (isNonEmptyTime(startTimeString)) {
            setStartTime(startTimeString);
            checkHourlyTime();
            checkDatePassed();
        }
    }

    /**
     * Checks if user's time is hourly.
     *
     * @throws TimeNotHourlyException Exception thrown if time is not hourly
     */
    private void checkHourlyTime() throws TimeNotHourlyException {
        if (startTime.getMinute() != 0) {
            throw new TimeNotHourlyException();
        }
    }

    /**
     * Checks if the starting time of a task is not empty.
     *
     * @param startTimeString the starting time of a task as a String
     * @return whether the starting time of a task is not empty.
     */
    private boolean isNonEmptyTime(String startTimeString) {
        return !startTimeString.equals("");
    }

    /**
     * Sets the starting time to be converted from its parameter as a String
     * to LocalTime.
     *
     * @param startTimeString the starting time of a task as a String
     * @throws InvalidTimeFormatException Exception thrown when user mistypes time
     */
    private void setStartTime(String startTimeString) throws InvalidTimeFormatException {
        try {
            this.startTime = LocalTime.parse(startTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    }


    /**
     * Attempts to set recurrence.
     *
     * @param recurrenceString the date the task will occur
     * @throws InvalidRecurrenceException if the user mistypes recurrence
     */
    private void setRecurrence(String recurrenceString) throws InvalidRecurrenceException {
        try {
            checkDefaultDate(recurrenceString);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        }
    }

    /**
     * Sets recurrence to be its parameter but as a LocalDate.
     *
     * @param recurrenceString the date the task will occur
     */
    private void checkDefaultDate(String recurrenceString) {
        if (isDayOfWeek(recurrenceString.toLowerCase())) {
            return;
        } else if (recurrenceString.toLowerCase().equals(TODAY)) {
            this.recurrence = LocalDate.now();
        } else if (recurrenceString.length() == DD_MM_YYYY.length()) {
            this.recurrence = LocalDate.parse(recurrenceString, dateFormatter);
        }
    }

    private boolean isDayOfWeek(String recurrenceString) {
        return recurrenceString.equals("sunday")
                || recurrenceString.equals("monday")
                || recurrenceString.equals("tuesday")
                || recurrenceString.equals("wednesday")
                || recurrenceString.equals("thursday")
                || recurrenceString.equals("friday")
                || recurrenceString.equals("saturday");
    }

    /**
     * Sets the String type attributes of DateChecker.
     *
     * @param recurrenceString  the date the task will occur
     * @param startTimeString   the starting time of a task as a String
     */
    private void setStringAttributes(String recurrenceString, String startTimeString) {
        this.recurrenceString = recurrenceString;
        this.startTimeString = startTimeString;
    }

    /**
     * Checks if a user's date has already passed.
     *
     * @throws DateHasPassedException Exception thrown if the user's date has already passed
     */
    private void checkDatePassed() throws DateHasPassedException {
        if (recurrence == null) {
            return;
        } else if (recurrence.compareTo(LocalDate.now()) < 0) {
            throw new DateHasPassedException();
        } else if (recurrence.compareTo(LocalDate.now()) == 0) {
            checkStartTime();
        }
    }

    /**
     * Checks if a user's time has already passed.
     *
     * @throws DateHasPassedException Exception thrown if the user's time has already passed
     */
    private void checkStartTime() throws DateHasPassedException {
        if (startTime.compareTo(LocalTime.now()) < 0) {
            throw new DateHasPassedException();
        }
    }
}
