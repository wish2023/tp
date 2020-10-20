package athena;

import athena.task.Task;
import athena.timetable.Timetable;
import java.util.ArrayList;

/**
 * Represents all of the text that the user sees on the command line.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Prints the first message that the user sees upon launching the application.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm the Goddess of Wisdom and War, the mighty ATHENA!\n" + "...\n"
                + "Okay okay I'm not a Goddess but I am your Automated Timetable Helper Encourager n' Assistant!\n"
                + "What can I do? Are you challenging me to a duel?\n" + "...\n"
                + "Oh you mean in terms of tasks? Just type \"help\" to witness my mighty repertoire!\n"
                + "So, what would you like to do today?\n");
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
        System.out.println("You've successfully added " + taskName + " to your list!\n"
                + "It will start at " + taskStartTime + " and finish on " + taskDeadline + ".\n"
                + "You should spend a total of " + taskDuration + " hours on it.\n"
                + "It is set to happen " + taskRecurrence + " and has an importance of " + taskImportance + ".\n"
                + "Additionally, you've also added these additional notes!\n" + taskNotes + ".\n"
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
                + task.getName() + " as complete!\n");
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
        System.out.println("You've changed the details of task number " + taskIndex + ": " + taskName + "?\n"
                + "Here are the new details of your task!\n"
                + "Start Time: " + taskStartTime + "\n"
                + "Duration: " + taskDuration + "\n"
                + "Due Date: " + taskDeadline + "\n"
                + "Recurrence: " + taskRecurrence + "\n"
                + "Importance: " + taskImportance + "\n"
                + "Additional Notes: " + taskNotes + "\n"
                + "The mistakes of the past have been vanquished!\n");
    }

    /**
     * Prints out a message verifying that the task the user specified has been deleted.
     *
     * @param task The task that the user has deleted.
     */
    public void printTaskDeleted(Task task, String taskRestore) {
        System.out.println("Are we pretending the task " + task + " never existed? Very well...\n");
        System.out.println("We both know the human propensity to make mistakes, if you want it back just type\n"
                + taskRestore + "\nYou can thank me later\n");
    }

    /**
     * Prints out a help menu of all available tasks that ATHENA is able to do.
     */
    public void printHelp() {
        System.out.println("Not sure of what I'm capable of doing? Well here's a list just for you!\n\n"
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
                + "But why would you want to leave me anyways? Hmph.\n");
    }

    /**
     * Prints a message when there is an error with a task in the storage file.
     */
    public void printInvalidTask() {
        System.out.println("One of the tasks in the archives seems to be cursed, I have disposed of it!");
    }

    /**
     * Prints a message stating the task is not found.
     *
     * @param taskNumber Task number given by the user
     */
    public void printTaskNotFound(int taskNumber) {
        System.out.println("The task with the label " + taskNumber + " cannot be found");
    }

    /**
     * Prints a message telling user they did not specify either the name or start time of the task.
     */
    public void printAddMissingRequiredParametersException() {
        System.out.println("You haven't specified the name or the start time of the task! "
                + "How can we go on a conquest if we don't know what to slay and what time to start?\n");
    }

    /**
     * Prints a message telling user they did not provide a valid index for the delete command.
     */
    public void printDeleteInvalidIndexException() {
        System.out.println("Hmm...not sure what you're trying to delete, but there is no task at that index.\n");
    }

    /**
     * Prints a message telling user they did not provide an index for the delete command.
     */
    public void printDeleteNoIndexException() {
        System.out.println("So you're trying to delete a task...but which one exactly? You haven't specified.\n");
    }

    /**
     * Prints a message telling user they did not provide a valid index for the done command.
     */
    public void printDoneInvalidIndexException() {
        System.out.println("I'm not sure if you're trying to smoke me or smoke yourself, but there is no task at that "
                + "index. You should enter a task index that contains a task.\n");
    }

    /**
     * Prints a message telling user they did not provide an index for the done command.
     */
    public void printDoneNoIndexException() {
        System.out.println("I know you're really eager, but you need to specify a task to mark as done!\n");
    }

    /**
     * Prints a message telling user they did not provide an index for the edit command.
     */
    public void printEditNoIndexException() {
        System.out.println("You need to specify the correct index of the task you want to edit!\n");
    }

    /**
     * Prints a message telling user they did not input a command that is recognized by the program.
     */
    public void printInvalidCommandException() {
        System.out.println("I know I'm really smart but even I don't know what you're requesting. Maybe if you type "
                + "\"help\", you can see what the valid commands are.\n");
    }

    /**
     * Prints a message telling user their task list is empty.
     */
    public void printEmptyTaskListException() {
        System.out.println("You don't have any tasks in your list! You should probably add some in if you want to "
                + "be productive.\n");
    }

    /**
     * Prints a message when the user chooses to exit the program.
     */
    public void printExitMessage() {
        System.out.println("Going so soon? Well I'll be here the next time you need me. Farewell!");
    }

    /**
     * Prints the timetable.
     *
     * @param timetable Timetable to be printed
     */
    public void printTimetable(Timetable timetable) {
        System.out.println(timetable);
    }

    public void printStorageLoadFail() {
    }

    public void printCorruptedLine(String corruptedLine) {
        System.out.println(corruptedLine);
    }
}
