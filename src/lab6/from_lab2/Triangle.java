package lab6.from_lab2;

public class Triangle implements Shape {
    private double A, B, C;

    public Triangle(double A, double B, double C) throws IllegalArgumentException {
        if ((A <= 0) || (B <= 0) || (C <= 0))
            throw new IllegalArgumentException("Triangle's sides should be positive");
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public double calcArea() {
        double AB, BC, AC;
        double p = calcPerimeter() / 2;
        return Math.pow((p * (p - A) * (p - B) * (p - C)), 0.5);
    }

    @Override
    public double calcPerimeter() {
        return A + B + C;
    }

    @Override
    public String toString() {
        return "Triangle (" + A + ", " + B + ", " + C + ")";
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }
}
