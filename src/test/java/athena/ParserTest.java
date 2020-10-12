package athena;

import athena.commands.Command;
import athena.commands.AddCommand;
import athena.commands.DeleteCommand;
import athena.commands.DoneCommand;
import athena.commands.EditCommand;
import athena.commands.ExitCommand;
import athena.commands.HelpCommand;
import athena.commands.ListCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_unknownCommandWord_returnsHelp() {
        final String input = "unknown arguments";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    /*
     * Tests for 0-argument commands =======================================================================
     */

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String input = "help";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() {
        final String input = "bye";
        parseAndAssertCommandType(input, ExitCommand.class);
    }

    /*
     * Tests for single index argument commands ===============================================================
     */

    @Test
    public void parse_deleteCommandNumericArg_indexParsedCorrectly() {
        final int testNumber = 1;
        final String input = "delete 1";
        final DeleteCommand parsedCommand = parseAndAssertCommandType(input, DeleteCommand.class);
        final DeleteCommand expectedCommand = new DeleteCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    @Test
    public void parse_doneCommandNumericArg_indexParsedCorrectly() {
        final int testNumber = 1;
        final String input = "done 1";
        final DoneCommand parsedCommand = parseAndAssertCommandType(input, DoneCommand.class);
        final DoneCommand expectedCommand = new DoneCommand(testNumber);
        assertEquals(parsedCommand, expectedCommand);
    }

    /*
     * Tests for multiple argument commands ===============================================================
     */

    @Test
    public void parse_addCommandArg_ParsedCorrectly() {
        final String input = "add n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/Monday i/high a/Refer to slides";
        final AddCommand parsedCommand = parseAndAssertCommandType(input, AddCommand.class);
        final AddCommand expectedCommand = new AddCommand("Assignment1", "1100",
                "2 hours", "16-09-2020", "Monday","high",
                "Refer to slides");
        assertEquals(parsedCommand, expectedCommand);
    }

    @Test
    public void parse_editCommandArg_ParsedCorrectly() {
        final int testNumber = 1;
        final String input = "edit 1 n/Assignment1 t/1100 D/16-09-2020 d/2 hours r/Monday i/high a/Refer to slides";
        final EditCommand parsedCommand = parseAndAssertCommandType(input, EditCommand.class);
        final EditCommand expectedCommand = new EditCommand(testNumber,"Assignment1", "1100",
                "2 hours", "16-09-2020", "Monday", Importance.valueOf("high".toUpperCase()),
                "Refer to slides");
        assertEquals(parsedCommand, expectedCommand);
    }

    @Test
    public void parse_listCommandArg_ParsedCorrectly() {
        final String input = "list f/WEEK i/medium";
        final ListCommand parsedCommand = parseAndAssertCommandType(input, ListCommand.class);
        final ListCommand expectedCommand = new ListCommand(Importance.valueOf("medium".toUpperCase()), "WEEK");
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
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}