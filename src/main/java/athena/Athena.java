package athena;

public class Athena {

    public static void main(String[] args) {

        taskList list = new taskList("string loop hello");
        Storage store = new Storage("savedata.csv");
        store.saveTaskListData(list);
        System.out.println(list.toString());
        list = store.loadTaskListData();
        System.out.println(list.toString());

    }
}
