package athena.task.taskfilter;

import athena.Forecast;
import athena.Importance;
import athena.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of forecast filter.
 */
class ForecastFilterTest {

    LocalDate todayDate = LocalDate.now();

    /**
     * Checks if task is included after applying the all forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterAll_returnsTrue() {
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
    void testIsTaskIncluded_filterByWeek_returnsTrue() {
        String todayDateString = LocalDate.now().toString();
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDateString, Importance.LOW, "testNotes", 0, false);
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, true);
    }

    /**
     * Checks if task not in this week is not included after applying the week forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterByWeek_returnsFalse() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.WEEK);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                "30-10-2020", Importance.LOW, "testNotes", 0, false);
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, false);
    }

    /**
     * Checks if task for today is included after applying the today forecast filter.
     */
    @Test
    void testIsTaskIncluded_filterByToday_returnsTrue() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.TODAY);
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
    void testIsTaskIncluded_day_returnsFalse() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.TODAY);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                "14-10-2020", Importance.LOW, "testNotes", 0, false); // Tested on 13-10-2020
        boolean isTaskIncluded = forecastFilter.isTaskIncluded(inputTask);
        assertEquals(isTaskIncluded, false);
    }


    /**
     * Check if relevant dates have been removed from task after filtering for a day.
     */
    @Test
    void testremoveExcludedDates_day_returnsTrue() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.TODAY);
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
    void testremoveExcludedDates_week_returnsTrue() {
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
    void testremoveExcludedDates_day_returnsFalse() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.TODAY);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expectedDate = todayDate.plusDays(1).format(formatter);
        Task expectedTask = new Task("testName", "0900", "1", "05-11-2020",
                expectedDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask.equals(expectedTask), false);
    }

    /**
     * Ensure tasks are not filtered incorrectly for week filter.
     */
    @Test
    void testremoveExcludedDates_week_returnsFalse() {
        ForecastFilter forecastFilter = new ForecastFilter(Forecast.TODAY);
        Task inputTask = new Task("testName", "0900", "1", "05-11-2020",
                todayDate.getDayOfWeek().toString(), Importance.LOW, "testNotes", 0, false);
        String expectedDate = getDateInString();
        Task expectedTask = new Task("testName", "0900", "1", "05-11-2020",
                expectedDate, Importance.LOW, "testNotes", 0, false);
        Task actualTask = forecastFilter.removeExcludedDates(inputTask);
        assertEquals(actualTask.equals(expectedTask), false);
    }

    private String getDateInString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return todayDate.plusDays(7).format(formatter);
    }


}