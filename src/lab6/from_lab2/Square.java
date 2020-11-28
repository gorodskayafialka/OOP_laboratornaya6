package lab6.from_lab2;

import java.io.Serializable;

public class Square implements Shape, Serializable
{
    private double A;

    public Square(double A) throws IllegalArgumentException
    {
        if (A <= 0)
            throw new IllegalArgumentException("Square's side should be positive");
        this.A = A;
    }

    @Override
    public double calcArea() {
        return A * A;
    }

    @Override
    public double calcPerimeter() {
        return 4 * A;
    }

    @Override
    public String toString() {
        return "Square (" + A + ")";
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }
}