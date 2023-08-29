package console_application;

public class threadexecution {
    public static void main(String[] args) {
       // bank_implementation bb = new bank_implementation();
        //bank_collection_implement bb = new bank_collection_implement();
        bank_file_implement bb = new bank_file_implement();
        Thread th1 = new Thread(bb, "moni");
        Thread th2 = new Thread(bb, "Hari");
        Thread th3 = new Thread(bb, "Abi");

        th1.start();

        th2.start();

        th3.start();

    }
}
