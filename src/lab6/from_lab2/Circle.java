package lab6.from_lab2;

import java.io.Serializable;

public class Circle implements Shape, Serializable {
    private double Radius;

    public Circle(double Radius) throws IllegalArgumentException {
        if (Radius <= 0) throw new IllegalArgumentException("Radius should be positive");
        this.Radius = Radius;
    }

    @Override
    public double calcArea() {
        return 3.14 * Math.pow(Radius, 2);
    }

    @Override
    public double calcPerimeter() {
        return 2 * 3.14 * Radius;
    }

    @Override
    public String toString() {
        String Info = "Circle ( " + Radius + ")";
        return Info;
    }

    public double getRadius() {
        return Radius;
    }

    public void setRadius(double radius) {
        Radius = radius;
    }
}
