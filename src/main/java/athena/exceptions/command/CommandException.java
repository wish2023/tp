package athena.exceptions.command;

/**
 * Abstract class for all the command exceptions that are thrown.
 */
public abstract class CommandException extends Exception {
    public CommandException() {

    }

    /**
     * Abstract method for printing the error message to the user when an exception is thrown.
     */
    public abstract void printErrorMessage();
}
