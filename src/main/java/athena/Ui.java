package athena;

import athena.task.Task;
import athena.timetable.Timetable;
import java.util.ArrayList;

/**
 * Represents all of the text that the user sees on the command line.
 */
public class Ui {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001b[33m";

    public Ui() {

    }

    public void printAthenaLogo() {
        System.out.println(ANSI_GREEN + "     ___   .__________.  __    __   _______ .__   __.      ___      \n"
                + "    /   \\  |          | |  |  |  | |   ____||  \\ |  |     /   \\     \n"
                + "   /  ^  \\ `---|  |---` |  |__|  | |  |__   |   \\|  |    /  ^  \\    \n"
                + "  /  /_\\  \\    |  |     |   __   | |   __|  |  . `  |   /  /_\\  \\   \n"
                + " /  _____  \\   |  |     |  |  |  | |  |____ |  |\\   |  /  _____  \\  \n"
                + "/__/     \\__\\  |__|     |__|  |__| |_______||__| \\__| /__/     \\__\\ \n"
                + "                                                                    " + ANSI_RESET);
    }

    /**
     * Prints the first message that the user sees upon launching the application.
     */
    public void printWelcomeMessage() {
        System.out.println(ANSI_GREEN + "Hello! I'm the Goddess of Wisdom and War, the mighty ATHENA!\n" + "...\n"
                + "Okay okay I'm not a Goddess but I am your Automated Timetable Helper Encourager n' Assistant!\n"
                + "What can I do? Are you challenging me to a duel?\n" + "...\n"
                + "Oh you mean in terms of tasks? Just type \"help\" to witness my mighty repertoire!\n"
                + "So, what would you like to do today?\n" + ANSI_RESET);
    }

    /**
     * Prints out a message verifying which task the user has added to their list.
     *
     * @param taskName       The name of the task
     * @param taskStartTime  When the task is scheduled to start
     * @param taskDuration   How long the task will last for
     * @param taskDeadline   When the task is due
     * @param taskRecurrence When will the task repeat
     * @param taskImportance What is the importance of the task
     * @param taskNotes      Any additional notes the user has added to the task
     */
    public void printTaskAdded(String taskName, String taskStartTime, String taskDuration, String taskDeadline,
                               String taskRecurrence, String taskImportance, String taskNotes) {
        System.out.println("You've successfully added " + ANSI_BLUE + taskName + ANSI_RESET + " to your list!\n"
                + "It will start at " + ANSI_BLUE + taskStartTime + ANSI_RESET + " and finish on "
                + ANSI_BLUE + taskDeadline + ANSI_RESET + ".\n"
                + "You should spend a total of " + ANSI_BLUE + taskDuration + ANSI_RESET + " hours on it.\n"
                + "It is set to happen " + ANSI_BLUE + taskRecurrence + ANSI_RESET + " and has an importance of "
                + ANSI_BLUE + taskImportance + ANSI_RESET + ".\n"
                + "Additionally, you've also added these additional notes!\n" + ANSI_BLUE + taskNotes
                + ANSI_RESET + ".\n"
                + "Looks like another mission to complete! Let's do it!\n");
    }

    /**
     * Lists out the the tasks that the user has added.
     *
     * @param taskList The list of tasks
     */
    public void printList(ArrayList<Task> taskList) {
        System.out.println("Here's your list of monsters you've been tasked to eliminate!\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.print("\n");
    }

    /**
     * Prints out a message verifying that the task the user specified has been marked as done.
     *
     * @param task The task that the user marked as done
     */
    public void printTaskDone(Task task) {
        System.out.println("A job well done! I've slayed the-- I mean, marked the task "
                + ANSI_BLUE + task.getName() + ANSI_RESET + " as complete!\n");
    }

    /**
     * Prints out a message verifying that the task the user specified has been edited, and shows the user the
     * new task details.
     *
     * @param taskIndex      The index of the task on the task list
     * @param taskName       The name of the task
     * @param taskStartTime  When the task is scheduled to start
     * @param taskDuration   How long the task will last for
     * @param taskDeadline   When the task is due
     * @param taskRecurrence When will the task repeat
     * @param taskImportance What is the importance of the task
     * @param taskNotes      Any additional notes the user has added to the task
     */
    public void printTaskEdited(int taskIndex, String taskName, String taskStartTime, String taskDuration,
                                String taskDeadline, String taskRecurrence, Importance taskImportance,
                                String taskNotes) {
        System.out.println("You've changed the details of task number " + ANSI_BLUE + taskIndex + ANSI_RESET + ": "
                + ANSI_BLUE + taskName + ANSI_RESET + "?\n"
                + "Here are the new details of your task!\n"
                + "Start Time: " + ANSI_BLUE + taskStartTime + ANSI_RESET +"\n"
                + "Duration: " + ANSI_BLUE + taskDuration + ANSI_RESET +"\n"
                + "Due Date: " + ANSI_BLUE + taskDeadline + ANSI_RESET +"\n"
                + "Recurrence: " + ANSI_BLUE + taskRecurrence + ANSI_RESET +"\n"
                + "Importance: " + ANSI_BLUE + taskImportance + ANSI_RESET +"\n"
                + "Additional Notes: " + ANSI_BLUE + taskNotes + ANSI_RESET +"\n"
                + "The mistakes of the past have been vanquished!\n");
    }

    /**
     * Prints out a message verifying that the task the user specified has been deleted.
     *
     * @param task The task that the user has deleted.
     */
    public void printTaskDeleted(Task task, String taskRestore) {
        System.out.println("Are we pretending the task " + ANSI_BLUE + task + ANSI_RESET
                + " never existed? Very well...\n");
        System.out.println("We both know the human propensity to make mistakes, if you want it back just type\n"
                + ANSI_BLUE + taskRestore + ANSI_RESET + "\nYou can thank me later\n");
    }

    /**
     * Prints out a help menu of all available tasks that ATHENA is able to do.
     */
    public void printHelp() {
        System.out.println(ANSI_YELLOW + "Not sure of what I'm capable of doing? Well here's a list just for you!\n\n"
                + "To add a task (parameters in square brackets are optional fields):\n"
                + "add n/NAME t/TIME [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]\n"
                + "e.g. add n/Assignment1 t/1100 d/16/09 D/2 r/Monday i/high a/Refer to lecture notes\n\n"
                + "To list your current tasks:\n"
                + "list f/FORECAST i/IMPORTANCE\n"
                + "e.g. list f/WEEK i/medium\n\n"
                + "To mark a task as done:\n"
                + "done INDEX\n"
                + "e.g. done 2\n\n"
                + "To edit a task (parameters in square brackets are optional fields):\n"
                + "edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE]"
                + "[a/ADDITIONAL-NOTES]\n"
                + "e.g. edit 2 n/Assignment1 t/1100 d/16/09 D/2 r/Monday i/high a/Refer to lecture notes\n\n"
                + "To delete a task:\n"
                + "delete INDEX\n"
                + "e.g. delete 2\n\n"
                + "To say farewell to me, ATHENA:\n"
                + "bye\n"
                + "But why would you want to leave me anyways? Hmph.\n" + ANSI_RESET);
    }

    /**
     * Prints a message when there is an error with a task in the storage file.
     */
    public void printInvalidTask() {
        System.out.println(ANSI_RED + "One of the tasks in the archives seems to be cursed, I have disposed of it!"
                + ANSI_RESET);
    }

    /**
     * Prints a message stating the task is not found.
     *
     * @param taskNumber Task number given by the user
     */
    public void printTaskNotFound(int taskNumber) {
        System.out.println(ANSI_RED + "The task with the label " + ANSI_BLUE + taskNumber + ANSI_RESET
                + ANSI_RED + " cannot be found" + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not specify either the name or start time of the task.
     */
    public void printAddMissingRequiredParametersException() {
        System.out.println(ANSI_RED + "You haven't specified the name or the start time of the task! "
                + "How can we go on a conquest if we don't know what to slay and what time to start?\n" + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not provide a valid index for the delete command.
     */
    public void printDeleteInvalidIndexException() {
        System.out.println(ANSI_RED + "Hmm...not sure what you're trying to delete, "
                + "but there is no task at that index.\n" + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not provide an index for the delete command.
     */
    public void printDeleteNoIndexException() {
        System.out.println(ANSI_RED + "So you're trying to delete a task...but which one exactly? "
                + "You haven't specified.\n" + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not provide a valid index for the done command.
     */
    public void printDoneInvalidIndexException() {
        System.out.println(ANSI_RED + "I'm not sure if you're trying to smoke me or smoke yourself, "
                + "but there is no task at that index. "
                + "You should enter a task index that contains a task.\n" + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not provide an index for the done command.
     */
    public void printDoneNoIndexException() {
        System.out.println(ANSI_RED + "I know you're really eager, but you need to specify a task to mark as done!\n"
                + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not provide an index for the edit command.
     */
    public void printEditNoIndexException() {
        System.out.println(ANSI_RED + "You need to specify the correct index of the task you want to edit!\n"
                + ANSI_RESET);
    }

    /**
     * Prints a message telling user they did not input a command that is recognized by the program.
     */
    public void printInvalidCommandException() {
        System.out.println(ANSI_RED + "I know I'm really smart but even I don't know what you're requesting. Maybe if you type "
                + "\"help\", you can see what the valid commands are.\n" + ANSI_RESET);
    }

    /**
     * Prints a message telling user their task list is empty.
     */
    public void printEmptyTaskListException() {
        System.out.println(ANSI_RED + "You don't have any tasks in your list! You should probably add some in if "
                + "you want to be productive.\n" + ANSI_RESET);
    }

    /**
     * Prints a message when the user chooses to exit the program.
     */
    public void printExitMessage() {
        System.out.println(ANSI_GREEN + "Going so soon? Well I'll be here the next time you need me. " +
                "Farewell!" + ANSI_RESET);
    }

    /**
     * Prints the timetable.
     *
     * @param timetable Timetable to be printed
     */
    public void printTimetable(Timetable timetable) {
        System.out.println(timetable);
    }
}
