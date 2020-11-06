package athena.exceptions;

import athena.exceptions.InternalException;
import athena.ui.AthenaUi;

public class AllocationFailedException extends AllocatorException {
    @Override
    public void printErrorMessage() {
        AthenaUi ui = new AthenaUi();
        ui.printAllocationFailed();
    }
}
