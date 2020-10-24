package athena.exceptions;

import athena.Ui;

public class ClashInTaskException extends CommandException {
    public ClashInTaskException() {

    }

    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printClashInTaskException();
    }
}
