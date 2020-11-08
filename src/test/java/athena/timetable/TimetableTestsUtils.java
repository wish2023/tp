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
        return getUpcomingDates(7);
    }

    /**
     * Gets the upcoming dates starting from today in a list.
     *
     * @param days Number of days to get.
     * @return List of upcoming dates.
     */
    static ArrayList<LocalDate> getUpcomingDates(int days) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (int i = 0; i < days; i++) {
            dates.add(date);
            date = date.plusDays(1);
        }
        return dates;
    }
}
