package athena;

import athena.logic.LogicManager;
import athena.exceptions.CommandException;
import athena.exceptions.StorageCorruptedException;
import athena.exceptions.StorageException;
import athena.exceptions.StorageLoadFailException;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

/**
 * The main class of the ATHENA program.
 */
public class Athena {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private LogicManager logicManager;

    /**
     * Creates an ATHENA object.
     */
    public Athena() {
        ui = new Ui();
        logicManager = new LogicManager();
        storage = new Storage("data.csv");
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

        ui.printAthenaLogo();
        ui.printWelcomeMessage();
        boolean isExit = false;
        try {
            taskList = storage.loadTaskListData();
        } catch (StorageException e) {
            e.printErrorMessage();
            isExit = true;
        }
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
