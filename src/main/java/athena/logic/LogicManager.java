package athena.logic;

import athena.Storage;
import athena.TaskList;
import athena.Ui;
import athena.exceptions.CommandException;
import athena.logic.commands.Command;

public class LogicManager implements Logic{
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    public LogicManager() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data.csv", ui);
    }

    @Override
    public boolean execute(String inputString) throws CommandException {
        Command userCommand;

        taskList = storage.loadTaskListData();
        userCommand = parser.parse(inputString, taskList);
        userCommand.execute(taskList, ui);
        storage.saveTaskListData(taskList);
        return userCommand.getIsExit();
    }
}
