package athena.exceptions;

import athena.Ui;

public class AddException extends Exception {
    public AddException() {

    }

    public void getErrorMessage() {
        Ui ui = new Ui();
        ui.printAddException();
    }
}
