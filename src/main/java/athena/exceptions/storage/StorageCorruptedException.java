package athena.exceptions.storage;

import athena.ui.AthenaUi;

public class StorageCorruptedException extends StorageException {
    private String[] corruptedLine;

    public StorageCorruptedException(String[] corruptedLine) {
        this.corruptedLine = corruptedLine;
    }

    @Override
    public void printErrorMessage() {
        AthenaUi athenaUi = new AthenaUi();
        String line = "";
        for (String info : corruptedLine) {
            line += info;
        }
        athenaUi.printCorruptedLine(line);
    }
}
