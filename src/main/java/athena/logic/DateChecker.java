package athena.logic;


import athena.exceptions.command.DateHasPassedException;
import athena.exceptions.command.InvalidDeadlineException;
import athena.exceptions.command.InvalidRecurrenceException;
import athena.exceptions.command.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker {
    String recurrenceString;
    String deadlineString;
    String startTimeString
    LocalDate recurrence;
    LocalDate deadline;
    LocalTime startTime;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public DateChecker(String recurrenceString, String deadlineString, String startTimeString)
            throws DateHasPassedException, InvalidRecurrenceException, InvalidDeadlineException,
            InvalidTimeFormatException {
        setStringAttributes(recurrenceString, deadlineString, startTimeString);
        setRecurrence(recurrenceString);
        setDeadline(deadlineString);
        setStartTime(startTimeString);
        checkDatePassed();
    }

    private void setStartTime(String startTimeString) throws InvalidTimeFormatException {
        try {
            this.startTime = LocalTime.parse(startTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    }

    private void setDeadline(String deadlineString) throws InvalidDeadlineException {
        try {
            this.deadline = LocalDate.parse(deadlineString, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    private void setRecurrence(String recurrenceString) throws InvalidRecurrenceException {
        try {
            this.recurrence = LocalDate.parse(recurrenceString, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        }
    }

    private void setStringAttributes(String recurrenceString, String deadlineString, String startTimeString) {
        this.recurrenceString = recurrenceString;
        this.deadlineString = deadlineString;
        this.startTimeString = startTimeString;
    }

    private void checkDatePassed() throws DateHasPassedException {
        if (recurrence.compareTo(LocalDate.now()) < 0) {
            throw new DateHasPassedException();
        }
        else if (recurrence.compareTo(LocalDate.now()) == 0) {
            checkStartTime();
        }
    }

    private void checkStartTime() throws DateHasPassedException {
        if (startTime.compareTo(LocalTime.now()) < 0) {
            throw new DateHasPassedException();
        }
    }
}
