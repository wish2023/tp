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
     * Parses user input and recognises what type of command
     * and parameters the user typed
     *
     * @param input String representing user input
     * @return new Command object based on what the user input is
     */
    public static Command parse(String input) {
        String[] commandAndDetails = input.split(COMMAND_WORD_DELIMITER, 2);
        String commandType = commandAndDetails[0];
        String taskInfo = "";
        if (commandAndDetails.length > 1)
            taskInfo = commandAndDetails[1];

        Command command = null;

        int nPosition = taskInfo.indexOf(NAME_DELIMITER);
        int tPosition = taskInfo.indexOf(TIME_DELIMITER);
        int dPosition = taskInfo.indexOf(DURATION_DELIMITER);
        int DPosition = taskInfo.indexOf(DEADLINE_DELIMITER);
        int rPosition = taskInfo.indexOf(RECURRENCE_DELIMITER);
        int iPosition = taskInfo.indexOf(IMPORTANCE_DELIMITER);
        int aPosition = taskInfo.indexOf(ADDITIONAL_NOTES_DELIMITER);

        if (commandType.equals("add")) {
            String[] retrieveName = taskInfo.split(NAME_DELIMITER, 2);
            String retrievedName = retrieveName[1];
            String name;
            int nameNextSlash = retrievedName.indexOf("/");
            if (nameNextSlash == -1) {
                name = retrievedName;
            } else {
                name = retrievedName.substring(0, (nameNextSlash - 2));
            }

            String[] retrieveTime = taskInfo.split(TIME_DELIMITER, 2);
            String retrievedTime = retrieveTime[1];
            String time;
            int timeNextSlash = retrievedTime.indexOf("/");
            if (timeNextSlash == -1) {
                time = retrievedTime;
            } else {
                time = retrievedTime.substring(0, (timeNextSlash - 2));
            }

            String duration;
            if (dPosition == -1) {
                duration = "1 hour";
            } else {
                String[] retrieveDuration = taskInfo.split(DURATION_DELIMITER, 2);
                String retrievedDuration = retrieveDuration[1];
                int durationNextSlash = retrievedDuration.indexOf("/");
                if (durationNextSlash == -1) {
                    duration = retrievedDuration;
                } else {
                    duration = retrievedDuration.substring(0, (durationNextSlash - 2));
                }
            }

            String deadline;
            if (DPosition == -1) {
                deadline = "No deadline";
            } else {
                String[] retrieveDeadline = taskInfo.split(DEADLINE_DELIMITER, 2);
                String retrievedDeadline = retrieveDeadline[1];
                int deadlineNextSlash = retrievedDeadline.indexOf("/");
                if (deadlineNextSlash == -1) {
                    deadline = retrievedDeadline;
                } else {
                    deadline = retrievedDeadline.substring(0, (deadlineNextSlash - 2));
                }
            }

            String recurrence;
            if (rPosition == -1) {
                recurrence = "Once-off, happening today";
            } else {
                String[] retrieveRecurrence = taskInfo.split(RECURRENCE_DELIMITER, 2);
                String retrievedRecurrence = retrieveRecurrence[1];
                int recurrenceNextSlash = retrievedRecurrence.indexOf("/");
                if (recurrenceNextSlash == -1) {
                    recurrence = retrievedRecurrence;
                } else {
                    recurrence = retrievedRecurrence.substring(0, (recurrenceNextSlash - 2));
                }
            }

            String importance;
            if (iPosition == -1) {
                importance = "medium";
            } else {
                String[] retrieveImportance = taskInfo.split(IMPORTANCE_DELIMITER, 2);
                String retrievedImportance = retrieveImportance[1];
                int importanceNextSlash = retrievedImportance.indexOf("/");
                if (importanceNextSlash == -1) {
                    importance = retrievedImportance;
                } else {
                    importance = retrievedImportance.substring(0, (importanceNextSlash - 2));
                }
            }

            String notes;
            if (aPosition == -1) {
                notes = "No notes";
            } else {
                String[] retrieveNotes = taskInfo.split(ADDITIONAL_NOTES_DELIMITER, 2);
                String retrievedNotes = retrieveNotes[1];
                int notesNextSlash = retrievedNotes.indexOf("/");
                if (notesNextSlash == -1) {
                    notes = retrievedNotes;
                } else {
                    notes = retrievedNotes.substring(0, (notesNextSlash - 2));
                }
            }

            command = new AddCommand(name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("edit")) {
            int indexNextSlash = taskInfo.indexOf("/");
            int index = Integer.parseInt(taskInfo.substring(0, (indexNextSlash - 2)));

            String name;
            if (nPosition == -1) {
                name = null;
            } else {
                String[] retrieveName = taskInfo.split(NAME_DELIMITER, 2);
                String retrievedName = retrieveName[1];
                int nameNextSlash = retrievedName.indexOf("/");
                if (nameNextSlash == -1) {
                    name = retrievedName;
                } else {
                    name = retrievedName.substring(0, (nameNextSlash - 2));
                }
            }

            String time;
            if (tPosition == -1) {
                time = null;
            } else {
                String[] retrieveTime = taskInfo.split(TIME_DELIMITER, 2);
                String retrievedTime = retrieveTime[1];
                int timeNextSlash = retrievedTime.indexOf("/");
                if (timeNextSlash == -1) {
                    time = retrievedTime;
                } else {
                    time = retrievedTime.substring(0, (timeNextSlash - 2));
                }
            }

            String duration;
            if (dPosition == -1) {
                duration = null;
            } else {
                String[] retrieveDuration = taskInfo.split(DURATION_DELIMITER, 2);
                String retrievedDuration = retrieveDuration[1];
                int durationNextSlash = retrievedDuration.indexOf("/");
                if (durationNextSlash == -1) {
                    duration = retrievedDuration;
                } else {
                    duration = retrievedDuration.substring(0, (durationNextSlash - 2));
                }
            }

            String deadline;
            if (DPosition == -1) {
                deadline = null;
            } else {
                String[] retrieveDeadline = taskInfo.split(DEADLINE_DELIMITER, 2);
                String retrievedDeadline = retrieveDeadline[1];
                int deadlineNextSlash = retrievedDeadline.indexOf("/");
                if (deadlineNextSlash == -1) {
                    deadline = retrievedDeadline;
                } else {
                    deadline = retrievedDeadline.substring(0, (deadlineNextSlash - 2));
                }
            }

            String recurrence;
            if (rPosition == -1) {
                recurrence = null;
            } else {
                String[] retrieveRecurrence = taskInfo.split(RECURRENCE_DELIMITER, 2);
                String retrievedRecurrence = retrieveRecurrence[1];
                int recurrenceNextSlash = retrievedRecurrence.indexOf("/");
                if (recurrenceNextSlash == -1) {
                    recurrence = retrievedRecurrence;
                } else {
                    recurrence = retrievedRecurrence.substring(0, (recurrenceNextSlash - 2));
                }
            }

            String importance;
            if (iPosition == -1) {
                importance = null;
            } else {
                String[] retrieveImportance = taskInfo.split(IMPORTANCE_DELIMITER, 2);
                String retrievedImportance = retrieveImportance[1];
                int importanceNextSlash = retrievedImportance.indexOf("/");
                if (importanceNextSlash == -1) {
                    importance = retrievedImportance;
                } else {
                    importance = retrievedImportance.substring(0, (importanceNextSlash - 2));
                }
            }

            String notes;
            if (aPosition == -1) {
                notes = null;
            } else {
                String[] retrieveNotes = taskInfo.split(ADDITIONAL_NOTES_DELIMITER, 2);
                String retrievedNotes = retrieveNotes[1];
                int notesNextSlash = retrievedNotes.indexOf("/");
                if (notesNextSlash == -1) {
                    notes = retrievedNotes;
                } else {
                    notes = retrievedNotes.substring(0, (notesNextSlash - 2));
                }
            }

            command = new EditCommand(index, name, time, duration, deadline, recurrence, importance, notes);
        } else if (commandType.equals("list")) {
            String importance = "";
            if (iPosition == -1) {
                importance = "";
            } else {
                String[] retrieveImportance = taskInfo.split(IMPORTANCE_DELIMITER, 2);
                String retrievedImportance = retrieveImportance[1];
                int importanceNextSlash = retrievedImportance.indexOf("/");
                if (importanceNextSlash == -1) {
                    importance = retrievedImportance;
                } else {
                    importance = retrievedImportance.substring(0, (importanceNextSlash - 2));
                }
            }
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