package lab1.part2;

public class Lab1 extends Thread {

    public static void main(String[] args) {
        Lab1Run1 lab1Run1 = new Lab1Run1();
        Lab1Run2 lab1Run2 = new Lab1Run2();

        Thread thread = new Thread(lab1Run1);

        thread.start();
        thread = new Thread(lab1Run2);
        thread.start();
    }
}
