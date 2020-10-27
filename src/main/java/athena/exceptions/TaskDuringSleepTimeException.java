package athena.exceptions;

import athena.ui.AthenaUi;

public class TaskDuringSleepTimeException extends CommandException {
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printSleepTimeNotAllowed();
    }
}
