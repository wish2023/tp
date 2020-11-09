package athena.ui;

import athena.task.Task;
import athena.task.TimeData;
import athena.timetable.Timetable;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

/**
 * Represents all of the text that the user sees on the command line.
 */
public class AthenaUi implements Ui {
    private ColorText colorText;

    public AthenaUi() {
        AnsiConsole.systemInstall();
        colorText = new ColorText();
    }

    public void printAthenaLogo() {
        printAthenaVoice("     ___   .__________.  __    __   _______  .__   __.      ___      \n"
                + "    /   \\  |          | |  |  |  | |   ____| |  \\ |  |     /   \\     \n"
                + "   /  ^  \\ `---|  |---` |  |__|  | |  |__    |   \\|  |    /  ^  \\    \n"
                + "  /  /_\\  \\    |  |     |   __   | |   __|   |  . `  |   /  /_\\  \\   \n"
                + " /  _____  \\   |  |     |  |  |  | |  |____  |  |\\   |  /  _____  \\  \n"
                + "/__/     \\__\\  |__|     |__|  |__| |_______| |__| \\__| /__/     \\__\\");
    }

    /**
     * Inserts an arrow before user input, making it easier for users to distinguish their input.
     */
    public void printUserInputIndicator() {
        printPurple("-> ");
        System.out.flush();
    }

    /**
     * Transfers user input to a string and trims extra whitespace.
     * @param userInput User input detected by the Scanner object
     * @return The user's input as a string
     */
    public String detectInput(Scanner userInput) {
        String inputString = userInput.nextLine();
        return inputString.trim();
    }

    public void printNewline() {
        System.out.print("\n");
    }

    public void printError(String inputString) {
        System.out.println(colorText.toRed(inputString));
    }

    public void printHighlight(String inputString) {
        System.out.println(colorText.toYellow(inputString));
    }

    public void printNormal(String inputString) {
        System.out.print(inputString);
    }

    public void printNormalNextLine(String inputString) {
        System.out.println(inputString);
    }

    public void printPurple(String inputString) {
        System.out.print(colorText.toPurple(inputString));
    }

    public void printAthenaVoice(String inputString) {
        System.out.println(colorText.toPurple(inputString));
    }

    public void printBold(String inputString) {
        System.out.print(colorText.toBlue(inputString));
    }

    public void printBoldNextLine(String inputString) {
        System.out.println(colorText.toBlue(inputString));
    }

    /**
     * Prints the first message that the user sees upon launching the application.
     */
    public void printWelcomeMessage() {
        printAthenaVoice("Hello! I'm the Goddess of Wisdom and War, the mighty ATHENA!\n" + "...\n"
                + "Okay okay I'm not a Goddess but I am your Automated Timetable Helper Encourager n' Assistant!\n"
                + "What can I do? Are you challenging me to a duel?\n" + "...\n"
                + "Oh you mean in terms of tasks? Just type \"help\" to witness my mighty repertoire!\n"
                + "So, what would you like to do today?");
    }

    /**
     * Prints out a message verifying which task the user has added to their list.
     *
     * @param task A task object that represents the task being added
     */
    public void printTaskAdded(Task task) {
        printNormal("You've successfully added ");
        printBold(task.getName());
        printNormalNextLine(" to your list!");

        if (task.getTimeInfo().getStartTimeString().equals("")) {
            printNormal("Since you haven't specified a start time, I'll allocate this task for you!");
        } else {
            printNormal("It will start at ");
            printBold(task.getTimeInfo().getStartTimeString());
            printNormal(".");
        }

        if (task.getTimeInfo().getDeadline().toLowerCase().equals("no deadline")) {
            printNormalNextLine(" This task has no deadline.");
        } else {
            printNormal(" This task is scheduled to finish on ");
            printBold(task.getTimeInfo().getDeadline());
            printNormalNextLine(".");
        }

        printNormal("You should spend a total of ");
        printBold(task.getTimeInfo().getDurationString());
        printNormal(" hour(s) on it. ");

        if (task.getTimeInfo().getRecurrence().toLowerCase().equals("today")) {
            printNormal("It is set to happen ");
            printBold(task.getTimeInfo().getRecurrence());
        } else if (task.getTimeInfo().getRecurrence().contains("-")) {
            printNormal("It is set to happen on ");
            printBold(task.getTimeInfo().getRecurrence());
        } else {
            printNormal("It is set to happen every ");
            printBold(task.getTimeInfo().getRecurrence());
        }

        printNormal(" and has an importance of ");
        printBold(task.getImportance().toString());
        printNormalNextLine(".");
        printNormalNextLine("Additionally, you've also added these notes!");
        printBold(task.getNotes());
        printNormalNextLine(".");
        printNormalNextLine("Looks like another mission to complete! Let's do it!");
    }

    /**
     * Prints out a message verifying that the task the user specified has been marked as done.
     *
     * @param task The task that the user marked as done
     */
    public void printTaskDone(Task task) {
        printNormal("A job well done! I've slayed the-- I mean, marked the task ");
        printBold(task.getName());
        printNormalNextLine(" as complete!");
    }

    /**
     * Prints out a message verifying that the task the user specified has been edited, and shows the user the
     * new task details.
     * @param task A task object that represents the task being edited
     */
    public void printTaskEdited(Task task) {
        printNormal("You've changed the details of task number ");
        printBoldNextLine((task.getNumber()) + ": " + task.getName() + "!");
        printNormalNextLine("Here are the new details of your task!");
        printNormal("Start Time: ");
        TimeData timeInfo = task.getTimeInfo();
        printBoldNextLine(timeInfo.getStartTimeString());
        printNormal("Duration: ");
        printBoldNextLine(timeInfo.getDurationString());
        printNormal("Due Date: ");
        printBoldNextLine(timeInfo.getDeadline());
        printNormal("Recurrence: ");
        printBoldNextLine(timeInfo.getRecurrence());
        printNormal("Importance: ");
        printBoldNextLine(task.getImportance().toString());
        printNormal("Additional Notes: ");
        printBoldNextLine(task.getNotes());
        printNormalNextLine("The mistakes of the past have been vanquished!");
    }

    /**
     * Prints out a message verifying that the task the user specified has been deleted.
     *
     * @param task The task that the user has deleted.
     */
    public void printTaskDeleted(Task task, String taskRestore) {
        printNormal("Are we pretending the task ");
        printBold(task.getName());
        printNormalNextLine(" never existed? Very well...");
        printNormalNextLine("We both know the human propensity to make mistakes, if you want it back just type:");
        printBoldNextLine(taskRestore);
        printNormalNextLine("You can thank me later!");
    }

    /**
     * Prints out the task details that the user specified to view.
     *
     * @param taskDetails The task that the user wants to view.
     */
    public void printTaskDetails(String taskDetails) {
        printNormal("Here are the details of your task: ");
        printBoldNextLine(taskDetails);
    }

    /**
     * Prints out a help menu of all available tasks that ATHENA is able to do.
     */
    public void printHelp() {
        printNormal("Not sure of what I'm capable of doing? ");
        printNormalNextLine("Well here's a list just for you!\n");

        printAthenaVoice("To add a task ");
        printNormalNextLine("(parameters in square brackets are optional fields):");
        printHighlight("add n/NAME [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] "
                        + "[i/IMPORTANCE] [a/ADDITIONAL-NOTES]");
        printNormalNextLine("e.g. add n/Assignment1 t/1100 D/16-09-2020 d/2 r/Today "
                + "i/high a/Refer to lecture notes\n");

        printAthenaVoice("To list your current tasks ");
        printNormalNextLine("(parameters in square brackets are optional fields):");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormalNextLine("e.g. list [f/WEEK] [i/medium]\n");

        printAthenaVoice("To mark a task as done:");
        printHighlight("done INDEX");
        printNormalNextLine("e.g. done 2\n");

        printAthenaVoice("To edit a task ");
        printNormalNextLine(" (parameters in square brackets are optional fields, "
                + "but at least one parameter needs to be included):");
        printHighlight("edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] "
                + "[r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]");
        printNormalNextLine("e.g.  edit 1 n/Assignment1 t/1100 D/16-09-2020 d/2 r/today "
                + "i/high a/Refer to lecture notes\n");

        printAthenaVoice("To delete a task:");
        printHighlight("delete INDEX");
        printNormalNextLine("e.g. delete 2\n");

        printAthenaVoice("To view a task:");
        printHighlight("view INDEX");
        printNormalNextLine("e.g. view 2\n");

        printAthenaVoice("To say farewell to me, ATHENA:");
        printHighlight("exit");
        printNormalNextLine("But why would you want to leave me anyways? Hmph.");
    }

    /**
     * Prints a message when there is an error with a task in the storage file.
     */
    public void printInvalidTask() {
        printError("One of the tasks in the archives seems to be cursed, I have disposed of it!");
    }

    /**
     * Print a message when user enters time in invalid format.
     */
    public void printInvalidTimeFormatException() {
        printError("Please enter your time in the format [HHMM].");
    }

    /**
     * Prints a message stating the task is not found.
     *
     * @param taskNumber Task number given by the user
     */
    public void printTaskNotFound(int taskNumber) {
        printError("The task with the label " + taskNumber + "cannot be found.");
    }

    /**
     * Prints a message telling user they did not specify either the name or start time of the task.
     */
    public void printAddMissingRequiredParametersException() {
        printError("You haven't specified the name of the task! "
                + "How can we go on a conquest if we don't know what to slay?");
        printNormal("In case you've forgotten, this is how you add a task: ");
        printHighlight("add n/NAME [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] "
                + "[i/IMPORTANCE] [a/ADDITIONAL-NOTES]");
    }

    /**
     * Prints an error when the date is not formatted correctly.
     */
    public void printAddDateWrongFormatException() {
        printError("You didn't format something in the right way!");
        printNormal("In case you've forgotten, this is how you add a task: ");
        printHighlight("add n/NAME [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] "
                + "[i/IMPORTANCE] [a/ADDITIONAL-NOTES]");
    }

    /**
     * Prints a message telling user they did not provide a valid index for the delete command.
     */
    public void printDeleteInvalidIndexException() {
        printError("Hmm...not sure what you're trying to delete, but there is no task at that index.");
        printNormal("In case you've forgotten your list of tasks and their indexes, type: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("This is how you delete a task, provided there is a task at the index you specify: ");
        printHighlight("delete INDEX");
    }

    /**
     * Prints a message telling user to input recurrence in correct format.
     */
    public void printInvalidRecurrenceException() {
        printError("Remember your date has to be valid in the format dd-mm(-yyyy)."
                + "Or you can type a day of the week for repeating tasks.");
    }

    public void printTimeNotHourlyException() {
        printError("Your time has to be hourly only! This version does not support "
                + " any time where the minute hand is not on 12.");
    }

    /**
     * Prints a message telling user that their proposed date has passed.
     */
    public void printDateHasPassedException() {
        printError("Your date or time has already passed. "
                + "I can't help you here unless I'm a time traveller.");
    }

    /**
     * Print message telling user they have mistyped the deadline date.
     */
    public void printInvalidDeadlineException() {
        printError("Remember your deadline has to be valid in the format dd-mm(-yyyy).");
    }

    /**
     * Prints a message telling user they did not provide an index for the delete command.
     */
    public void printDeleteNoIndexException() {
        printError("So you're trying to delete a task...but which one exactly? You haven't specified.");
        printNormal("In case you've forgotten your list of tasks and their indexes, type: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("Also just as a reminder, this is how you delete a task: ");
        printHighlight("delete INDEX");
    }

    /**
     * Prints a message telling user they did not provide a valid index for the done command.
     */
    public void printDoneInvalidIndexException() {
        printError("I'm not sure if you're trying to smoke me or smoke yourself, "
                + "but there is no task at that index.");
        printNormal("To see your list of tasks and their indexes, type this below: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("This is how you mark a task as done, provided that is a task at the index you specify: ");
        printHighlight("done INDEX");
    }

    /**
     * Prints a message telling user they did not provide an index for the done command.
     */
    public void printDoneNoIndexException() {
        printError("I know you're really eager, but you need to specify "
                + "the index of a task to mark that particular task as done!");
        printNormal("To see your list of tasks and their indexes, type: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("In the rare chance that you've forgotten how to mark a task as done, here is my tip for you: ");
        printHighlight("done INDEX");
    }

    /**
     * Prints a message telling the user they've already marked the task as done.
     */
    public void printTaskIsDoneException() {
        printError("Hmmm looks like you've already completed that task. Maybe consider taking a break?");
    }

    /**
     * Prints a message telling user they did not provide a valid index for the view command.
     */
    public void printViewInvalidIndexException() {
        printError("Errrrr I'm not sure what you wanted to do but...."
                + "there is no task at that index. You should enter an index number that contains a task.");
        printNormal("To see your list of tasks and their indexes, type: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("Also, in case you forgot how to view the details of a task, type: ");
        printHighlight("view INDEX");
    }

    /**
     * Prints a message telling user they did not provide an index for the view command.
     */
    public void printViewNoIndexException() {
        printError("Hold up, hold up, it seems like you haven't told me which task you want to view!");
        printNormal("If you can't remember which task has which index number, type: ");
        printHighlight("list [f/FORECAST] [i/IMPORTANCE]");
        printNormal("And if you forgot how to view a task, here you go: ");
        printHighlight("view INDEX");
    }

    /**
     * Prints a message telling user they did not provide an index for the edit command.
     */
    public void printEditNoIndexException() {
        printError("Your edit command instructions aren't quite right...");
        printNormal("Remember to edit at least one parameter! "
                + "I mean...why would you use the edit command if you don't want to edit anything?\n"
                + "I know the command is quite long, so here's a reminder for you: ");
        printHighlight("edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] "
                + "[r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]");
    }

    /**
     * Prints a message telling user they did not input a command that is recognized by the program.
     */
    public void printInvalidCommandException() {
        printError("I know I'm really smart but even I don't know what you're requesting. "
                + "Maybe if you type 'help', you can see what the valid commands are.");
    }

    /**
     * Prints a message telling user their task list is empty.
     */
    public void printEmptyTaskListException() {
        printError("You don't have any tasks in your list! "
                + "You should probably add some in if you want to be productive.");
    }

    /**
     * Prints a message telling user there's a clash with this task.
     */
    public void printClashInTaskException() {
        printError("There's a clash in this task, please choose a different time or date.");
    }

    /**
     * Prints a message telling user they've entered an invalid importance.
     */
    public void printInvalidImportanceException() {
        printError("You have entered an invalid importance. Please choose from high, medium, or low.");
    }

    /**
     * Prints a message telling user they've entered an invalid forecast.
     */
    public void printInvalidForecastException() {
        printError("You have entered an invalid forecast. Please choose from day, week, or all.");
    }

    /**
     * Prints a message when the user chooses to exit the program.
     */
    public void printExitMessage() {
        printAthenaVoice("Going so soon? Well I'll be here the next time you need me. Farewell!");
    }

    /**
     * Prints the timetable.
     *
     * @param timetable Timetable to be printed
     */
    public void printTimetable(Timetable timetable) {
        System.out.println(timetable);
    }

    /**
     * Generic error handling that prints out when an unexpected storage loading error has occurred.
     */
    public void printStorageLoadFail() {
        printError("Storage loading has failed.");
    }

    /**
     * Prints an error message when there is an error with the stored data.
     *
     * @param corruptedLine Line in the save file that has an error and has to be removed
     */
    public void printCorruptedLine(String corruptedLine) {
        printError("This task is invalid: " + corruptedLine + ".");
        printError("Please remove it externally to continue.");
    }

    /**
     * Generic error handling that prints out when an unexpected allocation error has occurred.
     */
    public void printAllocationFailed() {
        printError("Allocation Failed.");
    }

    /**
     * Prints a message telling the user they cannot add a task before 8am and after 12 midnight.
     */
    public void printSleepTimeNotAllowed() {
        printError("You are not allowed to add a task from 12am to 8am. It is time to sleep!");
    }

    public void printTaskTooLong(int taskNumber) {
        System.out.println(colorText.toRed("Task number " + taskNumber
                + " takes too long to complete, please reduce the time 16 hours and below \n"));
    }

    public void printIllegalTimeModificationException() {
        System.out.println(colorText.toRed(
                "You are not allowed to modify the time of flexible tasks \n"));
    }
}
