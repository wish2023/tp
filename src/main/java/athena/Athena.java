package athena;

import athena.commands.Command;
import athena.exceptions.AddException;

import java.util.Scanner;

public class Athena {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Athena() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("save.csv", ui);
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
    }

    public void runProgram() {
        String inputString;
        Command userCommand;

        ui.printWelcomeMessage();

        taskList = storage.loadTaskListData();
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                inputString = input.nextLine();
                userCommand = parser.parse(inputString);
                userCommand.execute(taskList, ui);
            } catch (AddException e) {
                e.getErrorMessage();
            }
            continue;
        }
    }

}
