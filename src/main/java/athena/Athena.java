package athena;
import java.util.ArrayList;

public class Athena {

    public static void main(String[] args) {

        TaskList list = new TaskList();
        ArrayList<String[]> listOfTasks = new ArrayList<String[]>();
        for(int i=0;i<10;i++) {
            String[] task = {"this"+i, "is"+i, "donut"+i};
            listOfTasks.add(task);
        }
        for( String[] i: listOfTasks)
        list.addToList(new Task(i[0],i[1],i[2]));
        Storage store = new Storage("savedata.csv");
        store.saveTaskListData(list);
        TaskList altList = store.loadTaskListData();
        altList.displayList();

    }
}
