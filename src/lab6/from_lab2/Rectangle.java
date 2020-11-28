package lab6.from_lab2;

import java.io.Serializable;

public class Rectangle implements Shape, Serializable {
    private double A, B;

    public Rectangle(double A, double B) throws IllegalArgumentException {
        if ((A <= 0) || (B <= 0))
            throw new IllegalArgumentException("Rectangle's sides should be positive");
        this.A = A;
        this.B = B;
    }

    @Override
    public double calcArea() {
        return A * B;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (A + B);
    }

    @Override
    public String toString() {
        return "Rectangle (" + A + "x" + B + ")";
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
}
