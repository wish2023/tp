package athena.exceptions;

import athena.Ui;

public class StorageCorruptedException extends StorageException {
    private String[] corruptedLine;

    public StorageCorruptedException(String[] corruptedLine) {
        this.corruptedLine = corruptedLine;
    }

    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        String line = "";
        for (String info : corruptedLine) {
            line += info;
        }
        ui.printCorruptedLine(line);
    }
}
