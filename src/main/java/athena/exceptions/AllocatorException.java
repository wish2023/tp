package athena.exceptions;

/**
 * Abstract class for all the command exceptions that are thrown.
 */

public abstract class AllocatorException extends InternalException {

        public AllocatorException() {
        }

        /**
         * Abstract method for printing the error message to the user when an exception is thrown.
         */
        public abstract void printErrorMessage();
}
