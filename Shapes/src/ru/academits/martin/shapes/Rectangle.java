package ru.academits.martin.shapes;

public class Rectangle extends Shape {
    private double width;//TODO может надо заменить на side1 и side2???
    private double height;

    public Rectangle(double side1, double side2) {
        super();
        if (side1 <= 0 || side2 <= 0) {
            throw new IllegalArgumentException("Длина стороны должна быть больше 0.");
        }
        width = Math.min(side1, side2);
        height = Math.max(side1, side2);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle: side1 = " + width + ", side2 = " + height;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) width;
        hash = prime * hash + (int) height;
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
        return (height == rectangle.height && width == rectangle.width);
    }
}
