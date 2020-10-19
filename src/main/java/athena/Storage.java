package athena;

import athena.task.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * Converts TaskLists to .csv files and back
 */

public class Storage {
    private String filePath;
    private TaskList tasks;
    private Ui ui;


    private int size;

    /**
     * Initialises Storage object.
     *
     * @param filepath Location of the save file
     * @param ui prints out error messages
     */
    public Storage(String filepath, Ui ui) {
        this.filePath = filepath;
        this.ui = ui;

    }

    /**
     * Takes a TaskList and converts it to a .csv file.
     *
     * @param tasks tasks to be saved as strings
     */
    public void saveTaskListData(TaskList tasks) {
        this.tasks = tasks;
        String taskString = null;
        //TODO: add compatibility for more task attributes
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                taskString = task.getName() + "|" + task.getStartTime() + "|" + task.getDuration() + "|"
                        + task.getDeadline() + "|" + task.getRecurrence() + "|" + task.getImportance() + "|"
                        + task.getNotes() + "|" + task.getNumber();
                taskString = taskString.replaceAll(",", "]c}").replace("|",
                        ",");
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
    public TaskList loadTaskListData() {
        File csvFile = new File(filePath);
        TaskList output = new TaskList();
        int maxNumber = 0;
        if (csvFile.isFile()) {
            String row;
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("]c}", ",");
                    }
                    output.addTask(Integer.parseInt(data[7]), data[0], data[1], data[2], data[3], data[4],
                            Importance.valueOf(data[5].toUpperCase()), data[6]);
                    maxNumber = Integer.parseInt(data[7]);
                }

                csvReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printInvalidTask();
            }

        }
        output.setMaxNumber(maxNumber);
        return output;
    }


}
