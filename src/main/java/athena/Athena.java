package athena;

import athena.logic.LogicManager;
import athena.exceptions.CommandException;
import athena.ui.AthenaUi;

import java.util.Scanner;

/**
 * The main class of the ATHENA program.
 */
public class Athena {
    private AthenaUi athenaUi;
    private Storage storage;
    private TaskList taskList;
    private TimeAllocator allocator;
    private LogicManager logicManager;

    /**
     * Creates an ATHENA object.
     */
    public Athena() {
        athenaUi = new AthenaUi();
        logicManager = new LogicManager();
        storage = new Storage("data.csv", athenaUi);
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

        athenaUi.printAthenaLogo();
        athenaUi.printWelcomeMessage();

        taskList = storage.loadTaskListData();
        boolean isExit = false;
        Scanner input = new Scanner(System.in);

        while (!isExit) {
            try {
                allocator = new TimeAllocator(taskList);
                allocator.runAllocate();
                inputString = input.nextLine();
                isExit = logicManager.execute(inputString);
            } catch (CommandException e) {
                e.printErrorMessage();
            }
            continue;
        }
    }
}
