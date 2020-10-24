package athena.exceptions;

public abstract class InternalException extends Exception {
    public InternalException() {

    }

    /**
     * Abstract method for printing the error message to the user when an exception is thrown.
     */
    public abstract void printErrorMessage();
}
