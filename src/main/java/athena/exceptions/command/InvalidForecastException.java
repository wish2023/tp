package athena.exceptions.command;

import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;

/**
 * Exception thrown when the user enters an invalid forecast.
 */
public class InvalidForecastException extends CommandException {
    public InvalidForecastException() {

    }

    /**
     * Prints an error message telling user that they entered an invalid forecast.
     */
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printInvalidForecastException();
    }
}
