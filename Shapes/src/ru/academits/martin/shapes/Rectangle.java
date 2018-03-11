package ru.academits.martin.shapes;

public class Rectangle implements Shape {

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        if (side1 <= 0 || side2 <= 0) {
            throw new IllegalArgumentException("Длина стороны должна быть больше 0.");
        }
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public double getWidth() {
        return Math.min(side1, side2);
    }

    @Override
    public double getHeight() {
        return Math.max(side1, side2);
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public String toString() {
        return "Rectangle: side1 = " + side1 + ", side2 = " + side2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1);
        hash = prime * hash + Double.hashCode(side2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        return (side1 == rectangle.side1 && side2 == rectangle.side2);
    }
}
