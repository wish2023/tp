package athena;

/**
 * Handles parsing of user input
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

    /**
     * Get Parameters description for Add command.
     * @param taskInformation String representing task information
     * @param delimiter String representing parameter delimiter
     * @return Description of parameter
     */
    public static String getParameterForAdd(String taskInformation, String delimiter) {
        String param;
        String[] retrieveParamInfo = taskInformation.split(delimiter, 2);
        String retrievedParamInfo = retrieveParamInfo[1];
        int paramNextSlash = retrievedParamInfo.indexOf("/");
        if (paramNextSlash == -1) {
            param = retrievedParamInfo;
        } else {
            param = retrievedParamInfo.substring(0, (paramNextSlash - 2));
        }
        return param;
    }

    /**
     * Get Parameters description for Edit and List command.
     * @param taskInformation String representing task information
     * @param delimiter String representing parameter delimiter
     * @param paramPosition Integer representing position of parameter
     * @return Description of parameter
     */
    public static String getParameterForEditAndList(String taskInformation, String delimiter, int paramPosition) {
        String param;
        if (paramPosition == -1) {
            param = "";
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
     * Parses user input and recognises what type of command
     * and parameters the user typed.
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

        int nPosition = taskInfo.indexOf(NAME_DELIMITER);
        int tPosition = taskInfo.indexOf(TIME_DELIMITER);
        int dPosition = taskInfo.indexOf(DURATION_DELIMITER);
        int DPosition = taskInfo.indexOf(DEADLINE_DELIMITER);
        int rPosition = taskInfo.indexOf(RECURRENCE_DELIMITER);
        int iPosition = taskInfo.indexOf(IMPORTANCE_DELIMITER);
        int aPosition = taskInfo.indexOf(ADDITIONAL_NOTES_DELIMITER);

        if (commandType.equals("add")) {
            String name = getParameterForAdd(taskInfo, NAME_DELIMITER);
            String time = getParameterForAdd(taskInfo, TIME_DELIMITER);
            String duration;
            if (dPosition == -1) {
                duration = "1 hour";
            } else {
                duration = getParameterForAdd(taskInfo, DURATION_DELIMITER);
            }
            String deadline;
            if (DPosition == -1) {
                deadline = "No deadline";
            } else {
                deadline = getParameterForAdd(taskInfo, DEADLINE_DELIMITER);
            }
            String recurrence;
            if (rPosition == -1) {
                recurrence = "Once-off, happening today";
            } else {
                recurrence = getParameterForAdd(taskInfo, RECURRENCE_DELIMITER);
            }
            String importance;
            if (iPosition == -1) {
                importance = "medium";
            } else {
                importance = getParameterForAdd(taskInfo, IMPORTANCE_DELIMITER);
            }
            String notes;
            if (aPosition == -1) {
                notes = "No notes";
            } else {
                notes = getParameterForAdd(taskInfo, ADDITIONAL_NOTES_DELIMITER);
            }
            command = new AddCommand(name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("edit")) {
            int indexNextSlash = taskInfo.indexOf("/");
            int index = Integer.parseInt(taskInfo.substring(0, (indexNextSlash - 2)));
            String name = getParameterForEditAndList(taskInfo, NAME_DELIMITER, nPosition);
            String time = getParameterForEditAndList(taskInfo, TIME_DELIMITER, tPosition);
            String duration = getParameterForEditAndList(taskInfo, DURATION_DELIMITER, dPosition);
            String deadline = getParameterForEditAndList(taskInfo, DEADLINE_DELIMITER, DPosition);
            String recurrence = getParameterForEditAndList(taskInfo, RECURRENCE_DELIMITER, rPosition);
            String importance = getParameterForEditAndList(taskInfo, IMPORTANCE_DELIMITER, iPosition);
            String notes = getParameterForEditAndList(taskInfo, ADDITIONAL_NOTES_DELIMITER, aPosition);
            command = new EditCommand(index, name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("list")) {
            String importance = getParameterForEditAndList(taskInfo, IMPORTANCE_DELIMITER, iPosition);
            command = new ListCommand(importance);
        } else if (commandType.equals("done")) {
            int taskIndex = Integer.parseInt(taskInfo);
            command = new DoneCommand(taskIndex);
        } else if (commandType.equals("delete")) {
            int taskIndex = Integer.parseInt(taskInfo);
            command = new DeleteCommand(taskIndex);
        } else if (commandType.equals("help")) {
            command = new HelpCommand();
        } else {
            if (commandType.equals("bye")) {
                command = new ExitCommand();
            }
        }
        return command;
    }
}