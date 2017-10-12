package lab2;

public class Lab2 extends Thread {

    public static void main(String[] args) {
        Lab2Run1 lab2Run1 = new Lab2Run1();
        Lab2Run2 lab2Run2 = new Lab2Run2();

        Thread thread = new Thread(lab2Run1);

        thread.start();
        thread = new Thread(lab2Run2);
        thread.start();
    }
}
