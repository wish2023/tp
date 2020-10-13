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
     * @param filepath Location of the save file
     * @param ui prints out error messages
     */
    public Storage(String filepath, Ui ui) {
        this.filePath = filepath;
        this.ui = ui;

    }

    /**
     * @param tasks tasks to be saved as strings
     */
    public void saveTaskListData(TaskList tasks) {
        this.tasks = tasks;
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                csvWriter.append(task.getName() + "," + task.getStartTime() + "," + task.getDuration() + ","
                        + task.getDeadline() + "," + task.getRecurrence() + "," + task.getImportance() + ","
                        + task.getNotes() + "," + task.getNumber() + "\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return TaskList object equivalent of save file
     */
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
