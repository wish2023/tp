package athena.task;

import athena.Importance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    private ArrayList<LocalDate> expectedDates;

    @BeforeEach
    public void setUp() {
        setupExpectedDates();
    }

    private void setupExpectedDates() {
        expectedDates = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            expectedDates.add(startDate.plusWeeks(i));
        }
    }

    /**
     * Ensure repeating dates have been set correctly.
     */
    @Test
    void testSetRecurrence_thisDayOfWeek_returnsTrue() {
        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        Task task = new Task("testName", "0900", "1", "05-11-2020",
                dayOfWeek, Importance.MEDIUM, "testNotes", 0, false);
        assertEquals(task.getDates(), expectedDates);
    }

    /**
     * Ensure repeating dates have not been set incorrectly.
     */
    @Test
    void testSetRecurrence_thisDayOfWeek_returnsFalse() {
        String dayOfWeek = LocalDate.now().plusDays(1).getDayOfWeek().toString();
        Task task = new Task("testName", "0900", "1", "05-11-2020",
                dayOfWeek, Importance.MEDIUM, "testNotes", 0, false);
        assertEquals(task.getDates().equals(expectedDates), false);
    }


}