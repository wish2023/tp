package athena;
import java.io.*;


public class Storage {
    private String filePath;
    private TaskList tasks;

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

    public Storage(String filepath) {
        this.filePath = filepath;

    }
    public void saveTaskListData(TaskList tasks) {
        this.tasks=tasks;
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            for(Task task : tasks.getList()) {
                csvWriter.append(task.getName()+","+task.getStartTime()+","+task.getDeadline()+","+"\n");
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
                    Task currentTask = new Task( data[0],  data[1],  data[2]);
                    output.addToList(currentTask);
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
