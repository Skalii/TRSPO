package lab3;

import java.util.Objects;
import java.util.Scanner;

public class Lab3Threads extends Thread {

    private Thread thread;
    static int i = 0;

    public static void main(String[] args) {
        System.out.println("Введите имена потоков: ");

        new Thread(new Lab3Threads()).start();
        new Thread(new Lab3Threads()).start();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String name = scanner.nextLine();
            if (Objects.equals(name, "stop")) break;
            else setThread(name);
        }
    }

    private void setThread(String name) {
        thread = new Thread(name);
        System.out.println("Имя потока " + ++i + ": " + thread.getName());
    }
}
