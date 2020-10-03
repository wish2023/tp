package athena;

/**
 * Handles parsing of user input
 */
public class Parser {

    /**
     * Parses user input and recognises what type of command
     * the user is looking for
     * @param input String representing user input
     * @return new Command object based on what the user input is
     */
    public static Commands parse(String input) {
        String[] array = input.split(" ", 2);
        String cmd = array[0];
        int noOfWords = array.length;
        Commands c = null;

        if (cmd.equals("done")) {
            int taskNo = Integer.parseInt(input.substring(5));
            c = new DoneCommand(taskNo- 1);
        } else if (cmd.equals("add")) {
            c = new AddCommand(cmd, array[1].trim());
        } else if (cmd.equals("delete")) {
            int taskNo = Integer.parseInt(input.substring(7));
            c = new DeleteCommand(taskNo- 1);
        } else if (cmd.equals("list")) {
            c = new ListCommand();
        } else if (cmd.equals("edit")) {
            c = new EditCommand(input.substring(5));
        } else if (cmd.equals("help")) {
            c = new HelpCommand();
        } else {
            if (cmd.equals("bye")) {
                c = new ExitCommand();
            }
        }
        return c;
    }
}