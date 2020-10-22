package athena;

import athena.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeAllocatorTest {

    @Test
    void runAllocate_overCapacityDay_validSchedule() {
        TaskList taskList = new TaskList();
        taskList.addTask(0, "Assignment 1", "0", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(1, "Assignment 2", "1", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(2, "Assignment 3", "4", "5", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(3, "Assignment 4", "13", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(4, "Assignment 5", "9", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(5, "Assignment 6", "18", "6", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(6, "Assignment 7", "17", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        taskList.addTask(7, "Assignment 8", "12", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        taskList.addTask(8, "Assignment 9", "3", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);

        TaskList messyTaskList = new TaskList();

        messyTaskList.addTask(0, "Assignment 1", "0", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(1, "Assignment 2", "5", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(2, "Assignment 3", "19", "5", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(3, "Assignment 4", "13", "2", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(4, "Assignment 5", "16", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(5, "Assignment 6", "1", "6", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(6, "Assignment 7", "17", "3", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);
        messyTaskList.addTask(7, "Assignment 8", "15", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", true);
        messyTaskList.addTask(8, "Assignment 9", "3", "1", "6pm", "today",
                Importance.MEDIUM, "Tough assignment", false);

        TimeAllocator allocator = new TimeAllocator(messyTaskList);
        try {
            allocator.runAllocate();
        } catch (TaskNotFoundException e) {
            assert false;
        }

        assertEquals(messyTaskList, taskList);


    }
}