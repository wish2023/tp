package athena;

import athena.commands.Command;
import athena.exceptions.CommandException;

public class Athena {
    private static Ui ui;

    public Athena() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
        Storage storage = new Storage("save.csv", ui);
        TaskList oldTask = new TaskList();
        oldTask = storage.loadTaskListData();
        TaskList taskList = new TaskList();
        taskList = storage.loadTaskListData();
        Parser parser = new Parser();
        try {
            Command command = parser.parse("add n/ t/ d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
            command.execute(taskList, athena.ui);
        } catch (CommandException e) {
            e.printErrorMessage();
        }

    }

    public void runProgram() {
        // TODO: pass task as argument
//        ui.printTaskAdded();
//        ui.printTaskDeleted();
//        ui.printTaskDone();
//        ui.printTaskEdited();
    }

}
