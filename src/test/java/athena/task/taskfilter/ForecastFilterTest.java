package athena.task.taskfilter;

import athena.Forecast;
import athena.Importance;
import athena.common.utils.DateUtils;
import athena.exceptions.CommandException;
import athena.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests methods of forecast filter.
 */
class ForecastFilterTest {

    LocalDate todayDate = LocalDate.now();

    /**
     * Checks if task is included after applying the all forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterAll_returnsTrue() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.ALL);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                "20-12-2020", Importance.MEDIUM, "testNotes", 0, false);
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, true);
    }

    /**
     * Checks if task in this week is included after applying the week forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterByWeek_returnsTrue() throws CommandException {
        LocalDate testDate = getFirstDayOfWeek();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        for (int i = 0; i < 7; i++) {
            if (testDate.compareTo(LocalDate.now()) >= 0) {
                Task testTask = new Task("testName", "0900", "1", "05-11-2020",
                        testDate.format(formatter), Importance.LOW, "testNotes", 0, false);
                boolean isTaskIncluded = forecastFilter.isTaskIncluded(testTask);
                assertTrue(isTaskIncluded);
            }
            testDate = testDate.plusDays(1);
        }
    }

    /**
     * Checks if a task not in this week is excluded from the week forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterByWeek_returnsFalse() throws CommandException {
        LocalDate nextWeekDate = LocalDate.now().plusWeeks(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String testDateString = nextWeekDate.format(formatter);

        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        Task testTask = new Task("testName", "0900", "1", "05-11-2020",
                testDateString, Importance.LOW, "testNotes", 0, false);
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(testTask);
        assertFalse(isTaskIncluded);
    }

    /**
     * Checks if task for today is included after applying the today forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterByToday_returnsTrue() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.DAY);
        String todayDateString = LocalDate.now().toString();
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDateString, Importance.LOW, "testNotes", 0, false); // Tested on 13-10-2020
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, true);
    }

    /**
     * Checks if task is not included after applying the today forecast filter.
     */
    @Test
    void testIsTaskIncluded_day_returnsFalse() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.DAY);
        LocalDate date = LocalDate.now().plusDays(1);
        String dateInString = DateUtils.formatDate(date);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                dateInString, Importance.LOW, "testNotes", 0, false); // Tested on 13-10-2020
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, false);
    }

    /**
     * Check if relevant dates have been removed from task after filtering for a day.
     */
    @Test
    void testRemoveExcludedDates_filterToday_returnsOnlyTodayDate() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.DAY);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expectedDate = todayDate.format(formatter);
        Task expectedTask = new Task("testName", "0900", "1", "05-11-2020",
                expectedDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask, expectedTask);
    }

    /**
     * Check if relevant dates have been removed from task after filtering for a week.
     */
    @Test
    void testRemoveExcludedDates_filterWeek_returnsOnlyTodayDate() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expectedDate = todayDate.format(formatter);
        Task expectedTask = new Task("testName", "0900", "1", "05-11-2020",
                expectedDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask, expectedTask);
    }

    /**
     * Ensure tasks are not filtered incorrectly for day filter.
     */
    @Test
    void testRemoveExcludedDates_filterDay_returnsFalse() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.DAY);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String wrongDate = todayDate.plusDays(1).format(formatter);
        Task wrongTask = new Task("testName", "0900", "1", "05-11-2020",
                wrongDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask.equals(wrongTask), false);
    }

    /**
     * Ensure tasks are not filtered incorrectly for week filter.
     */
    @Test
    void testRemoveExcludedDates_filterWeek_returnsFalse() throws CommandException {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String wrongDate = todayDate.plusDays(7).format(formatter);
        Task wrongTask = new Task("testName", "0900", "1", "05-11-2020",
                wrongDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask.equals(wrongTask), false);
    }

    /**
     * Utility method to get the first day of this week.
     *
     * @return The first day of this week.
     */
    private LocalDate getFirstDayOfWeek() {
        LocalDate now = LocalDate.now();
        TemporalField field = WeekFields.of(Locale.getDefault()).dayOfWeek();
        return now.with(field, 1);
    }
}