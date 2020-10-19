package athena;

import athena.commands.AddCommand;
import athena.commands.Command;
import athena.commands.DeleteCommand;
import athena.commands.DoneCommand;
import athena.commands.EditCommand;
import athena.commands.ExitCommand;
import athena.commands.HelpCommand;
import athena.commands.ListCommand;
import athena.commands.ViewCommand;
import athena.exceptions.CommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests method of the Parser class.
 */
class ParserTest {

    private Parser parser;
    private TaskList taskList;

    /**
     * Creates a new Parser and TaskList object before running each test.
     */
    @BeforeEach
    public void setUp() {
        parser = new Parser();
        taskList = new TaskList();
    }

    /**
     * Checks if the help command is shown if the user enters an invalid command.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_unknownCommandWord_returnsHelp() throws CommandException {
        final String input = "unknown arguments";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    /*
     * Tests for 0-argument commands =======================================================================
     */

    /**
     * Checks if the help command is shown if the user types "help".
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_helpCommand_parsedCorrectly() throws CommandException {
        final String input = "help";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    /**
     * Checks if the program exits if the user types "exit".
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_exitCommand_parsedCorrectly() throws CommandException {
        final String input = "exit";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    /**
     * Checks if the program exits if the user types "ex".
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_exitShortcutCommand_parsedCorrectly() throws CommandException {
        final String input = "ex";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    /*
     * Tests for single index argument commands ===============================================================
     */

    /**
     * Checks if the program deletes the correct task at index 1.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_deleteCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "delete 1";
        final DeleteCommand parsedCommand = parseAndAssertCommandType(input, DeleteCommand.class);
        final DeleteCommand expectedCommand = new DeleteCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program deletes the correct task at index 1.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_deleteShortcutCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "dl 1";
        final DeleteCommand parsedCommand = parseAndAssertCommandType(input, DeleteCommand.class);
        final DeleteCommand expectedCommand = new DeleteCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program marks the task at index 1 as done.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_doneCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "done 1";
        final DoneCommand parsedCommand = parseAndAssertCommandType(input, DoneCommand.class);
        final DoneCommand expectedCommand = new DoneCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program marks the task at index 1 as done.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_doneShortcutCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "dn 1";
        final DoneCommand parsedCommand = parseAndAssertCommandType(input, DoneCommand.class);
        final DoneCommand expectedCommand = new DoneCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program displays the details of task at index 1.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_viewCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "view 1";
        final ViewCommand parsedCommand = parseAndAssertCommandType(input, ViewCommand.class);
        final ViewCommand expectedCommand = new ViewCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program displays the details of task at index 1.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_viewShortcutCommandNumericArg_indexParsedCorrectly() throws CommandException {
        final int testNumber = 1;
        final String input = "v 1";
        final ViewCommand parsedCommand = parseAndAssertCommandType(input, ViewCommand.class);
        final ViewCommand expectedCommand = new ViewCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /*
     * Tests for multiple argument commands ===============================================================
     */

    /**
     * Checks if the program adds a task correctly.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_addCommandArg_parsedCorrectly() throws CommandException {
        final String input = "add n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/Monday i/high a/Refer to slides";
        final AddCommand parsedCommand = parseAndAssertCommandType(input, AddCommand.class);
        final AddCommand expectedCommand = new AddCommand("Assignment1", "1100",
                "2 hours", "16-09-2020", "Monday", "high",
                "Refer to slides", false);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program adds a task correctly.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_addShortcutCommandArg_parsedCorrectly() throws CommandException {
        final String input = "a n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/Monday i/high a/Refer to slides";
        final AddCommand parsedCommand = parseAndAssertCommandType(input, AddCommand.class);
        final AddCommand expectedCommand = new AddCommand("Assignment1", "1100",
                "2 hours", "16-09-2020", "Monday","high",
                "Refer to slides", false);
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program edits a task correctly.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_editCommandAllArg_parsedCorrectly() throws CommandException {
        taskList.addTask("name", "st", "dur", "deadline",
                "12-10-2020", Importance.LOW, "dummyNote", false);
        final int testNumber = 0;
        final String input = "edit 0 n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/13-10-2020 i/high a/Refer to slides";
        final EditCommand parsedCommand = parseAndAssertCommandType(input, EditCommand.class);
        final EditCommand expectedCommand = new EditCommand(testNumber, "Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                "Refer to slides");
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program edits a task correctly.
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_editShortcutCommandAllArg_parsedCorrectly() throws CommandException {
        taskList.addTask("name", "st", "dur", "deadline",
                "12-10-2020", Importance.LOW, "dummyNote", false);
        final int testNumber = 0;
        final String input = "e 0 n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/13-10-2020 i/high a/Refer to slides";
        final EditCommand parsedCommand = parseAndAssertCommandType(input, EditCommand.class);
        final EditCommand expectedCommand = new EditCommand(testNumber,"Assignment1", "1100",
                "2 hours", "16-09-2020", "13-10-2020", Importance.valueOf("high".toUpperCase()),
                "Refer to slides");
        assertEquals(parsedCommand, expectedCommand);
    }

    /**
     * Checks if the program edits a task correctly with fewer parameters.
     *
     * @throws CommandException Exception thrown if there is an error with the user entered command
     */
    @Test
    public void parse_editCommandSomeArg_parsedCorrectly() throws CommandException {
        taskList.addTask("name", "st", "dur", "deadline",
                "12-10-2020", Importance.LOW, "dummyNote", false);
        final int testNumber = 0;
        final String input = "edit 0 n/I have changed a/I am not filling any other arguments";
        final EditCommand parsedCommand = parseAndAssertCommandType(input, EditCommand.class);
        final EditCommand expectedCommand = new EditCommand(testNumber, "I have changed", "st",
                "dur", "deadline", "12-10-2020", Importance.valueOf("low".toUpperCase()),
                "I am not filling any other arguments");
        assertEquals(parsedCommand, expectedCommand);
    }

    @Test
    public void parse_editCommandBadArg_throwsException() throws CommandException {
        final String input = "edit abcde";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, EditCommand.class);
        });
    }

    @Test
    public void parse_editCommandBadArg2_throwsException() throws CommandException {
        final String input = "edit 1a/";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, EditCommand.class);
        });
    }

    @Test
    public void parse_editCommandBadArg3_throwsException() throws CommandException {
        final String input = "edit 1 n/n/";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, EditCommand.class);
        });
    }

    @Test
    public void parse_viewCommandBadArg_throwsException() throws CommandException {
        final String input = "view abcde";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, ViewCommand.class);
        });
    }

    @Test
    public void parse_doneCommandBadArg_throwsException() throws CommandException {
        final String input = "done abcde";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, DoneCommand.class);
        });
    }

    @Test
    public void parse_deleteCommandBadArg_throwsException() throws CommandException {
        final String input = "delete abcde";
        assertThrows(CommandException.class, () -> {
            parseAndAssertCommandType(input, DeleteCommand.class);
        });
    }

    @Test
    public void parse_listCommandArg_parsedCorrectly() throws CommandException {
        final String input = "list f/WEEK i/medium";
        final ListCommand parsedCommand = parseAndAssertCommandType(input, ListCommand.class);
        final ListCommand expectedCommand = new ListCommand(Importance.MEDIUM, Forecast.WEEK);
        assertEquals(parsedCommand, expectedCommand);
    }

    /*
     * Utility methods ====================================================================================
     */

    /**
     * Parses input and asserts the class of the returned command object.
     *
     * @param input                to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass)
            throws CommandException {
        final Command result = parser.parse(input, taskList);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

}