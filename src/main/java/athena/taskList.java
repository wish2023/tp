package athena;

import java.util.Arrays;

public class taskList {
    private String[] list;
    public taskList(String list) {
        String[] sepList = list.split(" ");
        this.list=sepList;
    }

    public String[] getList() {
        return list;
    }

    @Override
    public String toString() {
        return "taskList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }

    public void add(String[] data) {
        for(String i : data){

            System.out.println(i);
        }

    }
}
