package athena.logic;

import athena.Importance;
import athena.TaskList;
import athena.exceptions.command.CommandException;
import athena.exceptions.command.DeleteNoIndexException;
import athena.exceptions.command.DoneNoIndexException;
import athena.exceptions.command.EditNoIndexException;
import athena.exceptions.command.InvalidCommandException;
import athena.exceptions.command.InvalidForecastException;
import athena.exceptions.command.InvalidImportanceException;
import athena.exceptions.command.TaskNotFoundException;
import athena.exceptions.command.ViewNoIndexException;
import athena.logic.commands.AddCommand;
import athena.logic.commands.Command;
import athena.logic.commands.DeleteCommand;
import athena.logic.commands.DoneCommand;
import athena.logic.commands.EditCommand;
import athena.logic.commands.ExitCommand;
import athena.logic.commands.HelpCommand;
import athena.logic.commands.ListCommand;
import athena.logic.commands.ViewCommand;
import athena.task.Task;
import athena.task.taskfilter.FilterCalculator;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static final String EMPTY_STRING = "";

    /**
     * Get parameters description.
     *
     * @param taskInformation String representing task information
     * @param delimiter       String representing parameter delimiter
     * @param paramPosition   Integer representing position of parameter
     * @param defaultValue    String representing default value
     * @return Description of parameter
     * @throws InvalidCommandException Exception thrown when the the user does not specify a valid command
     */
    public String getParameterDesc(String taskInformation, String delimiter, int paramPosition,
                                          String defaultValue) throws InvalidCommandException {
        String param;
        if (paramPosition == -1) {
            param = defaultValue;
        } else {
            String[] retrieveParamInfo = taskInformation.split(delimiter, 2);
            CharSequence retrievedParamInfo = retrieveParamInfo[1];

            String patternStr = "\\s\\w+\\/";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(retrievedParamInfo);

            if (matcher.find()) {
                int nextParam = matcher.start();
                try {
                    param = retrievedParamInfo.subSequence(0, (nextParam)).toString();
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidCommandException();
                }
            } else {
                param = retrievedParamInfo.toString();
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
     * @throws InvalidCommandException Exception thrown when the the user does not specify a valid command
     * @throws InvalidImportanceException Exception thrown when the user does not specify a valid importance
     */
    public Command parseAddCommand(String taskInfo, int namePos, int timePos, int durationPos, int deadlinePos,
                                          int recurrencePos, int importancePos, int addNotesPos)
            throws InvalidCommandException, InvalidImportanceException {
        String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, EMPTY_STRING);
        String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, EMPTY_STRING);
        boolean isFlexible = (time.equals(EMPTY_STRING));
        String durationDefault = "1";
        String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos, durationDefault);
        String deadlineDefault = "No deadline";
        String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, deadlineDefault);
        String recurrenceDefault = "today";
        String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos, recurrenceDefault);
        String importanceDefault = "medium";
        String importanceString = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, importanceDefault);
        Importance importance;
        try {
            importance = Importance.valueOf(importanceString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidImportanceException();
        }
        String notesDefault = "No notes";
        String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos, notesDefault);

        return new AddCommand(name, time, duration, deadline, recurrence, importance, notes, isFlexible);
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
     * @throws TaskNotFoundException Exception thrown when the program is unable to find a task at the index
     *                               specified by the user
     * @throws EditNoIndexException  Exception thrown when the user does not specify an index of the task they
     *                               want to edit
     * @throws InvalidCommandException Exception thrown when the the user does not specify a valid command
     * @throws InvalidImportanceException Exception thrown when the user does not specify a valid importance
     */
    public Command parseEditCommand(String taskInfo, int namePos, int timePos, int durationPos, int deadlinePos,
                                           int recurrencePos, int importancePos, int addNotesPos, TaskList taskList)
            throws TaskNotFoundException, EditNoIndexException, InvalidCommandException, InvalidImportanceException {
        int number = getNumber(taskInfo);

        Task task = taskList.getTaskFromNumber(number);

        String name = getParameterDesc(taskInfo, NAME_DELIMITER, namePos, task.getName());
        String time = getParameterDesc(taskInfo, TIME_DELIMITER, timePos, task.getTimeInfo().getStartTimeString());
        String duration = getParameterDesc(taskInfo, DURATION_DELIMITER, durationPos,
                task.getTimeInfo().getDurationString());
        String deadline = getParameterDesc(taskInfo, DEADLINE_DELIMITER, deadlinePos, task.getTimeInfo().getDeadline());
        String recurrence = getParameterDesc(taskInfo, RECURRENCE_DELIMITER, recurrencePos,
                task.getTimeInfo().getRecurrence());
        String importanceString = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos,
                task.getImportance().toString());

        Importance importance;
        try {
            importance = Importance.valueOf(importanceString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidImportanceException();
        }
        String notes = getParameterDesc(taskInfo, ADDITIONAL_NOTES_DELIMITER, addNotesPos,
                taskList.getTaskFromNumber(number).getNotes());

        return new EditCommand(number, name, time, duration, deadline, recurrence, importance, notes);
    }

    /**
     * Parses task information to get task number.
     *
     * @param taskInfo String representing task information
     * @return task number
     * @throws EditNoIndexException Exception thrown when the user does not specify an index of the task they
     *                              want to edit
     */
    private int getNumber(String taskInfo) throws EditNoIndexException {
        try {
            int numberNextSlash = taskInfo.indexOf("/");
            int number = Integer.parseInt(taskInfo.substring(0, (numberNextSlash - 2)));
            return number;
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new EditNoIndexException();
        }
    }

    /**
     * Parses user input when command is list.
     *
     * @param taskInfo      String representing task information
     * @param importancePos Integer representing position of importance parameter
     * @param forecastPos   Integer representing position of forecast parameter
     * @return command object
     * @throws InvalidCommandException Exception thrown when the the user does not specify a valid command
     * @throws InvalidForecastException Exception thrown when the user does not specify a valid forecast
     * @throws InvalidImportanceException Exception thrown when the user does not specify a valid importance
     */
    public Command parseListCommand(String taskInfo, int importancePos, int forecastPos)
            throws InvalidCommandException, InvalidForecastException, InvalidImportanceException {
        String importanceDefault = "ALL";
        String forecastDefault = "WEEK";
        String importanceString = getParameterDesc(taskInfo, IMPORTANCE_DELIMITER, importancePos, importanceDefault);
        String forecastString = getParameterDesc(taskInfo, FORECAST_DELIMITER, forecastPos, forecastDefault);
        FilterCalculator filterCalculator = new FilterCalculator(importanceString, forecastString);

        return new ListCommand(filterCalculator.getImportance(), filterCalculator.getForecast());
    }

    /**
     * Parses user input when command is done.
     *
     * @param taskInfo String representing task information
     * @return command object
     * @throws DoneNoIndexException Exception thrown when the user does not specify an index of the task they
     *                              want to mark as done
     */
    public Command parseDoneCommand(String taskInfo) throws DoneNoIndexException {
        try {
            int taskIndex = Integer.parseInt(taskInfo);
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DoneNoIndexException();
        }
    }

    /**
     * Parses user input when command is delete.
     *
     * @param taskInfo String representing task information
     * @return command object
     * @throws DeleteNoIndexException Exception thrown when the user does not specify an index of the task they
     *                              want to delete
     */
    public Command parseDeleteCommand(String taskInfo) throws DeleteNoIndexException {
        try {
            int taskIndex = Integer.parseInt(taskInfo);
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DeleteNoIndexException();
        }
    }

    /**
     * Parses user input when command is view.
     *
     * @param taskInfo      String representing task information
     * @return command object
     * @throws ViewNoIndexException Exception thrown when the user does not specify an index of the task they
     *                              want to view
     */
    public Command parseViewCommand(String taskInfo) throws ViewNoIndexException {
        try {
            int taskIndex = Integer.parseInt(taskInfo);
            return new ViewCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ViewNoIndexException();
        }
    }

    /**
     * Parses user input for shortcut commands.
     *
     * @param userInput String representing command and information of task
     * @return String representing what the shortcut commands meant
     */
    public String parseShortcutCommands(String userInput) {
        HashMap<String, String> shortcutCommandsWithDetails = new HashMap<>();
        shortcutCommandsWithDetails.put("a", "add");
        shortcutCommandsWithDetails.put("e", "edit");
        shortcutCommandsWithDetails.put("l", "list");
        shortcutCommandsWithDetails.put("dn", "done");
        shortcutCommandsWithDetails.put("dl", "delete");
        shortcutCommandsWithDetails.put("v", "view");
        shortcutCommandsWithDetails.put("ex", "exit");
        shortcutCommandsWithDetails.put("l3", "list i/HIGH");
        shortcutCommandsWithDetails.put("l2", "list i/MEDIUM");
        shortcutCommandsWithDetails.put("l1", "list i/LOW");
        shortcutCommandsWithDetails.put("lw", "list f/WEEK");
        shortcutCommandsWithDetails.put("ld", "list f/DAY");
        shortcutCommandsWithDetails.put("la", "list f/ALL");

        String actualInputMeaning = userInput;
        if (shortcutCommandsWithDetails.get(actualInputMeaning) != null) {
            actualInputMeaning = shortcutCommandsWithDetails.get(actualInputMeaning);
        }
        return actualInputMeaning;
    }

    /**
     * Parses user input to split shortcut command and task information.
     *
     * @param userInput String representing task information
     * @return String representing full input meaning
     */
    public String parseShortcutCommandAndDetails(String userInput) {
        String[] commandAndDetails = userInput.split(COMMAND_WORD_DELIMITER, 2);
        String shortcutInput = parseShortcutCommands(commandAndDetails[0]);
        String remainingTaskInfo = "";
        if (commandAndDetails.length > 1) {
            remainingTaskInfo = commandAndDetails[1];
        }
        String fullInput = shortcutInput + " " + remainingTaskInfo;
        fullInput = fullInput.trim();
        return fullInput;
    }

    /**
     * Parses user input and recognises what type of command
     * and parameters the user typed.
     *
     * @param userInput    String representing user input
     * @param taskList Tasks list
     * @return new Command object based on what the user input is
     * @throws CommandException Exception thrown when there is an error when the user inputs a command
     */
    public Command parse(String userInput, TaskList taskList) throws CommandException {
        String fullInput = parseShortcutCommandAndDetails(userInput);
        String[] commandAndDetails = fullInput.split(COMMAND_WORD_DELIMITER, 2);
        String commandType = commandAndDetails[0];
        String taskInfo = "";
        if (commandAndDetails.length > 1) {
            taskInfo = commandAndDetails[1];
        }

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
                    recurrencePos, importancePos, addNotesPos, taskList);
        }

        case "list": {
            return parseListCommand(taskInfo, importancePos, forecastPos);
        }

        case "done": {
            return parseDoneCommand(taskInfo);
        }

        case "delete": {
            return parseDeleteCommand(taskInfo);
        }

        case "view": {
            return parseViewCommand(taskInfo);
        }

        case "help": {
            return new HelpCommand();
        }

        case "exit": {
            return new ExitCommand();
        }

        default: {
            throw new InvalidCommandException();
        }
        }
    }
}