package athena.logic;


import athena.exceptions.command.DateHasPassedException;
import athena.exceptions.command.InvalidDeadlineException;
import athena.exceptions.command.InvalidRecurrenceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker {
    String recurrenceString;
    String deadlineString;
    LocalDate recurrence;
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DateChecker(String recurrenceString, String deadlineString)
            throws DateHasPassedException, InvalidRecurrenceException, InvalidDeadlineException {
        this.recurrenceString = recurrenceString;
        this.deadlineString = deadlineString;
        try {
            this.recurrence = LocalDate.parse(recurrenceString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        }
        try {
            this.deadline = LocalDate.parse(recurrenceString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
        checkDatePassed(recurrence);
    }

    private void checkDatePassed(LocalDate date) throws DateHasPassedException {
        if (date.compareTo(LocalDate.now()) < 0) {
            throw new DateHasPassedException();
        }
    }
}
