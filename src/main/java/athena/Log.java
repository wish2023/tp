package athena;

import athena.task.Task;

import java.util.ArrayList;
import java.util.Collections;

public class Log {
    private ArrayList<Integer> numberList;
    private Integer size;
    private Integer start;
    private Integer end;


    public Log(Integer start, Integer end) {
        this.size = end - start;
        this.numberList = new ArrayList<Integer>((Collections.nCopies(size, -1)));
        this.start = start;
        this.end = end;
    }
    public void setNumber(int pos,int number){
        numberList.set(pos,number);
    }
    public ArrayList<Integer> getNumberList(){
        return this.numberList;
    }

    public void setNumberList(ArrayList<Task> taskList) {
        Integer space = end - start;
        for (Task currTask : taskList) {
            int span = currTask.getTimeInfo().getDuration();
            if (span <= space) {
                int taskNumber = currTask.getNumber();
                for (int i = 0; i < span; i++) {
                    int relativePos = end - space - start;
                    numberList.set(i + relativePos, taskNumber);
                }
                space -= span;
            }
            if (space == 0) {
                break;
            }
        }
    }


    public int get(Integer start) {
        return numberList.get(start);
    }
}
