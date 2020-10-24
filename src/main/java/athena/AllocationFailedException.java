package athena;

import athena.exceptions.InternalException;

public class AllocationFailedException extends InternalException {
    @Override
    public void printErrorMessage() {
        AthenaUi ui = new AthenaUi();
        ui.printAllocationFailed();
    }
}
