package athena.exceptions;

import athena.ui.AthenaUi;

public class ClashInTaskException extends CommandException {
    public ClashInTaskException() {

    }

    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printClashInTaskException();
    }
}
