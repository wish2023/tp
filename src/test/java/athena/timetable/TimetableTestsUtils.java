package athena.timetable;

import java.time.LocalDate;
import java.util.ArrayList;

public class TimetableTestsUtils {
    /**
     * Generates a list of LocalDates starting from today and ending 6 days later (total 7 days).
     *
     * @return List of dates for one week starting today.
     */
    static ArrayList<LocalDate> getThisWeekDates() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            dates.add(date);
            date = date.plusDays(1);
        }
        return dates;
    }
}
