package athena;

public class Athena {

    private Ui ui;

    public Athena() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
    }

    public void runProgram() {
        ui.printTaskAdded();
        ui.printTaskDeleted();
        ui.printTaskDone();
        ui.printTaskEdited();
    }
}
