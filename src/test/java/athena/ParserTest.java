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

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_unknownCommandWord_returnsHelp() {
        final String input = "unknowncommandword arguments arguments";
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
     * Utility methods ====================================================================================
     */

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parse(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}