package athena;

public class Ui {
    public Ui() {

    }

    //todo: could also include a logo to make it more friendly
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm the God of Wisdom and War, the mighty ATHENA!\n" + "...\n"
                + "Okay okay I'm not a God but I am your Automated Timetable Helper Encourager n' Assistant!\n"
                + "What can I do? Are you challenging me to a duel?\n" + "...\n"
                + "Oh you mean in terms of tasks? Just type \"help\" to witness my mighty repertoire!\n"
                + "So, what would you like to do today?\n");
    }

    public void printTaskAdded(Task task) {
        System.out.println("You've successfully added " + task + "!\n"
                + "Another mission to complete? Let's do it!\n");
    }

    public void printList(ArrayList<Task> tasks) {
        System.out.println("Here's your list of monsters you've been tasked to eliminate!\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.print("\n");
    }

    public void printTaskDone(Task task) {
        System.out.println("A job well done! I've slayed the-- I mean, marked the task " + task + "as complete!\n");
    }

    public void printTaskEdited(Task task) {
        System.out.println("You've changed the details of the task " + task + "?\n"
                + "The mistakes of the past have been vanquished!\n");
    }

    public void printTaskDeleted(Task task) {
        System.out.println("Are we pretending the task " + task + " never existed? Very well...\n");
    }

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
                + "edit INDEX [n/NAME] [t/TIME] [d/DURATION] [D/DEADLINE] [r/RECURRENCE] [i/IMPORTANCE] [a/ADDITIONAL-NOTES]\n"
                + "e.g. edit 2 n/Assignment1 t/1100 d/16/09 D/2 r/Monday i/high a/Refer to lecture notes\n\n"
                + "To delete a task:\n"
                + "delete INDEX\n"
                + "e.g. delete 2\n\n"
                + "To say farewell to me, ATHENA:\n"
                + "exit\n"
                + "But why would you want to leave me anyways? Hmph.\n");
    }

    public void printExitMessage() {
        System.out.println("Going so soon? Well I'll be here the next time you need me. Farewell!");
    }

    /*
    //message that is printed out while awaiting the next command
    public void nextCommandPrompt() {

    }

    public void printTimetable(Timetable timetable) {

    }

    //access from Parser
    public void lastSaved() {

    }

    public void successMessage() {

    }

    //will have many of these, depending on how many types of errors we have
    public void errorMessage() {

    }
    */
}
