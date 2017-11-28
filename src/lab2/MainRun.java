package lab2;

public class MainRun implements Runnable {

    public void run() {
        for (char i = 'а'; i <= 'я'; i++) {
            System.out.println(i);
        }
    }
}
