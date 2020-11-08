package athena.exceptions.storage;

import athena.ui.AthenaUi;

public class StorageLoadFailException extends StorageException {

    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        athenaUi.printStorageLoadFail();
    }
}
