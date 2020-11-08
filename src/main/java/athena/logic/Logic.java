package athena.logic;

import athena.exceptions.command.CommandException;
import athena.exceptions.storage.StorageException;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns whether exit or not.
     *
     * @param inputString The command as entered by the user.
     * @return true if command is exit, false if not exit.
     * @throws CommandException If an error occurs during command execution.
     * @throws StorageException If an error occurs during storage.
     */
    boolean execute(String inputString) throws CommandException, StorageException;
}
