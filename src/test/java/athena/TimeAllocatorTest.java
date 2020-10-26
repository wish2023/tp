package athena;

import athena.exceptions.CommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeAllocatorTest {

    @Test
    void runAllocate_overCapacityDay_validSchedule() throws CommandException {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "0000", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "0100", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(2, "Assignment 3", "1900", "5", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(3, "Assignment 4", "1300", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "1000", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(5, "Assignment 6", "0400", "6", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(6, "Assignment 7", "1700", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "1500", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(8, "Assignment 9", "0300", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);

        TaskList messyTaskList = new TaskList();

        messyTaskList.addTask(0, "Assignment 1", "0000", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(1, "Assignment 2", "0500", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(2, "Assignment 3", "1900", "5", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(3, "Assignment 4", "1300", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(4, "Assignment 5", "1600", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(5, "Assignment 6", "0100", "6", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(6, "Assignment 7", "1700", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(7, "Assignment 8", "1500", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(8, "Assignment 9", "0300", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);

        TimeAllocator allocator = new TimeAllocator(messyTaskList);
        allocator.runAllocate();
        assertEquals(messyTaskList, taskList);


    }
}