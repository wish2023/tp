package athena;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


public class Storage {
    private String filePath;
    private TaskList tasks;


    private int size;

    public Storage(String filepath) {
        this.filePath = filepath;

    }

    public void saveTaskListData(TaskList tasks) {
        this.tasks = tasks;
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                csvWriter.append(task.getName() + "," + task.getStartTime() + "," + task.getDuration() + ","
                        + task.getDeadline() + "," + task.getRecurrence() + "," + task.getImportance() + ","
                        + task.getNotes() + "\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public TaskList loadTaskListData() {
        File csvFile = new File(filePath);
        TaskList output = new TaskList();
        if (csvFile.isFile()) {
            String row;
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    output.addTask(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
                }
                csvReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return output;
    }


}
