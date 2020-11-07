package athena.exceptions.storage;

public abstract class StorageException extends Exception {
    public StorageException() {

    }

    /**
     * Abstract method for printing the error message to the user when an exception is thrown.
     */
    public abstract void printErrorMessage();
}