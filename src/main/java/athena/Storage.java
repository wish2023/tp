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
     *
     * methods given to tasklist
     * saveTaskListData(index) if index -1 then save everything
     * loadTaskListData return taskList and highest index(-1 if no existing data)
     *
     * methods needed from Timetable
     * date
     * time
     * task
     * index
     *
     * methods given to Timetable
     * saveTimetableData()
     * loadTimetableData return timetable
     *
     * methods given to UI
     * lastSaved
     * index
     *
     * methods given to Parser
     * ???
     *
     *
     *
     *
    * */

    private int size;

    public Storage(int i) {
        this.size = i;

    }

    public void saveTaskListData(taskList list) {
        File csvFile = new File(filePath);
        if (csvFile.isFile()) {
            String row;
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(filePath));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    /* do something with the data */
                }
                csvReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
