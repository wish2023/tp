package athena;

import athena.commands.Command;
import athena.exceptions.AddException;

import java.util.Scanner;

public class Athena {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList oldTask;

    public Athena() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("save.csv", ui);
    }

    public static void main(String[] args) {
        Athena athena = new Athena();

        athena.runProgram();

        /*Athena athena = new Athena();
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
        } catch (AddException e) {
            e.getErrorMessage();
        }
        Command command = parser.parse("add n/Assignment2 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
        command.execute(taskList, athena.ui);
        for (int i = 0; i < 10; i++) {
            command = parser.parse("add n/Assignment2 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
            command.execute(taskList, athena.ui);
        }
        command = parser.parse("list");
        command.execute(taskList, athena.ui);
        command = parser.parse("help");
        command.execute(taskList, athena.ui);
        storage.saveTaskListData(taskList);
        command = parser.parse("delete 2");
        command.execute(taskList, athena.ui);
        storage.saveTaskListData(taskList);*/

    }

    public void runProgram() {
        Scanner input;
        String inputString;
        Command userCommand;

        ui.printWelcomeMessage();

        oldTask = storage.loadTaskListData();

        while(true) {
            try {
                input = new Scanner(System.in);
                inputString = input.nextLine();
                userCommand = parser.parse(inputString);
                userCommand.execute(oldTask, ui);
            } catch (AddException e) {
                e.getErrorMessage();
            }
            continue;
        }
    }

}
