package athena;

import java.util.ArrayList;
import athena.commands.Command;


public class Athena {


    private Ui ui;

    public Athena() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
        Storage storage = new Storage("save.csv");
        TaskList oldTask = new TaskList();
        oldTask = storage.loadTaskListData();


        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Command command = parser.parse("add n/Assignment2 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
        command.execute(taskList, athena.ui);
        command = parser.parse("add n/Assignment2 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
        command.execute(taskList, athena.ui);
        command = parser.parse("list");
        command.execute(taskList, athena.ui);
        command = parser.parse("help");
        command.execute(taskList, athena.ui);
        command = parser.parse("list");
        command.execute(oldTask, athena.ui);
        storage.saveTaskListData(taskList);

    }

    public void runProgram() {
        // TODO: pass task as argument
//        ui.printTaskAdded();
//        ui.printTaskDeleted();
//        ui.printTaskDone();
//        ui.printTaskEdited();
    }

}
