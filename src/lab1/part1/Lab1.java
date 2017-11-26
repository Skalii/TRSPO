package lab1.part1;

import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        Lab1PlusMinus calcPlusMinus = new Lab1PlusMinus(a, b);
        System.out.println("Сложение: " + calcPlusMinus.plus() + "\nВычитание: " + calcPlusMinus.minus());

        Lab1MultDiv calcMultDiv = new Lab1MultDiv(a, b);
        System.out.println("Произведение: " + calcMultDiv.mult() + "\nЧастное: " + calcMultDiv.div());

    }
}
