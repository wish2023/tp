package athena.logic;


import athena.exceptions.command.DateHasPassedException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker {
    public static final String TODAY = "today";
    String recurrenceString;
    String startTimeString;
    LocalDate recurrence;
    LocalTime startTime;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public DateChecker(String recurrenceString, String startTimeString)
            throws DateHasPassedException, InvalidRecurrenceException, InvalidTimeFormatException {
        setStringAttributes(recurrenceString, startTimeString);
        setRecurrence(recurrenceString);
        if (isNonEmptyTime(startTimeString)) {
            setStartTime(startTimeString);
            checkDatePassed();
        }
    }

    private boolean isNonEmptyTime(String startTimeString) {
        return !startTimeString.equals("");
    }

    private void setStartTime(String startTimeString) throws InvalidTimeFormatException {
        try {
            this.startTime = LocalTime.parse(startTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    }


    private void setRecurrence(String recurrenceString) throws InvalidRecurrenceException {
        try {
            checkDefaultDate(recurrenceString);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        }
    }

    private void checkDefaultDate(String recurrenceString) {
        if (recurrenceString.toLowerCase().equals(TODAY)) {
            this.recurrence = LocalDate.now();
        } else if (recurrenceString.length() == "dd-MM-yyyy".length()) {
            this.recurrence = LocalDate.parse(recurrenceString, dateFormatter);
        }
    }

    private void setStringAttributes(String recurrenceString, String startTimeString) {
        this.recurrenceString = recurrenceString;
        this.startTimeString = startTimeString;
    }

    private void checkDatePassed() throws DateHasPassedException {
        if (recurrence.compareTo(LocalDate.now()) < 0) {
            throw new DateHasPassedException();
        } else if (recurrence.compareTo(LocalDate.now()) == 0) {
            checkStartTime();
        }
    }

    private void checkStartTime() throws DateHasPassedException {
        if (startTime.compareTo(LocalTime.now()) < 0) {
            throw new DateHasPassedException();
        }
    }
}
