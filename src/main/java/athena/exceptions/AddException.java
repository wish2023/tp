package athena.exceptions;

import athena.Ui;

public class AddException extends CommandException {
    public AddException() {

    }

    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printAddException();
    }
}
