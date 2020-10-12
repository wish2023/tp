package athena;

import athena.commands.Command;
import athena.commands.AddCommand;
import athena.commands.DeleteCommand;
import athena.commands.DoneCommand;
import athena.commands.EditCommand;
import athena.commands.ExitCommand;
import athena.commands.HelpCommand;
import athena.commands.ListCommand;


/**
 * Handles parsing of user input.
 */
public class Parser {

    public static final String COMMAND_WORD_DELIMITER = " ";
    public static final String NAME_DELIMITER = "n/";
    public static final String TIME_DELIMITER = "t/";
    public static final String DURATION_DELIMITER = "d/";
    public static final String DEADLINE_DELIMITER = "D/";
    public static final String RECURRENCE_DELIMITER = "r/";
    public static final String IMPORTANCE_DELIMITER = "i/";
    public static final String ADDITIONAL_NOTES_DELIMITER = "a/";
    public static final String FORECAST_DELIMITER = "f/";

    /**
     * Get parameters description.
     *
     * @param taskInformation String representing task information
     * @param delimiter       String representing parameter delimiter
     * @param paramPosition   Integer representing position of parameter
     * @param defaultValue    String representing default value
     * @return Description of parameter
     */
    public static String getParameterDesc(String taskInformation, String delimiter, int paramPosition,
                                          String defaultValue) {
        String param;
        if (paramPosition == -1) {
            param = defaultValue;
        } else {
            String[] retrieveParamInfo = taskInformation.split(delimiter, 2);
            String retrievedParamInfo = retrieveParamInfo[1];
            int paramNextSlash = retrievedParamInfo.indexOf("/");
            if (paramNextSlash == -1) {
                param = retrievedParamInfo;
            } else {
                param = retrievedParamInfo.substring(0, (paramNextSlash - 2));
            }
        }
        return param;
    }

    /**
     * Parses user input when command is add.
     *
     * @param taskInfo      String representing task information
     * @param namePos       Integer representing position of name parameter
     * @param timePos       Integer representing position of time parameter
     * @param durationPos   Integer representing position of duration parameter
     * @param deadlinePos   Integer representing position of deadline parameter
     * @param recurrencePos Integer representing position of recurrence parameter
     * @param importancePos Integer representing position of importance parameter
     * @param addNotesPos   Integer representing position of additional notes parameter
     * @return command object
     */
    public static Command parseAddCommand(String taskInfo, int namePos, int timePos, int durationPos, int deadlinePos,
                                          int recurrencePos, int importancePos, int addNotesPos) {
        String nullDefault = "";
        String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, nullDefault);
        String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, nullDefault);
        String durationDefault = "1 hour";
        String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos, durationDefault);
        String deadlineDefault = "No deadline";
        String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, deadlineDefault);
        String recurrenceDefault = "today";
        String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos, recurrenceDefault);
        String importanceDefault = "medium";
        String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, importanceDefault);
        String notesDefault = "No notes";
        String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos, notesDefault);
        Command command = new AddCommand(name, time, duration, deadline, recurrence, importance, notes);

        return command;
    }

    /**
     * Parses user input when command is edit.
     *
     * @param taskInfo      String representing task information
     * @param namePos       Integer representing position of name parameter
     * @param timePos       Integer representing position of time parameter
     * @param durationPos   Integer representing position of duration parameter
     * @param deadlinePos   Integer representing position of deadline parameter
     * @param recurrencePos Integer representing position of recurrence parameter
     * @param importancePos Integer representing position of importance parameter
     * @param addNotesPos   Integer representing position of additional notes parameter
     * @return command object
     */
    public static Command parseEditCommand(String taskInfo, int namePos, int timePos, int durationPos, int deadlinePos,
                                           int recurrencePos, int importancePos, int addNotesPos) {
        int indexNextSlash = taskInfo.indexOf("/");
        int index = Integer.parseInt(taskInfo.substring(0, (indexNextSlash - 2)));
        String nullValue = "";
        String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, nullValue);
        String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, nullValue);
        String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos, nullValue);
        String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, nullValue);
        String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos, nullValue);
        String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, nullValue);
        String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos, nullValue);
        Command command = new EditCommand(index, name, time, duration, deadline, recurrence,
                Importance.valueOf(importance), notes);

        return command;
    }

    /**
     * Parses user input when command is list.
     *
     * @param taskInfo      String representing task information
     * @param importancePos Integer representing position of importance parameter
     * @param forecastPos   Integer representing position of forecast parameter
     * @return command object
     */
    public static Command parseListCommand(String taskInfo, int importancePos, int forecastPos) {
        String importanceDefault = "ALL";
        String forecastDefault = "TODAY";
        String importance = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, importanceDefault);
        String forecast = getParameterDesc(taskInfo, FORECAST_DELIMITER, forecastPos, forecastDefault);
        Command command = new ListCommand(Importance.valueOf(importance.toUpperCase()),
                Forecast.valueOf(forecast.toUpperCase()));
        return command;
    }

    /**
     * Parses user input and recognises what type of command
     * and parameters the user typed.
     *
     * @param input String representing user input
     * @return new Command object based on what the user input is
     */
    public static Command parse(String input) {
        String[] commandAndDetails = input.split(COMMAND_WORD_DELIMITER, 2);
        String commandType = commandAndDetails[0];
        String taskInfo = "";
        if (commandAndDetails.length > 1) {
            taskInfo = commandAndDetails[1];
        }
        Command command = null;

        int namePos = taskInfo.indexOf(NAME_DELIMITER);
        int timePos = taskInfo.indexOf(TIME_DELIMITER);
        int durationPos = taskInfo.indexOf(DURATION_DELIMITER);
        int deadlinePos = taskInfo.indexOf(DEADLINE_DELIMITER);
        int recurrencePos = taskInfo.indexOf(RECURRENCE_DELIMITER);
        int importancePos = taskInfo.indexOf(IMPORTANCE_DELIMITER);
        int addNotesPos = taskInfo.indexOf(ADDITIONAL_NOTES_DELIMITER);
        int forecastPos = taskInfo.indexOf(FORECAST_DELIMITER);

        switch (commandType) {

        case "add": {
            return parseAddCommand(taskInfo, namePos, timePos, durationPos, deadlinePos,
                    recurrencePos, importancePos, addNotesPos);
        }

        case "edit": {
            return parseEditCommand(taskInfo, namePos, timePos, durationPos, deadlinePos,
                    recurrencePos, importancePos, addNotesPos);
        }

        case "list": {
            return parseListCommand(taskInfo, importancePos, forecastPos);
        }

        case "done": {
            int taskIndex = Integer.parseInt(taskInfo);
            return new DoneCommand(taskIndex);
        }

        case "delete": {
            int taskIndex = Integer.parseInt(taskInfo);
            return new DeleteCommand(taskIndex);
        }

        case "bye": {
            return new ExitCommand();
        }

        case "help":
        default:
            return new HelpCommand();
        }
    }
}