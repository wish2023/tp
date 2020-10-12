package athena;

import athena.commands.Command;
import athena.commands.ExitCommand;
import athena.exceptions.AddException;

import java.util.Scanner;

public class Athena {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private ExitCommand exitCommand;

    public Athena() {
        ui = new Ui();
        parser = new Parser();
        exitCommand = new ExitCommand();
        storage = new Storage("data.csv", ui);
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
        boolean isExit = false;
        Scanner input = new Scanner(System.in);

        while (!isExit) {
            try {
                inputString = input.nextLine();
                userCommand = parser.parse(inputString);
                userCommand.execute(taskList, ui);
                isExit = userCommand.getIsExit();
            } catch (AddException e) {
                e.getErrorMessage();
            }
            continue;
        }
    }

}
