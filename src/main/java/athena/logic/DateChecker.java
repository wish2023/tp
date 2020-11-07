package athena.logic;


import athena.exceptions.command.DateHasPassedException;
import athena.exceptions.command.InvalidRecurrenceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateChecker {
    String dateString;
    LocalDate date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DateChecker(String dateString) throws DateHasPassedException, InvalidRecurrenceException {
        this.dateString = dateString;
        try {
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidRecurrenceException();
        }
        checkDatePassed(date);
    }

    private void checkDatePassed(LocalDate date) throws DateHasPassedException {
        if (date.compareTo(LocalDate.now()) < 0) {
            throw new DateHasPassedException();
        }
    }
}
