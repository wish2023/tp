package athena.exceptions;

public abstract class CommandException extends Exception {
    public CommandException() {

    }

    public abstract void printErrorMessage();
}