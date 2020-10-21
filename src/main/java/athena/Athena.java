package athena;

import athena.logic.LogicManager;
import athena.exceptions.CommandException;
import athena.logic.Parser;

import java.util.Scanner;

/**
 * The main class of the ATHENA program.
 */
public class Athena {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates an ATHENA object.
     */
    public Athena() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data.csv", ui);
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
    }

    /**
     * Runs the main code of the ATHENA program.
     */
    public void runProgram() {
        String inputString;
        LogicManager logicManager = new LogicManager();

        ui.printWelcomeMessage();

        taskList = storage.loadTaskListData();
        boolean isExit = false;
        Scanner input = new Scanner(System.in);

        while (!isExit) {
            try {
                inputString = input.nextLine();
                isExit = logicManager.execute(inputString);
            } catch (CommandException e) {
                e.printErrorMessage();
            }
            continue;
        }
    }
}
