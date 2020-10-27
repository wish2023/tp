package athena.logic;

import athena.TimeAllocator;
import athena.Storage;
import athena.TaskList;
import athena.ui.AthenaUi;
import athena.exceptions.CommandException;
import athena.exceptions.StorageCorruptedException;
import athena.exceptions.StorageException;
import athena.logic.commands.Command;

/**
 * The main LogicManager of the ATHENA.
 */
public class LogicManager implements Logic {
    private static AthenaUi athenaUi;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    public LogicManager() {
        athenaUi = new AthenaUi();
        parser = new Parser();
        storage = new Storage("data.csv");
    }

    @Override
    public boolean execute(String inputString) throws CommandException, StorageException,
            StorageCorruptedException {
        Command userCommand;

        taskList = storage.loadTaskListData();
        userCommand = parser.parse(inputString, taskList);
        userCommand.execute(taskList, athenaUi);

        TimeAllocator timeAllocator = new TimeAllocator(taskList);
        timeAllocator.runAllocate();

        storage.saveTaskListData(taskList);
        return userCommand.getIsExit();
    }
}
