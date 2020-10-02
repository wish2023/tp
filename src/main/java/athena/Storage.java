package athena;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Storage {
    private String filePath;
    private taskList tasks;

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

    public taskList loadTaskListData() {
        File csvFile = new File(filePath);
        taskList output = null;
        if (csvFile.isFile()) {
            String row;
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split("\t");
                    output= new taskList(row);
                    output.add(data);
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
