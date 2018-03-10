package ru.academits.martin.shapes;

public class Triangle extends Shape {

    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        super();
        double side1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double side2 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        if (side1 >= side1 + side2 || side2 >= side1 + side3 || side3 >= side1 + side2) {
            throw new IllegalArgumentException("Это не треугольник");//TODO правильное ли исключение используется?
        }
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3));
    }

    @Override
    public double getPerimeter() {
        double side1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double side2 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triangle: coordinates = (" + x1 + "," + y1 + "), (" + x2 + "," + y2 + "), (" + x3 + "," + y3 + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) x1;
        hash = prime * hash + (int) y1;
        hash = prime * hash + (int) x2;
        hash = prime * hash + (int) y2;
        hash = prime * hash + (int) x3;
        hash = prime * hash + (int) y3;
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
        Triangle triangle = (Triangle) obj;
        return (x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3);
    }
}