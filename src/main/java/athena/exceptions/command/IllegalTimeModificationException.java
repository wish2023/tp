package athena.exceptions.command;

import athena.ui.AthenaUi;

public class IllegalTimeModificationException extends CommandException {
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printIllegalTimeModificationException();
    }
}
