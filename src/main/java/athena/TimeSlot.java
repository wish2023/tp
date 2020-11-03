package athena;

import athena.exceptions.AllocationFailedException;
import athena.exceptions.NoNextSlotException;

public class TimeSlot {
    private Log dayLog;
    private final int WAKE = 8;
    private final int SLEEP = 22;
    private Integer start = WAKE;
    private Integer end = WAKE;


    public TimeSlot(Log dayLog) {
        this.dayLog = dayLog;
    }

    public void findNextSlot() throws NoNextSlotException {
        if (start == SLEEP){
            throw new NoNextSlotException();
        }
        start = end;
        for (; start < SLEEP; start++) {
            if (dayLog.get(start) == -1) {
                break;
            }
        }
        end = start;
        for (; end < SLEEP; end++) {
            if (dayLog.get(end) != -1) {
                break;
            }
        }
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }
}
