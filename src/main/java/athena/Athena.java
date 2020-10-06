package athena;

public class Athena {

    private Ui ui;

    public Athena() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();

        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Command command = parser.parse("add n/Assignment1 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
        command.execute(taskList, athena.ui);
        command = parser.parse("add n/Assignment1 t/1100 d/16-09 D/2 r/Monday i/high a/Refer to lecture notes");
        command.execute(taskList, athena.ui);
        command = parser.parse("list");
        command.execute(taskList, athena.ui);
        command = parser.parse("help");
        command.execute(taskList, athena.ui);
    }

    public void runProgram() {
        // TODO: pass task as argument
//        ui.printTaskAdded();
//        ui.printTaskDeleted();
//        ui.printTaskDone();
//        ui.printTaskEdited();
    }
}
