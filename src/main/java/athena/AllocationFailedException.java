package athena;

import athena.exceptions.InternalException;

public class AllocationFailedException extends InternalException {
    @Override
    public void printErrorMessage() {
        Ui ui = new Ui();
        ui.printAllocationFailed();
    }
}
