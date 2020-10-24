package athena;

import athena.exceptions.InternalException;
import athena.ui.AthenaUi;

public class AllocationFailedException extends InternalException {
    @Override
    public void printErrorMessage() {
        AthenaUi ui = new AthenaUi();
        ui.printAllocationFailed();
    }
}
