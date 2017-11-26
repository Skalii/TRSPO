package lab1.part2;

public class Lab1Run2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Я работаю!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
