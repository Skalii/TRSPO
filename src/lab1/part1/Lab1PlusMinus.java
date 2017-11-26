package lab1.part1;

public class Lab1PlusMinus {
    protected double a, b;

    public Lab1PlusMinus(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double plus() {
        return a + b;
    }

    public double minus() {
        return a - b;
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
