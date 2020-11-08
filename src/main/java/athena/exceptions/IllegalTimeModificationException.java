package athena.exceptions;

import athena.ui.AthenaUi;

public class IllegalTimeModificationException extends CommandException {
    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printIllegalTimeModificationException();
    }
}
