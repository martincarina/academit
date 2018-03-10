package ru.academits.martin.shapes.exec;

import ru.academits.martin.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length < 1) {
            throw new IndexOutOfBoundsException("Объектов в массиве должно быть не меньше одного.");
        }
        Arrays.sort(shapes, new AreaComparator());
        return shapes[0];
    }

    private static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        if (shapes.length < 2) {
            throw new IndexOutOfBoundsException("Объектов в массиве должно быть не меньше двух.");
        }
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[1];
    }

    public static void main(String[] args) {
        Shape square1 = new Square(5);

        System.out.println("Квадрат1:");
        System.out.printf("Ширина = %f%n", square1.getWidth());
        System.out.printf("Высота = %f%n", square1.getHeight());
        System.out.printf("Площадь = %f%n", square1.getArea());
        System.out.printf("Периметр = %f%n", square1.getPerimeter());

        Shape square2 = new Square(5);

        System.out.println("Квадрат2:");
        System.out.printf("Ширина = %f%n", square2.getWidth());
        System.out.printf("Высота = %f%n", square2.getHeight());
        System.out.printf("Площадь = %f%n", square2.getArea());
        System.out.printf("Периметр = %f%n", square2.getPerimeter());

        Shape triangle1 = new Triangle(0, 0, 0, 1, 1, 1);
        System.out.println("Треугольник1:");
        System.out.printf("Ширина = %f%n", triangle1.getWidth());
        System.out.printf("Высота = %f%n", triangle1.getHeight());
        System.out.printf("Площадь = %f%n", triangle1.getArea());
        System.out.printf("Периметр = %f%n", triangle1.getPerimeter());

        Shape triangle2 = new Triangle(5, 7, 0, 1, 1, 1);
        System.out.println("Треугольник2:");
        System.out.printf("Ширина = %f%n", triangle2.getWidth());
        System.out.printf("Высота = %f%n", triangle2.getHeight());
        System.out.printf("Площадь = %f%n", triangle2.getArea());
        System.out.printf("Периметр = %f%n", triangle2.getPerimeter());

        Shape rectangle1 = new Rectangle(8, 7);
        System.out.println("Прямоугольник1:");
        System.out.printf("Ширина = %f%n", rectangle1.getWidth());
        System.out.printf("Высота = %f%n", rectangle1.getHeight());
        System.out.printf("Площадь = %f%n", rectangle1.getArea());
        System.out.printf("Периметр = %f%n", rectangle1.getPerimeter());

        Shape rectangle2 = new Rectangle(4, 8);
        System.out.println("Прямоугольник2:");
        System.out.printf("Ширина = %f%n", rectangle2.getWidth());
        System.out.printf("Высота = %f%n", rectangle2.getHeight());
        System.out.printf("Площадь = %f%n", rectangle2.getArea());
        System.out.printf("Периметр = %f%n", rectangle2.getPerimeter());

        Shape circle1 = new Circle(2);
        System.out.println("Круг1:");
        System.out.printf("Ширина = %f%n", circle1.getWidth());
        System.out.printf("Высота = %f%n", circle1.getHeight());
        System.out.printf("Площадь = %f%n", circle1.getArea());
        System.out.printf("Периметр = %f%n", circle1.getPerimeter());

        Shape circle2 = new Circle(3);
        System.out.println("Круг2:");
        System.out.printf("Ширина = %f%n", circle2.getWidth());
        System.out.printf("Высота = %f%n", circle2.getHeight());
        System.out.printf("Площадь = %f%n", circle2.getArea());
        System.out.printf("Периметр = %f%n", circle2.getPerimeter());

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("Max Area:");
        System.out.printf("Ширина = %f%n", maxAreaShape.getWidth());
        System.out.printf("Высота = %f%n", maxAreaShape.getHeight());
        System.out.printf("Площадь = %f%n", maxAreaShape.getArea());
        System.out.printf("Периметр = %f%n", maxAreaShape.getPerimeter());

        System.out.println("Second Perimeter:");
        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapes);
        System.out.printf("Ширина = %f%n", secondPerimeterShape.getWidth());
        System.out.printf("Высота = %f%n", secondPerimeterShape.getHeight());
        System.out.printf("Площадь = %f%n", secondPerimeterShape.getArea());
        System.out.printf("Периметр = %f%n", secondPerimeterShape.getPerimeter());

        System.out.println(square2.toString());
        System.out.println(triangle2.toString());
        System.out.println(rectangle2.toString());
        System.out.println(circle2.toString());

        System.out.print("Проверка на равенство фигур: ");
        if (rectangle1.hashCode() != rectangle2.hashCode()) {
            System.out.println("Фигуры не равны.");
        } else {
            if (rectangle2.equals(rectangle1)) {
                System.out.println("Фигуры равны.");
            } else {
                System.out.println("Фигуры не равны.");
            }
        }
    }
}

class AreaComparator implements Comparator<Object> {
    @Override
    public int compare(Object object1, Object object2) {
        Shape shape1 = (Shape) object1;
        Shape shape2 = (Shape) object2;
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}

class PerimeterComparator implements Comparator<Object> {
    @Override
    public int compare(Object object1, Object object2) {
        Shape shape1 = (Shape) object1;
        Shape shape2 = (Shape) object2;
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}

