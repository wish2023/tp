package athena;

import athena.exceptions.NoNextSlotException;

public class TimeSlot {
    private Log dayLog;
    private final Integer wake = 8;
    private final Integer sleep = 24;
    private Integer start = wake;
    private Integer end = wake;


    public TimeSlot(Log dayLog) {
        this.dayLog = dayLog;
    }

    public void findNextSlot() throws NoNextSlotException {
        if (start == sleep) {
            throw new NoNextSlotException();
        }
        start = end;
        for (; start < sleep; start++) {
            if (dayLog.getStart(start) == -1) {
                break;
            }
        }
        end = start;
        for (; end < sleep; end++) {
            if (dayLog.getStart(end) != -1) {
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
