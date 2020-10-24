package athena.logic;

import athena.Storage;
import athena.TaskList;
import athena.ui.AthenaUi;
import athena.exceptions.CommandException;
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
        storage = new Storage("data.csv", athenaUi);
    }

    @Override
    public boolean execute(String inputString) throws CommandException {
        Command userCommand;

        taskList = storage.loadTaskListData();
        userCommand = parser.parse(inputString, taskList);
        userCommand.execute(taskList, athenaUi);
        storage.saveTaskListData(taskList);
        return userCommand.getIsExit();
    }
}
