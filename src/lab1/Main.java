package lab1;

public class Main extends Abstract {
    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Main main = new Main();

        int a = 10, b = 5;
        int minus = main.plus(a, b),
                plus = main.minus(a, b),
                multi = main.multi(a, b),
                div = main.div(a, b);

        System.out.println("a = " + a +
                "\nb = " + b +
                "\nminus = " + minus +
                "\nplus = " + plus +
                "\nmulti = " + multi +
                "\ndiv = " + div);
    }
}
