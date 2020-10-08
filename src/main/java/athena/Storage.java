package athena;

import java.io.*;


public class Storage {
    private String filePath;
    private TaskList tasks;
    private Ui ui;

    /**
     * methods needed from tasklist
     * Name
     * Time
     * Duration
     * Deadline
     * Recurrence
     * Importance
     * Notes
     * Index
     * Restore
     * task and tasklist constructor
     * <p>
     * methods given to tasklist
     * saveTaskListData(index) if index -1 then save everything
     * loadTaskListData return taskList and highest index(-1 if no existing data)
     * <p>
     * methods needed from Timetable
     * date
     * time
     * task
     * index
     * <p>
     * methods given to Timetable
     * saveTimetableData()
     * loadTimetableData return timetable
     * <p>
     * methods given to UI
     * lastSaved
     * index
     * <p>
     * methods given to Parser
     * ???
     */

    private int size;

    public Storage(String filepath, Ui ui) {
        this.filePath = filepath;
        this.ui = ui;

    }

    public void saveTaskListData(TaskList tasks) {
        this.tasks = tasks;
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                csvWriter.append(task.getName() + "," + task.getStartTime() + "," + task.getDuration() + "," + task.getDeadline() + "," +
                        task.getRecurrence() + "," + task.getImportance() + "," + task.getNotes() + ","
                        + task.getIndex() + "\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TaskList loadTaskListData() {
        File csvFile = new File(filePath);
        TaskList output = new TaskList();
        int maxIndex = -1;
        if (csvFile.isFile()) {
            String row;
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    output.addTask(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
                            Integer.parseInt(data[7]));
                    maxIndex = Integer.parseInt(data[7]);
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
        output.setMaxIndex(maxIndex);
        return output;
    }


}
