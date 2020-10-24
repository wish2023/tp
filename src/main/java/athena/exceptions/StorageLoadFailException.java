package athena.exceptions;

import athena.Ui;

public class StorageLoadFailException extends StorageException {

    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printStorageLoadFail();
    }
}
