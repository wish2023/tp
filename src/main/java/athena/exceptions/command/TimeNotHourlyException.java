package athena.exceptions.command;

import athena.ui.AthenaUi;

/**
 * Exception thrown when user attempts to add in a task that doesn't start at an hour.
 */
public class TimeNotHourlyException extends CommandException {
    public TimeNotHourlyException() {

    }

    /**
     * Prints an error message telling user that there the time needs to be hourly.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printTimeNotHourlyException();
    }
}
