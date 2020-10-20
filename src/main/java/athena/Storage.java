package athena;

import athena.exceptions.StorageCorruptedException;
import athena.exceptions.StorageLoadFailException;
import athena.task.Task;

import java.io.*;

/**
 * Converts TaskLists to .csv files and back
 */

public class Storage {
    private String filePath;
    private TaskList tasks;

    private int size;

    /**
     * Initialises Storage object.
     *
     * @param filepath Location of the save file
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    private String replaceCommas(String info) {
        return info.replace(",", "]c}");
    }

    /**
     * Takes a TaskList and converts it to a .csv file.
     *
     * @param tasks tasks to be saved as strings
     */
    public void saveTaskListData(TaskList tasks) {
        this.tasks = tasks;
        String taskString = null;
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                taskString = replaceCommas(task.getName()) + "," + replaceCommas(task.getStartTime()) + ","
                        + replaceCommas(task.getDuration()) + "," + replaceCommas(task.getDeadline()) + ","
                        + replaceCommas(task.getRecurrence()) + "," + task.getImportance() + ","
                        + replaceCommas(task.getNotes()) + "," + task.getNumber();
                if (task.isFlexible()) {
                    taskString = taskString + "," + "true";
                } else {
                    taskString = taskString + "," + "false";
                }
                csvWriter.append(taskString + "\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves Tasklist from .csv file
     *
     * @return TaskList object equivalent of save file
     */

    //TODO: add compatibility for more task attributes
    public TaskList loadTaskListData() throws StorageLoadFailException, StorageCorruptedException {
        File csvFile = new File(filePath);
        TaskList output = new TaskList();
        int maxNumber = 0;
        String[] data = new String[0];
        try {
            if (csvFile.isFile()) {
                String row;
                BufferedReader csvReader = null;
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    data = row.split(",");
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("]c}", ",");
                    }
                    output.addTask(Integer.parseInt(data[7]), data[0], data[1], data[2], data[3], data[4],
                            Importance.valueOf(data[5].toUpperCase()), data[6], Boolean.parseBoolean(data[8]));
                    maxNumber = Integer.parseInt(data[7]);
                }
                csvReader.close();
            }
            output.setMaxNumber(maxNumber);
            return output;
        } catch (IOException e) {
            throw new StorageLoadFailException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new StorageCorruptedException(data);
        }
    }
}
