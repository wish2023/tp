package athena.logic;

import athena.exceptions.CommandException;

public interface Logic {
    boolean execute(String inputString) throws CommandException;
}
