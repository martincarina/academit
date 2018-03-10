package ru.academits.martin.shapes;

public class Square extends Shape {
    private double side;

    public Square(double side) {
        super();
        if (side <= 0) {
            throw new IllegalArgumentException("Длина стороны должна быть больше 0.");
        }
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Square: side = " + side;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) side;
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
        Square square = (Square) obj;
        return (side == square.side);
    }
}